package Model;

import java.util.ArrayList;

public class BaseEnemy implements Enemy{
    private int health;
    private int movementSpeed;
    private int magicResist;
    private int armor;
    private int positionX;
    private int positionY;

    public enum Direction {NORTH,EAST,SOUTH,WEST}

    //TODO path ska finnas med som parameter i BaseEnemy. Då vet varje enemy hur de ska gå m.h.a. path, t.ex. EAST,EAST,SOUTH,SOUTH med 50 pixlar mellan varje.
    //TODO update ska t.ex. kolla: if(positionX % 50 == 25), turn(path.get(stepNr)), stepNr=stepNr+1
    //TODO OBS! för att modulo-beräkningen ska fungera måste movementSpeed vara väldigt låg. Hitta bättre sätt.
    private ArrayList <Direction> path;

    protected Direction direction;

    public BaseEnemy(int health, int movementSpeed, int magicResist, int armor, int positionX, int positionY, ArrayList<BaseEnemy.Direction> path){
        this.health=health;
        this.movementSpeed=movementSpeed;
        this.magicResist=magicResist;
        this.armor=armor;
        this.positionX=positionX;
        this.positionY=positionY;
        this.path=path;
    }
    @Override
    public void update(){
        followPath();
        move();

    }

    private void move(){
        switch (direction) {
            case NORTH -> positionY = positionY - movementSpeed;
            case EAST -> positionX = positionX + movementSpeed;
            case SOUTH -> positionY = positionY + movementSpeed;
            case WEST -> positionX = positionX - movementSpeed;
        }
    }

    protected void turnNORTH(){
        this.direction=Direction.NORTH;
    }
    protected void turnEAST(){
        this.direction=Direction.EAST;
    }
    protected void turnSOUTH(){
        this.direction=Direction.SOUTH;
    }
    protected void turnWEST(){
        this.direction=Direction.WEST;
    }
    protected void turn(Direction dir){
        this.direction=dir;
    }

    private int stepNr=0;

    @Override
    public void followPath(){
        if(movementSpeed<=1){
            if(positionX%50==25 && positionY%50==25 && stepNr<path.size()){
                turn(path.get(stepNr));
                ++stepNr;
            }
        }
        /*
        else if (position)
        turn(path.get(stepNr));

         */

    }

    @Override
    // ska antagligen ta in damage type (ad/ap)
    public void tookDamage(int damage){
        //TODO lägga in beräkningar beroende på damage type och armor/mr
        health=health-damage;

        if (health<=0){
            //TODO delete this object
        }
    }
    public int getPositionX(){
        return positionX;
    }
    public int getPositionY(){
        return positionY;
    }
    protected Direction getDirection(){
        return direction;
    }
    protected int getHealth(){
        return health;
    }
    protected int getMovementSpeed(){
        return movementSpeed;
    }
    protected int getMagicResist(){
        return magicResist;
    }
    protected int getArmor(){
        return armor;
    }

}
