package Model.Enemy;

import Model.DamageType;
import Model.Direction;
import javafx.scene.image.Image;
import java.util.List;
/**
 * Has a reference to a BaseEnemy that can be used for the delegation of methods.
 * At the moment, there is nothing unique about these low-level enemies, but if more are added later on they have the possibility to have more sophisticated behaviours
 * @author Simon Larsson
 */
public class BlueEnemy implements Enemy {

    private final BaseEnemy parent;
    //TODO Finns bara här för att testa. Denna ska bort senare eftersom View-delar inte ska finnas i Model.
    private Image image;

    public BlueEnemy(int health, int movementSpeed, int magicResist, int armor, List<Direction> path, int startPos){
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor, path, startPos);
        this.image = new Image((getClass().getClassLoader().getResourceAsStream("View/img/blue_Monster.png")));
    }

    /**
     * updates the enemy
     */
    @Override
    public void update(){
        //TODO ska eventuellt vara individuell för varje enemy
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
        parent.tookDamage(damage, damageType);
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
    //ska också tas bort senare
    @Override
    public Image getImage(){
        return image;
    }



}
