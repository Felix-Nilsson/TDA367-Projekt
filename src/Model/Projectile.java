package Model;

import Model.Towers.Tower;
import javafx.scene.image.Image;

public class Projectile implements Updatable{
    private double vx;
    private double vy;
    private double posX;
    private double posY;
    private double enemyPosX;
    private double enemyPosY;
    private int angle;
    private int vMultiplier=10;
    Image image;

    //Kan också testa med Enemy som arg istället. Blir lättare om projectiles inte kan missa eftersom man då har direkt tillgång till Enemy.
    //Om projectile inte är hitscan och tower har en Angle blir det lätt att implementera en riktig projektil (collision måste dock skapas då)
    public Projectile(double towerPosX, double towerPosY, int enemyPosX, int enemyPosY){

        this.posX=towerPosX;
        this.posY=towerPosY;
        this.enemyPosX=enemyPosX;
        this.enemyPosY=enemyPosY;

        //this.angle=tower.getAngle();
        calculateVelocity();

        this.image = new Image((getClass().getClassLoader().getResourceAsStream("img/bluemonster1.png")));

    }
    private void damageEnemy(Enemy enemy){
        //temporärt
        enemy.tookDamage(5);
    }
    private void disappearIfHit(){
        if (isColission()){

        }
    }
    private void createImage(){

    }
    private void imageToPos(){

    }
    private void calculateVelocity(){
        //Om det finns en angle i Tower:
        vx = vMultiplier*Math.cos(angle);
        vy = vMultiplier*Math.sin(angle);

        //Om det inte finns:
        double distX = enemyPosX-posX;
        double distY = enemyPosY-posY;
        double theAngle = Math.atan2(distY,distX);
        vx = vMultiplier*Math.cos(theAngle);
        vy = vMultiplier*Math.sin(theAngle);
    }
    private void move(){
        posX=posX+vx;
        posY=posY+vy;
    }

    private boolean isColission(){
        //temporärt
        return false;
    }

    public void update(){
        move();
        disappearIfHit();
    }

}
