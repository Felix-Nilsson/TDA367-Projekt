package Model.Enemy;

import Model.DamageType;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BaseEnemy implements Enemy {
    private int health;
    private int positionX;
    private int positionY;
    private final int movementSpeed;
    private final int magicResist;
    private final int armor;
    private final int maxHp;

    public enum Direction {NORTH,EAST,SOUTH,WEST}

    //TODO path ska finnas med som parameter i BaseEnemy. Då vet varje enemy hur de ska gå m.h.a. path, t.ex. EAST,EAST,SOUTH,SOUTH med 50 pixlar mellan varje.
    //TODO update ska t.ex. kolla: if(positionX % 50 == 25), turn(path.get(stepNr)), stepNr=stepNr+1
    //TODO OBS! för att modulo-beräkningen ska fungera måste movementSpeed vara väldigt låg. Hitta bättre sätt.
    private List <Direction> path;

    protected Direction direction;
    private List<Point> positionList; //Lista med alla pathens nodpositioner
    private int stepNr = 0;//används för att gå igenom path
    private boolean isDead;


    public BaseEnemy(int health, int movementSpeed, int magicResist, int armor, List<Direction> path, int startPos){
        this.health=health;
        this.maxHp = health;
        this.movementSpeed=movementSpeed;
        this.magicResist=magicResist;
        this.armor=armor;
        this.positionX=0;
        this.positionY=(startPos-1) * 40;
        this.path = path;
        isDead = false;
        //så länge enemy spawnas på 25,75... ska denna inte behövas
        this.direction=path.get(0);
        convertPathToCoordinates();
    }

    //Dessa metoder ska kallas varje gång model ska uppdateras
    @Override
    public void update(){
        followPath();
        move();
    }

    //movementspeed = pixels per millisecond
    public void move(){
        switch (direction) {
            case NORTH : positionY = positionY - movementSpeed; break;
            case EAST : positionX = positionX + movementSpeed; break;
            case SOUTH : positionY = positionY + movementSpeed; break;
            case WEST : positionX = positionX - movementSpeed; break;
        }
    }


    @Override
    public boolean isDead() {
        return isDead;
    }


    public int spawnTime() {
        return -1;
    }

    protected void turn(Direction dir){
        this.direction = dir;
    }



    //Fyller positionList
    private void convertPathToCoordinates(){

        positionList = new ArrayList<>();
        int nextX = positionX;
        int nextY = positionY;
        for (Direction d : path) {
            switch (d) {
                case NORTH : {
                    nextY = nextY - 40;
                    positionList.add(new Point(nextX, nextY));
                } break;
                case EAST : {
                    nextX = nextX + 40;
                    positionList.add(new Point(nextX, nextY));
                } break;
                case SOUTH : {
                    nextY = nextY + 40;
                    positionList.add(new Point(nextX, nextY));
                } break;
                case WEST : {
                    nextX = nextX - 40;
                    positionList.add(new Point(nextX, nextY));
                } break;
            }

        }



    }


//Ser till så att enemy är vänd åt rätt håll.

    public void followPath(){
        if (stepNr<path.size()){

            switch (direction){

                case EAST:
                    if (positionList.get(stepNr).x-positionX<=0){
                        turn(path.get(stepNr));
                        stepNr++;
                    }
                    break;
                case SOUTH:
                    if (positionList.get(stepNr).y-positionY<=0){
                        turn(path.get(stepNr));
                        stepNr++;
                    }
                    break;
                case WEST:
                    if (positionList.get(stepNr).x-positionX>=0){
                        turn(path.get(stepNr));
                        stepNr++;
                    }
                    break;
                case NORTH:
                    if (positionList.get(stepNr).y-positionY>=0){
                        turn(path.get(stepNr));
                        stepNr++;
                    }
            }
        }
        else {
        isDead = true;

        }


    }




    // ska antagligen ta in damage type (ad/ap)
    public void tookDamage(double damage, DamageType damageType){
        //TODO lägga in beräkningar beroende på damage type och armor/mr

        if (damageType == DamageType.MAGICAL){
            health = (int) (health-(damage*((100.0-magicResist)/100)));
        }
        else if (damageType == DamageType.PHYSICAL){
            health = (int) (health-damage*((100.0-armor)/100));
        }
        System.out.println(this + "Health: ");
        if (health<=0){
            isDead = true;
        }
    }

    public Image getImage() {
        return null;
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
    public int getHealth(){
        return health;
    }

    @Override
    public int getMaxHealth() {
        return maxHp;
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
