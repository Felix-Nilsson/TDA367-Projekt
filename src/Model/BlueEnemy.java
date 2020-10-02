package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BlueEnemy implements Enemy{

    private final BaseEnemy parent;
    //TODO Finns bara här för att testa. Denna ska bort senare eftersom View-delar inte ska finnas i Model.
    private Image image;

    public BlueEnemy(int health, int movementSpeed, int magicResist, int armor, int positionX, int positionY, List<BaseEnemy.Direction> path){
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor, positionX, positionY,path);
        this.image = new Image((getClass().getClassLoader().getResourceAsStream("img/bluemonster1.png")));


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
