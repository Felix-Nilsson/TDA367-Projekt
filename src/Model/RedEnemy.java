package Model;

import javafx.scene.image.Image;

import java.util.List;

public class RedEnemy implements Enemy{
    private final BaseEnemy parent;
    //TODO Finns bara här för att testa. Denna ska bort senare eftersom View-delar inte ska finnas i Model.
    private Image image;

    public RedEnemy(int health, int movementSpeed, int magicResist, int armor, List<BaseEnemy.Direction> path, int startPos){
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor,path, startPos);
        this.image = new Image((getClass().getClassLoader().getResourceAsStream("img/red_Monster.png")));
    }

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
    public void tookDamage(int damage){
        parent.tookDamage(damage);
    }
    protected void turn(BaseEnemy.Direction dir){
        parent.direction=dir;
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
    public boolean isDead() {
        return parent.isDead();
    }

    public BaseEnemy.Direction getDirection(){
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
