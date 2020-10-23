package main.java.Model.Enemy;

import main.java.Model.DamageType;
import main.java.Model.Direction;

import java.util.List;

/**
 * Has a reference to a BaseEnemy that can be used for the delegation of methods.
 * At the moment, there is nothing unique about these low-level enemies, but if more are added later on they have the possibility to have more sophisticated behaviours
 * @author Simon Larsson
 */
public class RedEnemy implements Enemy {

    private final BaseEnemy parent;

    public RedEnemy(int health, int movementSpeed, int magicResist, int armor, List<Direction> path, int startPos){
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor,path, startPos);
    }

    /**
     * updates the enemy
     */
    @Override
    public void update(){
        parent.update();
    }

    @Override
    public void move() {
        parent.move();
    }
    @Override
    public void followPath(){
        parent.followPath();
    }
    @Override
    public void tookDamage(double damage, DamageType damageType){
        parent.tookDamage(damage,damageType);
    }

    @Override
    public int getId() {
        return 2;
    }


    @Override
    public boolean isKilled() {
        return parent.isKilled();
    }
    @Override
    public boolean isOut() {
        return parent.isOut();
    }

    @Override
    public int spawnTime() {
        return parent.spawnTime();
    }
    @Override
    public int getPositionX(){
        return parent.getPositionX();
    }
    @Override
    public int getPositionY(){
        return parent.getPositionY();
    }
    @Override
    public int getHealth(){
        return parent.getHealth();
    }
    @Override
    public int getMaxHealth() {
        return parent.getMaxHealth();
    }

}
