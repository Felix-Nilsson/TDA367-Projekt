package Model;


import Model.Cell.Cell;
import Model.Enemy.BaseEnemy;
import Model.Enemy.Enemy;
import Controller.Observer;
import Model.Towers.Projectile;
import Model.Towers.Tower;
import Model.Towers.TowerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends Observable1 implements Updatable {

    private final Difficulty difficulty;
    private final int mapNumber;
    private int health;
    private int money;
    private final Observable observable;
    private final UpdateModel updateModel;
    private final UpdateModel viewUpdate;
    private final Observable1 observable1;
    private final Board b;
    private final WaveManager waveManager;
    private boolean waveRunning = false;
    private Thread gameLoopThread;
    private Thread enemyCreatorThread;
    private Updatable updatable;
    private final List<BaseEnemy.Direction> enemyPath;
    private List<Enemy> enemiesInWave;
    private List<Tower> towers;
    private List <Projectile> projectileList;

    int round = 1;
    private int gameSpeed;
    private int enemyCounter;

    public Game (Difficulty difficulty, int mapNumber){
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        observable = new Observable();
        updateModel = new UpdateModel();
        viewUpdate = new UpdateModel();
        observable1 = new Observable1();
        b= new Board(mapNumber);
        this.enemyPath = b.getPath();
        waveManager = new WaveManager(difficulty,enemyPath,b.getStartPos());
        setValues();
        towers = new ArrayList<>();

        projectileList = new ArrayList<>();

        this.gameSpeed = 30;

    }

    public List<Enemy> getEnemiesInWave() {
        return enemiesInWave;
    }

    public void nextRound(){
        waveManager.createWave(round);
        List<Enemy> sizeCounter = waveManager.getWave();
        enemyCounter = sizeCounter.size()-1;
        enemiesInWave = new ArrayList<>();
        waveRunning = true;
        run();

    }
    public int getRound(){
        return round;
    }

    public int getStartPos(){
        return b.getStartPos();
    }
    public int getEndPos(){
        return b.getEndPos();
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
                updateModel.add(enemy);
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
            if(enemyCreatorThread.isAlive()){
                enemyCreatorThread.interrupt();
            }
        }
    }

    public void update(){

        updateModel.update();
        checksRadius();
        observable.update();
        checkIfProjectilesHit();

    }

    public void pause(){
        if(enemyCreatorThread.isAlive()){
            enemyCreatorThread.interrupt();

        }
        gameLoopThread.interrupt();
        waveRunning = false;

    }
    public void play(){
        waveRunning = true;
        if(enemyCreatorThread.isInterrupted()){
            startEnemyCreatorThread();
        }
        startGameLoopThread();
    }
    public void endRound(){
        if(enemyCreatorThread.isAlive()){
            enemyCreatorThread.interrupt();
        }
        gameLoopThread.interrupt();
        waveRunning = false;
        update();
        round++;
    }
    public void removeEnemy(Enemy enemy){
        if(!updateModel.removeObserver(enemy)){
            System.out.println("error in removing observer");
        }
        if(enemiesInWave.remove(enemy)){
            System.out.println("error in removing enemy");
        }
    }
    public void removeProjectile(Projectile p){
        if (projectileList.remove(p)){
            System.out.println("proj was removed");
        }
    }

    public boolean isWaveRunning(){
        return waveRunning;
    }


    public boolean addObserver(final Observer observer){
        return this.observable.addObserver(observer);
    }
    public void addUpdatable(final Updatable updatable){
        updateModel.add(updatable);
    }

    @Override
    public void addObserver1(Observer1 observer) {
        super.addObserver1(observer);
    }

    @Override
    public void notifyObservers1ThatProjWasAdded(Projectile p) {
        super.notifyObservers1ThatProjWasAdded(p);
    }

    private synchronized void checkIfProjectilesHit(){
        if(projectileList.size()>0){
            System.out.println("projList.size(): " + projectileList.size());
            Iterator<Projectile>iterator = projectileList.listIterator();

            while (iterator.hasNext()){
                Projectile p = iterator.next();
                if (!p.isExisting()){

                    super.notifyObservers1ThatProjWasRemoved(p);
                    //projectileList.remove(p);
                    System.out.println("size before iterator.remove(): " + projectileList.size());
                    iterator.remove();
                    System.out.println("size after iterator.remove(): " + projectileList.size());
                }
            }
            /*
            for (Projectile p : projectileList){
                if (!p.isExisting()){
                    //TODO Ordningen på metodkallningarna kanske påverkar

                    super.notifyObservers1ThatProjWasRemoved(p);
                    projectileList.remove(p);
                    System.out.println("SER JAG NÅNSIN DETTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                }
            }

            System.out.println("projectile was remooooooooooooooooved");
            System.out.println("projList.size(): " + projectileList.size());

             */
        }

    }

    synchronized void checksRadius(){
        if (towers.size()>0 && enemiesInWave.size()>0){
            for (Tower t : towers){
                //TODO if (t.cooldown == false)
                t.attackIfEnemyInRange(enemiesInWave);
                //TODO p har inte alltid en projectile
                Projectile p = t.getProjectile();
                if (p!=null){
                    System.out.println("p är inte null");
                    projectileList.add(p);
                    System.out.println(projectileList.size());
                    this.notifyObservers1ThatProjWasAdded(p);
                }
                /*
                 Projectile p = t.getProjectile();

                projectileList.add(p);
                viewUpdate.update();
                observable1.notifyObservers1(p);
                */
                /*
                //Om det inte finns:
                double distX = e.getPositionX()-t.getPosX();
                //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. theAngle blir nu korrekt.
                //i Projectile skapa finns det minus framför vy för att återställa detta igen
                double distY = -(e.getPositionY()-t.getPosY());
                double distHyp = Math.sqrt(distX*distX + distY*distY);
                //System.out.println(distHyp);
                if (distHyp<t.getRange()){
                    double angle = Math.atan2(distY,distX);
                    t.setAngle(angle);
                    t.attack();
                        // Om towers är hitscan blir det: e.tookDamage()
                }

                     */

            }
        }
    }
    public List<Projectile> getProjectileList(){
        return this.projectileList;
    }


    public void enemyIsOut(Enemy e){
        health--;
        removeEnemy(e);
    }


    //sets initial values of health and money
    private void setValues(){
        switch (difficulty) {
            case EASY:
                this.health = 100;
                this.money = 2000; //TODO CHANGE BEFORE FINAL PUSH
                break;
            case MEDIUM:
                this.health = 50;
                this.money = 150;
                break;
            case HARD:
                this.health = 10;
                this.money = 80;;
                break;
        }
    }
    public List<Cell> getBoard(){
        return b.getBoard();
    }
    public int getHealth() { return health; }
    public int getMoney() { return money; }

    public boolean isCellOccupied(int index){
        return b.isCellOccupied(index);
    }

    public void setCellOccupied(int index){
        b.setCellOccupied(index);
    }
    public void setCellUnoccupied(int index) { b.setCellUnoccupied(index); }

    public void updateArrayWithTower(int index, TowerFactory towerFactory){
        //TODO update cell to occupied
        setCellOccupied(index);
        Tower t = towerFactory.createTower(getBoard().get(index),updateModel);
        towers.add(t);
    }

    public Tower getTowerInCell(int x, int y){
        for(Tower t: towers){
            if(t.getX() == x && t.getY() == y){
                return t;
            }
        }
        return null;
    }
    public int getArrayIndex(int x_placement, int y_placement){
        int placeInArray = 0;
        for(int i =0; i < b.getBOARD_WIDTH(); i++){
            for(int j = 0; j < b.getBOARD_HEIGHT(); j++){
                if(i == x_placement && j == y_placement){
                    return placeInArray;
                }
                placeInArray++;
            }
        }

        //TODO Replace with exception
        return -1;
    }


    public void removeTower(Tower t){

        try{
            towers.remove(t);
        }
        catch (NullPointerException e){
            System.out.println("not found tower");
        }

    }

    public void addMoney(int toAdd){
        System.out.println("before: " + money);
        money += toAdd;
        System.out.println("after: " + money);
    }




}
