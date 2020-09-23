package Model;

import Model.Towers.ArcherTower;
import Model.Towers.BaseTower;
import Model.Towers.Tower;
import View.MapController;

public class Game implements Updatable{

    private MapController mapController;
    private ArcherTower archerTower;
    private String difficulty;
    private int mapNumber;
    private int health;
    private int money;
    private Observable observable;

    private static Game single_instance = null;


    public static Game getInstance(){
        if(single_instance == null){
            single_instance = new Game("easy", 1);
        }
        return single_instance;
    }

    private Game (String difficulty, int mapNumber){
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        startGame();
    }

    private void startGame(){

        observable = new Observable();

        Board b= new Board(mapNumber);
        mapController = new MapController(this,b.getBoard(),observable);


        setValues();
        update();
    }
    //sets values of health and money
    private void setValues(){
        if(difficulty.equals("easy")){
            setHealth(100);
            setMoney(200);
        }
        else if(difficulty.equals("medium")){
            setMoney(150);
            setHealth(50);
        }
        else if(difficulty.equals("hard")){
            setHealth(10);
            setMoney(80);
        }
    }
    public MapController getMapController(){
        return mapController;
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
        //mapController.update(); //not good change later
        //updatable.update();

    }
    public void startRound(){

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDifficulty() {return difficulty;}
    
    public void setDifficulty(String difficulty) {this.difficulty = difficulty;}

    public int getMapNumber(){return mapNumber;}

    public void setMapNumber(int mapNumber){this.mapNumber = mapNumber;}
}
