package Model;


<<<<<<< Updated upstream
=======
import Controller.Observer;
import Model.Towers.MageTower;
import Model.Towers.MageTowerFactory;
import Model.Towers.Tower;
import Model.Towers.TowerFactory;

import java.util.ArrayList;
>>>>>>> Stashed changes
import java.util.List;

public class Game implements Updatable{

    private final Difficulty difficulty;
    private final int mapNumber;
    private int health;
    private int money;
    private final Observable observable;
    private final UpdateModel updateModel;
    private final Board b;
<<<<<<< Updated upstream

=======
    private final WaveManager waveManager;
    private boolean running = false;
    public Thread gameLoopThread;
    private List<Enemy> enemies;

    private List<Tower> towers;

    private int round = 0;
>>>>>>> Stashed changes

    public Game (Difficulty difficulty, int mapNumber){
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        observable = new Observable();
        updateModel = new UpdateModel();
        b= new Board(mapNumber);
        startGame();

<<<<<<< Updated upstream
=======
        towers = new ArrayList();

    }
    public void startGame(){
        running = true;
>>>>>>> Stashed changes
        run();
    }
    private void run(){
        updateModel.notifyAllUpdatables();
    }



    private void startGame(){
        setValues();
        update();
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

    public void update(){

            observable.update();

    }
    public List<Cell> getBoard(){
        return b.getBoard();
    }
    public int getHealth() {
        return health;
    }
    public int getMoney() {
        return money;
    }

<<<<<<< Updated upstream
=======

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

    public <T extends Tower, TF extends TowerFactory> void updateArrayWithTower(int index, T tower, TF towerFactory){

        //TODO update cell to occupied
        MageTowerFactory mageTowerFactory = new MageTowerFactory();
        TowerFactory t = towerFactory;
        //check type of cell:

        MageTower mageTower =  mageTowerFactory.createTower((GroundCell)(getBoard().get(index)),updateModel);
        Tower tow = t.createTower((GroundCell)(getBoard().get(index)),updateModel);

        towers.add(tow);

        System.out.println("" + tow.getClass());
    }

    public List<Tower> getTowers(){
        //Maybe should give out a copy for immutability
        return towers;
    }


>>>>>>> Stashed changes
}
