package Model.Towers;

import Model.Enemy.Enemy;
import Model.Updatable;
import Model.UpdateModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class Projectile implements Updatable {
    private double vx;
    private double vy;
    private double posX;
    private double posY;
    private double enemyPosX;
    private double enemyPosY;
    //angle is in radians
    private double angle;
    private int vMultiplier=30;
    private final ImageView imageView;

    //Kan också testa med Enemy som arg istället. Blir lättare om projectiles inte kan missa eftersom man då har direkt tillgång till Enemy.
    //Om projectile inte är hitscan och tower har en Angle blir det lätt att implementera en riktig projektil (collision måste dock skapas då)
    public Projectile(double towerPosX, double towerPosY, double angle, UpdateModel updateModel){
        System.out.println("Projectile was created");
        this.posX=towerPosX;
        this.posY=towerPosY;
        /*
        this.enemyPosX=enemyPosX;
        this.enemyPosY=enemyPosY;
         */
        //this.angle=tower.getAngle();
        this.angle=angle;
        calculateVelocity();

        Image image = new Image((getClass().getClassLoader().getResourceAsStream("img/pokeBall.png")));
        imageView = new ImageView(image);
        fixImage(imageView);
        //gameBoardAnchorPane.getChildren().add(imageView);
        updateModel.add(this);

    }
    public ImageView getImageView(){
        return imageView;
    }
    private void fixImage(ImageView img){
        img.setX(posX);
        img.setY(posY);
        img.setFitHeight(25);
        img.setFitWidth(25);
        img.setPreserveRatio(true);
        img.toFront();
    }
    private void damageEnemy(Enemy enemy){
        //temporärt
        enemy.tookDamage(5);
    }
    private void disappearIfHit(){
        if (isColission()){

        }
    }

    private void calculateVelocity(){
        vx = vMultiplier*Math.cos(angle);
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. vy blir nu korrekt
        vy = -(vMultiplier*Math.sin(angle));

    }
    public void move(){
        posX=posX+vx;
        posY=posY+vy;
        System.out.println("posX= " + posX + ", posY= "+posY);
    }

    private boolean isColission(){
        //temporärt
        return false;
    }
    @Override
    public void update(){
        move();
        disappearIfHit();
    }
    public double getVx(){
        return vx;
    }
    public double getVy(){
        return vy;
    }
    public double getPosX(){
        return posX;
    }
    public double getPosY(){
        return posY;
    }
    //temporär. Används för testning
    private void setAngle(double angle){
        this.angle=angle;
    }

}
