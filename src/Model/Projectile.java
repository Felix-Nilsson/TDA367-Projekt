package Model;

import Model.Towers.Tower;
import javafx.scene.image.Image;

import static java.lang.StrictMath.PI;

public class Projectile implements Updatable{
    private double vx;
    private double vy;
    private double posX;
    private double posY;
    private double enemyPosX;
    private double enemyPosY;
    //angle is in radians
    private double angle;
    private int vMultiplier=10;
    Image image;

    //Kan också testa med Enemy som arg istället. Blir lättare om projectiles inte kan missa eftersom man då har direkt tillgång till Enemy.
    //Om projectile inte är hitscan och tower har en Angle blir det lätt att implementera en riktig projektil (collision måste dock skapas då)
    public Projectile(double towerPosX, double towerPosY, double angle){

        this.posX=towerPosX;
        this.posY=towerPosY;
        /*
        this.enemyPosX=enemyPosX;
        this.enemyPosY=enemyPosY;

         */

        //this.angle=tower.getAngle();
        this.angle=angle;
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
        /*
        //Om det finns en angle i Tower:
        setAngle(PI); //temporär metod för testning
        vx = vMultiplier*Math.cos(angle);
        vy = vMultiplier*Math.sin(angle);
        System.out.println("angle in degrees=" + Math.toDegrees(angle) + ", vx= " + vx +", vy =" +vy);

         */
/*
        //Om det inte finns:
        double distX = enemyPosX-posX;
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. theAngle blir nu korrekt
        double distY = -(enemyPosY-posY);
        double theAngle = Math.atan2(distY,distX);



        vx = vMultiplier*Math.cos(theAngle);
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. vy blir nu korrekt
        vy = -(vMultiplier*Math.sin(theAngle));
        System.out.println("calculated angle in degrees: " +Math.toDegrees(theAngle) + ", vx= "+vx + ", vy= "+vy);
        System.out.println("vx= "+vx);
        System.out.println("vy= "+vy);

 */
        vx = vMultiplier*Math.cos(angle);
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. vy blir nu korrekt
        vy = -(vMultiplier*Math.sin(angle));

    }
    public void move(){
        posX=posX+vx;
        posY=posY+vy;
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
