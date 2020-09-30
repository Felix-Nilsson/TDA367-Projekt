package Model;


import Controller.Observer;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game implements Updatable{

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
    private List<Enemy> enemies;
    private int round = 0;

    public Game (Difficulty difficulty, int mapNumber){
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        observable = new Observable();
        updateModel = new UpdateModel();
        waveManager = new WaveManager(difficulty);
        b= new Board(mapNumber);
        setValues();

    }
    public void startGame(){
        running = true;
        run();
    }
    private void run(){

        gameLoopThread = new Thread(()-> {
            int seconds = 0;
           while(running){

               update();
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               System.out.println("running for: "+ seconds + " seconds");
               seconds++;
           }
        });
        gameLoopThread.setDaemon(true);
        gameLoopThread.start();


    }
    public void nextRound(){
        enemies = waveManager.createWave(round);
        round++;
    }
    private void delay(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public boolean addObserver(final Observer observer){
        return this.observable.addObserver(observer);
    }
    public boolean removeObserver(final Observer observer){
        return this.observable.removeObserver(observer);
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

    public Board getTmpBoard(){
        return b.getTmpBoard();
    }
    public int getMapNumber(){return mapNumber;}

    public int getHealth() {
        return health;
    }
    public int getMoney() {
        return money;
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
        return 0;
    }

    public boolean isCellOccupied(int index){
        return b.isCellOccupied(index);
    }

    public void setCellOccupied(int index){
        b.setCellOccupied(index);
    }

    public void updateArrayWithTower(int index){
        //TODO update cell to occupied
    }


}
