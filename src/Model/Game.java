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

    private final Difficulty difficulty;
    private final int mapNumber;
    private int health;
    private int money;
    private final Observable observable;
    private final Board b;
    private final WaveManager waveManager;
    private boolean waveRunning = false;
    private Thread gameLoopThread;
    private Thread enemyCreatorThread;
    private  List<Enemy> enemiesInWave;
    private List<Tower> towers;
    private List <Projectile> projectileList;
    private boolean autostart;

    int round = 1;

    private final int gameSpeed;

    private int enemyCounter;
    private int totalNumberOfRounds;

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

    public List<Enemy> getEnemiesInWave() {
        return enemiesInWave;
    }

    /**
     * Enemy counter keeps track of amount of enemies in current wave
     * resets list (enemiesInWave)
     */
    public void nextRound(){
        observable.notifyRoundStart();
        enemyCounter = waveManager.getWaveSize(round);
        enemiesInWave = new ArrayList<>();
        waveRunning = true;
        startAllTowerTimers();
        run();
    }
    public void notifyAllObservers(){
        observable.update();
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


    public void run() {
        startEnemyCreatorThread();
        startGameLoopThread();

    }

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


    private synchronized void update(){
        checkIfGameOver();

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
                    e.update();
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
    }
    private synchronized void checkTowerRadius(){

        if(enemiesInWave != null && towers != null){
            Iterator<Tower> towerIterator = towers.listIterator();
            while (towerIterator.hasNext()){

                Tower tower = towerIterator.next();
                if(tower.getIsReadyToFire()){
                    tower.attackIfEnemyInRange(enemiesInWave);
                    //sidoeffekt av t.getProjectile() är att currentProjectile sätts till null (ska vara så just nu)
                    Projectile p = tower.getProjectile();
                    if (p!=null){
                        projectileList.add(p);
                        System.out.println("projectile was added----------------------------------------------");
                        observable.notifyProjectileAdded(p);
                    }
                }
            }
        }
    }
    private synchronized void checkIfProjectilesHit() {
        if (projectileList != null) {
            Iterator<Projectile> iterator = projectileList.listIterator();
            while (iterator.hasNext()) {
                Projectile p = iterator.next();
                if (!p.isExisting()) {
                    System.out.println("p ska vara false " + p.isExisting());
                    removeProjectile(p);
                    //iterator.remove();
                    break;
                }
            }
        }
    }
    private synchronized void enemyIsDead(Enemy e){
        observable.notifyEnemyDead(e);
        if(!enemiesInWave.remove(e)){
            System.out.println("error in removing enemy");
        }
    }

    private void checkIfGameOver(){
        if(health <= 0){
            gameOver();
        }
        //if its the last wave and there are no more enemies
        else if(round == totalNumberOfRounds && enemiesInWave.size() == 0){
            gameWon();
        }
    }
    private void gameOver(){
        observable.notifyGameOver();
        stopAllTowerTimers();
        stopGameLoopThread();
        stopEnemyCreatorThread();

    }
    private void gameWon(){
        stopAllTowerTimers();
        observable.notifyGameWon();
    }

    private void stopEnemyCreatorThread(){

        if(enemyCreatorThread.isAlive()){
            enemyCreatorThread.interrupt();
        }
    }
    private void stopGameLoopThread(){
        if(gameLoopThread.isAlive()){
            gameLoopThread.interrupt();
        }
    }
    public void pause(){
        waveRunning = false;
        stopAllTowerTimers();
        stopEnemyCreatorThread();
        stopGameLoopThread();
    }

    public void play() {
        waveRunning = true;

        startAllTowerTimers();


        if (enemyCreatorThread.isInterrupted()) {
            startEnemyCreatorThread();
        }

        startGameLoopThread();
    }

    public void endRound(){
        observable.notifyRoundOver();

        if(autostart){
            money = money +round*100;
            round++;
            observable.notifyRoundStart();
            stopEnemyCreatorThread();
            stopGameLoopThread();
            nextRound();

        }
        else{
            stopEnemyCreatorThread();
            stopGameLoopThread();
            waveRunning = false;
            money = money +round*100;
            round++;
            stopAllTowerTimers();
        }

    }
    public void removeProjectile(Projectile p){
        observable.notifyProjectileRemoved(p);
        if (!projectileList.remove(p)){
            System.out.println("Error in removing projectile");
        }
    }
    private void stopAllTowerTimers(){
        for (Tower t : towers){
            t.stopTimer();
        }
    }
    private void startAllTowerTimers(){
        for (Tower t : towers){
            t.startTimer();
        }
    }





    public boolean isWaveRunning() {
        return waveRunning;
    }



    public boolean addMapObserver(final MapObserver mapObserver){
        return this.observable.addObserver(mapObserver);
    }

    public boolean addProjectileObserver(ProjectileObserver projectileObserver) {
        return this.observable.addObserver(projectileObserver);
    }




    public List<Projectile> getProjectileList() {
        return this.projectileList;
    }


    //sets initial values of health and money
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

    public boolean isCellOccupied(int index) {
        return b.isCellOccupied(index);
    }

    public void setCellOccupied(int index) {
        b.setCellOccupied(index);
    }

    public void setCellUnoccupied(int index) {
        b.setCellUnoccupied(index);
    }

    public void updateArrayWithTower(int index, TowerFactory towerFactory) {
        setCellOccupied(index);
        Tower t = towerFactory.createTower(getBoard().get(index));
        towers.add(t);
    }

    public Tower getTowerInCell(int x, int y) {
        for (Tower t : towers) {
            if (t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null; //Should never get here
    }

    private void usePelle() { // implicit synchronized(this)
        // Do stuff
        // T1: pelle2() -> tar monitorn för towers
        // T2: pelle2() -> kan inte ta monitorn för att den has av T1
        // T1: klar med pelle2() -> monitorn för towers släpps
        // T2: kan nu ta monitorn för towers.
        synchronized (towers) {
            // Kritisk sektion.
            towers.clear();
        }
    }

    private void pelle2() { // implicit synchronized(this)
        synchronized(towers) {
            // Do stuff
        }
    }








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

        //TODO Replace with exception
        return -1;
    }



    public void removeTower(Tower t){
        t.stopTimer();
        if(!towers.remove(t)){
            System.out.println("tower not found ");
        }
    }


    public void addMoney(int toAdd) {
        money += toAdd;
    }
    public int getMageTowerPrice(){
      return  new MageTowerFactory().getPrice();
    }
    public int getArcherTowerPrice(){
        return new ArcherTowerFactory().getPrice();
    }

    public Tower leftUpgradeTower(Tower t) {
        Tower tower = t.leftUpgrade(t);

        //This shiiett updates the tower array with the new tower
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

    public Tower rightUpgradeTower(Tower t) {
        Tower tower = t.rightUpgrade(t);

        //This shiiett updates the tower array with the new tower
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



}
