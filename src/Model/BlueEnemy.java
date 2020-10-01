package Model;

import java.util.ArrayList;

public class BlueEnemy implements Enemy{

    private final BaseEnemy parent;

    public BlueEnemy(int health, int movementSpeed, int magicResist, int armor, int positionX, int positionY, ArrayList<BaseEnemy.Direction> path){
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor, positionX, positionY, path);
    }
    @Override
    public void update(){
        //TODO ska eventuellt vara individuell för varje enemy
        parent.update();
    }
    @Override
    public void followPath(){
        parent.followPath();
    }
    @Override
    public void tookDamage(int damage){
        parent.tookDamage(damage);
    }

    protected void turnNORTH(){
        parent.turnNORTH();
    }
    protected void turnEAST(){
        parent.turnEAST();
    }
    protected void turnSOUTH(){
        parent.turnSOUTH();
    }
    protected void turnWEST(){
        parent.turnWEST();
    }

    public int getPositionX(){
        return parent.getPositionX();
    }
    public int getPositionY(){
        return parent.getPositionY();
    }
    protected BaseEnemy.Direction getDirection(){
        return parent.direction;
    }
    protected int getHealth(){
        return parent.getHealth();
    }
    protected int getMovementSpeed(){
        return parent.getMovementSpeed();
    }
    protected int getMagicResist(){
        return parent.getMagicResist();
    }
    protected int getArmor(){
        return parent.getArmor();
    }

}
