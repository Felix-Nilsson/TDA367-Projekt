package Model.Enemy;

import Model.DamageType;
import Model.Direction;
import javafx.scene.image.Image;
import java.util.List;

/**
 * has a reference to a BaseEnemy that can be used for the delegation of methods.
 * @author Simon Larsson
 */
public class RedEnemy implements Enemy {
    private final BaseEnemy parent;
    //TODO Finns bara här för att testa. Denna ska bort senare eftersom View-delar inte ska finnas i Model.
    private Image image;

    public RedEnemy(int health, int movementSpeed, int magicResist, int armor, List<Direction> path, int startPos){
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor,path, startPos);

        this.image = new Image((getClass().getClassLoader().getResourceAsStream("View/img/red_Monster.png")));
    }

    /**
     * updates the enemy
     */
    @Override
    public void update(){
        //TODO ska eventuellt vara individuell för varje enemy
        parent.update();

    }


    //ska kanske också bort senare
    @Override
    public Image getImage(){
        return image;
    }


    @Override
    public void followPath(){
        parent.followPath();
    }
    @Override
    public void tookDamage(double damage, DamageType damageType){
        parent.tookDamage(damage,damageType);
    }

    public int getPositionX(){
        return parent.getPositionX();
    }
    public int getPositionY(){
        return parent.getPositionY();
    }

    @Override
    public void move() {
        parent.move();
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

    public Direction getDirection(){
        return parent.direction;
    }
    public int getHealth(){
        return parent.getHealth();
    }

    @Override
    public int getMaxHealth() {
        return parent.getMaxHealth();
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
