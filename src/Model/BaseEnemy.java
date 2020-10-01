package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private List <Direction> path;

    protected Direction direction;
    private ImageView imageView;
    private List<Point> positionList; //Lista med alla pathens nodpositioner

    //används för att gå igenom path
    private int stepNr = 0;

    public BaseEnemy(int health, int movementSpeed, int magicResist, int armor, int positionX, int positionY,List<Direction> path){
        this.health=health;
        this.movementSpeed=movementSpeed;
        this.magicResist=magicResist;
        this.armor=armor;
        this.positionX=positionX;
        this.positionY=positionY;
        this.path = path;

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

    public void move(){
        switch (direction) {
            case NORTH : positionY = positionY - movementSpeed; break;
            case EAST : positionX = positionX + movementSpeed; break;
            case SOUTH : positionY = positionY + movementSpeed; break;
            case WEST : positionX = positionX - movementSpeed; break;
        }
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
                    //System.out.println("position för path.get(" + counter + "): ger x=" + positionList.get(counter).x + ", y=" + positionList.get(counter).y);
                } break;
                case EAST : {
                    nextX = nextX + 40;
                    positionList.add(new Point(nextX, nextY));
                    //System.out.println("position för path.get(" + counter + "): ger x=" + positionList.get(counter).x + ", y=" + positionList.get(counter).y);
                } break;
                case SOUTH : {
                    nextY = nextY + 40;
                    positionList.add(new Point(nextX, nextY));
                    //System.out.println("position för path.get(" + counter + "): ger x=" + positionList.get(counter).x + ", y=" + positionList.get(counter).y);
                } break;
                case WEST : {
                    nextX = nextX - 40;
                    positionList.add(new Point(nextX, nextY));
                    //System.out.println("position för path.get(" + counter + "): ger x=" + positionList.get(counter).x + ", y=" + positionList.get(counter).y);
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
            System.out.println("out of bounds. should be removed from the world");
        }


    }



    // ska antagligen ta in damage type (ad/ap)
    public void tookDamage(int damage){
        //TODO lägga in beräkningar beroende på damage type och armor/mr
        health=health-damage;

        if (health<=0){
            //TODO delete this object
        }
    }


    public Image getImage() {
        return null;
    }


    public ImageView getImageView() {
        return imageView;
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
