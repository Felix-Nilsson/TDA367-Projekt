package main.java.Model.Enemy;

import main.java.Model.DamageType;
import main.java.Model.Direction;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The BaseEnemy functions as a parent for low-level enemies such as RedEnemy
 * Its function is similar to that of a superclass
 * @author Simon Larsson, Oscar Forsyth, Felix Nilsson
 */
public class BaseEnemy implements Enemy {
    private int health;
    private int positionX;
    private int positionY;
    private final int movementSpeed;
    private final int magicResist;
    private final int armor;
    private final int maxHp;

    /**
     * this is the path that the enemy will follow, for example: EAST,EAST,SOUTH
     */
    private final List <Direction> path;

    /**
     * the current Direction of the enemy
     */
    protected Direction direction;

    /**
     * contains Points of all nodes the enemy will walk on, for example: (40,40), (80,40), (120,40), (120,80)
     */
    private List<Point> positionList; //Lista med alla pathens nodpositioner
    private int stepNr = 0;//används för att gå igenom path
    private boolean isKilled = false;
    private boolean isOut = false;
    /**
     * @param health when hp<0 enemy is removed from the game
     * @param movementSpeed how many pixels enemy moves each update
     * @param magicResist affects how much Magic damage enemy takes
     * @param armor affects how much Physical damage enemy takes
     * @param path the Path is follows
     * @param startPos vertical index of its start position
     */
    public BaseEnemy(int health, int movementSpeed, int magicResist, int armor, List<Direction> path, int startPos){
        this.health=health;
        this.maxHp = health;
        this.movementSpeed=movementSpeed;
        this.magicResist=magicResist;
        this.armor=armor;
        this.positionX=0;
        this.positionY=(startPos-1) * 40;
        this.path = path;
        this.direction=path.get(0);
        convertPathToCoordinates();
    }


    /**
     * is called in the main loop in Game. Currently only updates enemy's position
     * @author Simon Larsson
     */

    public void update(){
        move();
    }

    /**
     * updates enemy's position based on its current direction.
     * @author Simon Larsson
     */
    public void move(){
        followPath();
        switch (direction) {
            case NORTH : positionY = positionY - movementSpeed; break;
            case EAST : positionX = positionX + movementSpeed; break;
            case SOUTH : positionY = positionY + movementSpeed; break;
            case WEST : positionX = positionX - movementSpeed; break;
        }
    }

    /**
     * @author Oscar Forsyth
     * @return if enemy has been killed
     */
    public boolean isKilled() {
        return isKilled;
    }
    /**
     * @author Oscar Forsyth
     * @return if enemy has made it through the whole path
     */
    public boolean isOut() {
        return isOut;
    }
    /**
     * @author Oscar Forsyth
     * modifies spawnTime based on the objects movementSpeed so that faster enemies dont spawn in a cluster
     */
    public int spawnTime() {
        if(10 - this.movementSpeed > 0){
            return 10 - this.movementSpeed;
        }
        return 10;
    }

    /**
     * can make enemy turn, for example call turn(Direction.EAST) if enemy should change direction to EAST
     * @param dir sets the current direction to dir
     * @author Simon Larsson
     */
    private void turn(Direction dir){
        this.direction = dir;
    }



    //Fyller positionList

    /**
     * fills positionList based on the path
     * for example if path={EAST,EAST}, the startposition is (0,0), then positionList={(40,0),(80,0)}
     * these Points are the nodes where the enemy might change direction
     * See javadoc for positionList variable for more information
     * @author Simon Larsson
     */
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

    /**
     * makes sure that the enemy is facing the correct direction
     * @author Simon Larsson
     */
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
        isOut = true;
        }
    }


    /**
     * calculates how much damage it takes
     * @param damage amount of flat damage
     * @param damageType the type of damage
     * @author Felix Nilsson
     */
    public void tookDamage(double damage, DamageType damageType){
        if (damageType == DamageType.MAGICAL){
            health = (int) (health-(damage*((100.0-magicResist)/100)));
        }
        else if (damageType == DamageType.PHYSICAL){
            health = (int) (health-damage*((100.0-armor)/100));
        }
        System.out.println(this + "Health: ");
        if (health<=0){
            isKilled = true;
        }
    }

    @Override
    public int getId() {
        return -1;
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

    public int getHealth(){
        return health;
    }

    @Override
    public int getMaxHealth() {
        return maxHp;
    }



}
