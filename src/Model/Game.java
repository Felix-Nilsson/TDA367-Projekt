package Model;


import Controller.Observer;
import Model.Towers.MageTower;
import Model.Towers.MageTowerFactory;
import Model.Towers.Tower;
import Model.Towers.TowerFactory;

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
    private List<Enemy> enemiesInWave;
    int round = 1;

    private List<Tower> towers;

    public Game (Difficulty difficulty, int mapNumber){
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        observable = new Observable();
        updateModel = new UpdateModel();


        b= new Board(mapNumber);
        this.enemyPath = b.getPath();
        waveManager = new WaveManager(difficulty,enemyPath);

        setValues();

        towers = new ArrayList();

    }

    public List<Enemy> getEnemiesInWave() {
        return enemiesInWave;
    }

    public void startGame(){
        running = true;
        run();
    }
    public void createWave(){
        enemiesInWave  = waveManager.createWave(round);

    }
    public void putEnemyInUpdateModel(){
        for(Enemy e : enemiesInWave){
            updateModel.add(e);
            int i = 0;
            while(i < 10){
                delay();
                update();
                i++;
            }
        }
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
                    Thread.sleep(100);
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

    public void update(){
        observable.update();
        updateModel.update();
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
    public int getMapNumber(){return mapNumber;}
    public int getHealth() { return health; }
    public int getMoney() { return money; }
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
        return 0;
    }

    public boolean isCellOccupied(int index){
        return b.isCellOccupied(index);
    }

    public void setCellOccupied(int index){
        b.setCellOccupied(index);
    }

    public void updateArrayWithTower(int index, TowerFactory towerFactory){
        //TODO update cell to occupied
        getBoard().get(index).setOccupiedTrue();

        Tower t = towerFactory.createTower((GroundCell)(getBoard().get(index)),updateModel);

        towers.add(t);
        System.out.println(t.getClass());
    }


}
