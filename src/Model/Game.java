package Model;

import Model.Towers.ArcherTower;
import java.util.ArrayList;

public class Game implements Updatable{

    private ArcherTower archerTower;
    private String difficulty;
    private int mapNumber;
    private int health;
    private int money;
    private  final Observable observable;
    private final UpdateModel updateModel;
    private Board b;

    private static Game single_instance = null;

    //TODO remove singleton
    public static Game getInstance(){
        if(single_instance == null){
            single_instance = new Game("easy", 1); //temp, change later
        }
        return single_instance;
    }

    private Game (String difficulty, int mapNumber){
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        observable = new Observable();
        updateModel = new UpdateModel();
        startGame();

        run();
    }
    private void run(){
        updateModel.notifyAllUpdatables();
    }


    private void startGame(){

        b= new Board(mapNumber);
        setValues();
        update();
    }


    //sets values of health and money
    private void setValues(){
        switch (difficulty) {
            case "easy":
                setHealth(100);
                setMoney(200);
                break;
            case "medium":
                setMoney(150);
                setHealth(50);
                break;
            case "hard":
                setHealth(10);
                setMoney(80);
                break;
        }
    }


    public void moveTower(String tower){
        if(tower.equals("archer")){
            if(money >= archerTower.getPrice()){
                System.out.println("hi");
            }
        }
    }
    public void update(){

            observable.notifyAllObservers();

    }
    public void startRound(){}

    public Observable getObservable(){
        return observable;
    }
    public ArrayList<Cell> getBoard(){
        return b.getBoard();
    }
    public int getMapNumber(){return mapNumber;}
    public int getHealth() {
        return health;
    }
    public int getMoney() {
        return money;
    }
    public String getDifficulty() {return difficulty;}

    public void setMoney(int money) {
        this.money = money;
    }
    public void setDifficulty(String difficulty) {this.difficulty = difficulty;}
    public void setHealth(int health) {
        this.health = health;
    }
    public void setMapNumber(int mapNumber){this.mapNumber = mapNumber;}


    public int getArrayIndex(int x_placement, int y_placement){
        int placeInArray = 0;
        System.out.println("x" + x_placement);
        System.out.println("y" + y_placement);
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

    public void updateArrayWithTower(int index){

    }


}
