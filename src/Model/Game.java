package Model;


import java.util.List;

public class Game implements Updatable{

    private final Difficulty difficulty;
    private final int mapNumber;
    private int health;
    private int money;

    private final Observable observable;
    private final UpdateModel updateModel;
    private final Board b;




    public Game (Difficulty difficulty, int mapNumber){
        this.difficulty = difficulty;
        this.mapNumber = mapNumber;
        observable = new Observable();
        updateModel = new UpdateModel();
        b= new Board(mapNumber);
        startGame();

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

}
