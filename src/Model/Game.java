package Model;


import Model.Cell.Cell;
import Model.Enemy.BaseEnemy;
import Model.Enemy.Enemy;
import Model.Towers.*;
import View.MapObserver;
import View.ProjectileObserver;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game  {
    private int round = 1;
    private final int gameSpeed;
    private int enemyCounter;
    private int totalNumberOfRounds;
    private final int mapNumber; //only one now but can change later
    private int health;
    private int money;

    private String nameOfMap;

    private boolean waveRunning = false;
    private boolean autostart;

    private final Difficulty difficulty;
    private final Observable observable;
    private final Board b;
    private final WaveManager waveManager;

    private Thread gameLoopThread;
    private Thread enemyCreatorThread;

    private List<Enemy> enemiesInWave;
    private List<Tower> towers;
    private List <Projectile> projectileList;


    public Game(Difficulty difficulty, int mapNumber) {
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        observable = new Observable();
        b= new Board(mapNumber);
        List<Direction> enemyPath = b.getPath();
        waveManager = new WaveManager(difficulty, enemyPath,b.getStartPos());
        setValues();
        towers = new ArrayList<>();
        projectileList = new ArrayList<>();
        this.gameSpeed = 30;
    }


    /**
     * creates enemy counter that keeps track of amount of enemies in current wave
     * resets list (enemiesInWave)
     * calls run
     */
    public void nextRound(){
        observable.notifyRoundStart();
        enemyCounter = waveManager.getWaveSize(round);
        enemiesInWave = new ArrayList<>();
        waveRunning = true;
        run();
    }

    public void notifyAllObservers(){
        observable.update();
    }

    /**
     * starts secondary threads
     */
    public void run() {
        startEnemyCreatorThread();
        startGameLoopThread();
        startAllTowerTimers();
    }
    /**
     * created a new thread that adds enemies to List:enemiesInWave
     * after a delay
     */
    private void startEnemyCreatorThread(){
        enemyCreatorThread = new Thread(()->{
            waveManager.createWave(round);
            while (waveRunning && enemyCounter>=0) {
                Enemy enemy = waveManager.getEnemy(enemyCounter);
                enemiesInWave.add(enemy);
                threadSleep(enemy.spawnTime() * 100);
                enemyCounter--;
            }

        });
        enemyCreatorThread.setDaemon(true);
        enemyCreatorThread.start();
    }

    /**
     * creates a new thread that loops update
     * ends the round if there are no more enemies left
     */
    private void startGameLoopThread(){
        gameLoopThread = new Thread(() -> {
            while (waveRunning) {
                if(enemiesInWave.size() > 0) {
                    update();
                }
                else if(!enemyCreatorThread.isAlive()){ //waits until all enemies have been created
                    endRound();
                    System.out.println("round ended");
                }
                threadSleep(gameSpeed);
            }
        });
        gameLoopThread.setDaemon(true);
        gameLoopThread.start();
    }

    /**
     * sleeps the current thread wherever it is called
     * @param time thread is asleep in milliseconds
     */
    private void threadSleep(int time){

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            gameLoopThread.interrupt();
            if (enemyCreatorThread.isAlive()) {
                enemyCreatorThread.interrupt();
            }
        }
    }

    /**
     * this acts as the main game loop
     * loops through all enemies and checks if it has been killed or is out if not the move
     * loops through projectiles that need to be updated
     * checks tower radius, if projectiles hit and if game is over
     * Updates View
     */
    private void update(){
        synchronized (enemiesInWave){
            if(enemiesInWave != null){
                Iterator<Enemy> enemyIterator = enemiesInWave.listIterator();
                while (enemyIterator.hasNext()){
                    Enemy e = enemyIterator.next();
                    if(e.isKilled()){
                        money = money + 20;
                        enemyIsDead(e);
                        break;
                    }
                    else if(e.isOut()){
                        health--;
                        enemyIsDead(e);
                        break;
                    }
                    else{
                        e.move();
                    }
                }
            }
        }

        if(projectileList !=null){
            Iterator<Projectile> projectileIterator = projectileList.listIterator();
            while(projectileIterator.hasNext()){
                Projectile p = projectileIterator.next();
                p.update();
            }
        }
        checkTowerRadius();
        checkIfProjectilesHit();
        observable.update(); //notifies view to update graphics
        checkIfGameOver();
    }

    /**
     * Loops through all the towers to first check if they are ready to fire
     * if ready then attack and create a projectile
     * add the projectile to List:ProjectileList and observable
     */
    private synchronized void checkTowerRadius(){
        if(enemiesInWave != null && towers != null){
            Iterator<Tower> towerIterator = towers.listIterator();
            while (towerIterator.hasNext()){
                Tower tower = towerIterator.next();
                if(tower.getIsReadyToFire()){
                    tower.attackIfEnemyInRange(enemiesInWave);
                    Projectile p = tower.getProjectile();
                    if (p!=null){
                        projectileList.add(p);
                        observable.notifyProjectileAdded(p);
                    }
                }
            }
        }
    }

    /**
     * loops through a list as an iterator and checks if they are to be removed
     */
    private synchronized void checkIfProjectilesHit() {
        if (projectileList != null) {
            Iterator<Projectile> iterator = projectileList.listIterator();
            while (iterator.hasNext()) {
                Projectile p = iterator.next();
                if (!p.isExisting()) {
                    removeProjectile(p);
                    //iterator.remove();
                    break;
                }
            }
        }
    }

    /**
     * removes enemy e from observable and List:enemiesInWave
     * @param e Enemy to be removed
     */
    private synchronized void enemyIsDead(Enemy e){
        observable.notifyEnemyDead(e);
        if(!enemiesInWave.remove(e)){
            System.out.println("error in removing enemy");
        }
    }

    /**
     * checks if game is over or game is won
     */
    private void checkIfGameOver(){
        if(health <= 0){
            gameOver();
        }
        //if its the last wave and there are no more enemies and enemies arent being created
        else if(round == totalNumberOfRounds && enemiesInWave.size() == 0 && !enemyCreatorThread.isAlive()){
            gameWon();
        }
    }
    /**
     * Stops all secondary threads
     * notifies view that game is over
     */
    private void gameOver(){
        observable.notifyGameOver();
        stopAllTowerTimers();
        stopGameLoopThread();
        stopEnemyCreatorThread();
    }

    /**
     * Stops all secondary threads
     * notifies view that game is won
     */
    private void gameWon(){
        stopAllTowerTimers();
        observable.notifyGameWon();
        stopGameLoopThread();
        stopEnemyCreatorThread();
    }
    /**
     * checks if the thread is alive
     * interrupts the thread
     */
    private void stopEnemyCreatorThread(){
        if(enemyCreatorThread.isAlive()){
            enemyCreatorThread.interrupt();
        }
    }

    /**
     * checks if the thread is alive
     * interrupts the thread
     */
    private void stopGameLoopThread(){
        if(gameLoopThread.isAlive()){
            gameLoopThread.interrupt();
        }
    }
    /**
     * stops all secondary Threads
     * sets waverunning to false
     */
    public void pause(){
        waveRunning = false;
        stopAllTowerTimers();
        stopEnemyCreatorThread();
        stopGameLoopThread();
    }

    /**
     * starts all secondary Threads
     * sets waverunning to true
     */
    public void play() {
        waveRunning = true;
        startAllTowerTimers();
        if (enemyCreatorThread.isInterrupted()) {
            startEnemyCreatorThread();
        }
        startGameLoopThread();
    }

    /**
     * notifies observers that round is over
     * if autostart active start new round
     * stops enemyCreatorThread, and GameLoopThread
     * adds money
     * increments round
     */
    public void endRound(){
        observable.notifyRoundOver();
        stopEnemyCreatorThread();
        stopGameLoopThread();
        money = money + round*50;
        if(autostart){
            observable.notifyRoundStart();
            nextRound();

        }
        else{
            waveRunning = false;
            stopAllTowerTimers();
        }
        round++;
    }

    /**
     * Removes a projectile from the observable list and the projectile list
     * @param p The projectile to be removed
     */
    public void removeProjectile(Projectile p){
        observable.notifyProjectileRemoved(p);
        if (!projectileList.remove(p)){
            System.out.println("Error in removing projectile");
        }
    }

    /**
     * loops through all towers and stops their timer
     */
    private void stopAllTowerTimers(){
        for (Tower t : towers){
            t.stopTimer();
        }
    }

    /**
     * loops through all towers and starts their timer
     */
    private void startAllTowerTimers(){
        for (Tower t : towers){
            t.startTimer();
        }
    }

    /**
     * sets initial values of multiple in game attributes
     * depending on the games difficulty
     */
    private void setValues() {
        switch (difficulty) {
            case EASY:
                this.health = 20;
                this.money = 2000;
                this.totalNumberOfRounds = 5;
                break;
            case MEDIUM:
                this.health = 10;
                this.money = 1500;
                this.totalNumberOfRounds = 10;
                break;
            case HARD:
                this.health = 1;
                this.money = 800;
                this.totalNumberOfRounds = 15;
                break;
        }
        switch(mapNumber){
            case 1:
                this.nameOfMap = "Curvy Snake";
                break;
            case 2:
                this.nameOfMap = "TBH";
                break;
            case 3:
                this.nameOfMap = "TBH2";
                break;
        }
    }

    /**
     *  adds a final mapobserver to the list of observers in Observerable
     * @param mapObserver a class that implements MapObserver
     * @return if the mapobserver is already observing
     */
    public boolean addMapObserver(final MapObserver mapObserver){
        return this.observable.addObserver(mapObserver);
    }
    /**
     *  adds a  projectileObserver to the list of observers in Observerable
     * @param projectileObserver a class that implements ProjectileObserver
     * @return if the projectileObserver is already observing
     */
    public boolean addProjectileObserver(ProjectileObserver projectileObserver) {
        return this.observable.addObserver(projectileObserver);
    }


    /**
     * gets the tower in a specific cell
     * @param x position of Cell
     * @param y position of Cell
     * @return the Tower
     */
    public Tower getTower(int x, int y) {
        for (Tower t : towers) {
            if (t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null;
    }

    /**
     * adds a new tower to the tower array
     * sets a specific cell occupied
     * @param index the position of the cell
     * @param towerFactory sends in the specific towerfactory so
     *                     a new tower can be created
     */
    public void createTower(int index, TowerFactory towerFactory) {
        setCellOccupied(index);
        Tower t = towerFactory.createTower(getBoard().get(index));
        towers.add(t);
    }

    /**
     * gets the index in an array of cells
     * @param x_placement of a cell
     * @param y_placement of a cell
     * @return index of cell
     */
    public int getArrayIndex(int x_placement, int y_placement){

        int placeInArray = 0;
        for (int i = 0; i < b.getBOARD_WIDTH(); i++) {
            for (int j = 0; j < b.getBOARD_HEIGHT(); j++) {
                if (i == x_placement && j == y_placement) {
                    return placeInArray;
                }
                placeInArray++;
            }
        }
        return -1;
    }

    /**
     * removes tower t from towers array
     * stops the timer of the tower
     * prints out error if tower t does not exist in towers array
     * @param t the tower to be removed
     */
    public void removeTower(Tower t){
        t.stopTimer();
        if(!towers.remove(t)){
            System.out.println("tower not found ");
        }
    }
    /**
     * updates the tower array with the new tower
     * @param t type of tower
     * @return the the upgraded tower
     */
    public Tower leftUpgradeTower(Tower t) {
        Tower tower = t.leftUpgrade(t);
        int i = 0;
        for (Tower t1 : towers) {
            if (t1 == t) {
                towers.set(i, tower);
                break;
            }
            i++;

        }
        return tower;
    }
    /**
     * updates the tower array with the new tower
     * @param t type of tower
     * @return the the upgraded tower
     */
    public Tower rightUpgradeTower(Tower t) {
        Tower tower = t.rightUpgrade(t);
        int i = 0;
        for (Tower t1 : towers) {
            if (t1 == t) {
                towers.set(i, tower);
                break;
            }
            i++;
        }

        return tower;
    }

    /**
     * getters and setters
     */
    public List<Enemy> getEnemiesInWave() {
        return enemiesInWave;
    }
    public int getRound() {
        return round;
    }
    public int getStartPos() {
        return b.getStartPos();
    }
    public int getEndPos() {
        return b.getEndPos();
    }
    public void setAutostart(boolean a){
        autostart = a;
    }
    public boolean getWaveRunning() {
        return waveRunning;
    }
    public List<Projectile> getProjectileList() {
        return this.projectileList;
    }
    public List<Cell> getBoard() {
        return b.getBoard();
    }
    public int getHealth() {
        return health;
    }
    public int getMoney() {
        return money;
    }
    public boolean getCellOccupied(int index) {
        return b.isCellOccupied(index);
    }
    public void setCellOccupied(int index) {
        b.setCellOccupied(index);
    }
    public void setCellUnoccupied(int index) {
        b.setCellUnoccupied(index);
    }
    public int getMageTowerPrice(){
        return  new MageTowerFactory().getPrice();
    }
    public int getArcherTowerPrice(){
        return new ArcherTowerFactory().getPrice();
    }
    public void addMoney(int toAdd) {
        money += toAdd;
    }
    public String getNameOfMap() {
        return nameOfMap;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
