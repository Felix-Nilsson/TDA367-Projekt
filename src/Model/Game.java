package Model;


import Controller.Observer;
import Model.Towers.MageTower;
import Model.Towers.MageTowerFactory;
import Model.Towers.Tower;
import Model.Towers.TowerFactory;
import Model.Cell;

import java.util.Collections;

import java.util.ArrayList;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Game implements Updatable {

    private final Difficulty difficulty;
    private final int mapNumber;
    private int health;
    private int money;
    private final Observable observable;
    private final UpdateModel updateModel;
    private final Board b;
    private final WaveManager waveManager;
    private boolean running = false;
    public Thread gameLoopThread;

    private Updatable updatable;
    private final List<BaseEnemy.Direction> enemyPath;

    int round = 1;
    private List<Enemy> enemiesInWave;
    private List<Tower> towers;

    public Game (Difficulty difficulty, int mapNumber){
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        observable = new Observable();
        updateModel = new UpdateModel();


        b= new Board(mapNumber);
        this.enemyPath = b.getPath();
        waveManager = new WaveManager(difficulty,enemyPath,b.getStartPos(b.getMap()));

        setValues();

        towers = new ArrayList<>();

    }

    public void loseHP(){
        health--;
    }

    public List<Enemy> getEnemiesInWave() {
        return enemiesInWave;
    }

    public void startGame(){
        running = true;
        run();
    }
    public void createWave(){
        System.out.println("wave is created");
        enemiesInWave  = waveManager.createWave(round);
    }

    public interface Cancelable extends Runnable {
        public void cancel();
    }

    public Cancelable putEnemyInUpdateModel(){
        Cancelable enemyAdder = new Cancelable() {
            private boolean canceled;

            public void cancel() {
                canceled = true;
            }
            @Override
            public void run() {
              List<Enemy> localEnemies = Collections.unmodifiableList(enemiesInWave);
              System.out.println("Number of enemies: " + localEnemies.size());
              for(Enemy e : localEnemies){
                  if(canceled) {
                      break;
                  }
                  updateModel.add(e);
                    int i = 0;
                    while(i < 10){
                        delay();
                        update();
                        i++;
                    }
                }
            }
        };
        Thread enemyThread = new Thread(enemyAdder);
        enemyThread.start();
        return enemyAdder;
        // return enemyThread; // Fördel: kan avbrytas direkt via v.interrupt();
    }

    private void delay(){
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void pauseGame(){

    }
    public void nextRound(){
        //waveManager.createWave(round);
        round++;
    }

    private void run() {
        gameLoopThread = new Thread(() -> {
            while (running) {
                update();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        gameLoopThread.setDaemon(true);
        gameLoopThread.start();

    }




    public boolean addObserver(final Observer observer){
        return this.observable.addObserver(observer);
    }

    private void checksRadius(){
        if (towers.size()>0 && enemiesInWave.size()>0){
            for (Tower t : towers){
                //TODO if (t.cooldown == false)
                for (Enemy e : enemiesInWave){
                    t.checkRadius(e.getPositionX(),e.getPositionY());
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
                        // Om torn är hitscan blir det: e.tookDamage()
                    }

                     */


                }
            }
        }


    }

    public void update(){
        observable.update();
        updateModel.update();
        checksRadius();

    }
    //sets values of health and money
    private void setValues(){
        switch (difficulty) {
            case EASY:
                this.health = 100;
                this.money = 200;
                break;
            case MEDIUM:
                this.health = 50;
                this.money = 150;
                break;
            case HARD:
                this.health = 10;
                this.money = 80;
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

    public void updateArrayWithTower(int index, TowerFactory towerFactory){
        //TODO update cell to occupied
        setCellOccupied(index);
        Tower t = towerFactory.createTower(getBoard().get(index),updateModel);
        towers.add(t);
        System.out.println(towers);
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
