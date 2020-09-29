package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class BlueEnemy implements Enemy{

    private final BaseEnemy parent;
    private ImageView imageView;

    public BlueEnemy(int health, int movementSpeed, int magicResist, int armor, int positionX, int positionY, ArrayList<BaseEnemy.Direction> path){
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor, positionX, positionY, path);
        init();
    }

    @Override
    public void update(){
        //TODO ska eventuellt vara individuell för varje enemy
        parent.update();
        imageToPos();
    }

    private void init() {

        Image image = new Image((getClass().getClassLoader().getResourceAsStream("img/bluemonster1.png")));
        imageView = new ImageView(image);

        //Setting the position of the image
        imageToPos();

        //setting the fit height and width of the image view
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);

        //Setting the preserve ratio of the image view
        //imageView.setPreserveRatio(true);
        imageView.toFront();
    }
    private void imageToPos(){
        imageView.setX(parent.getPositionX());
        imageView.setY(parent.getPositionY());

        System.out.println("x = "+parent.getPositionX());
        System.out.println(" y = "+parent.getPositionY());
    }
    public ImageView getImageView(){
        return imageView;
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
