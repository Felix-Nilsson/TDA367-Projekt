package Model.Towers;

import Model.DamageType;
import Model.Enemy.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile {
    private double vx;
    private double vy;
    private double posX;
    private double posY;
    private double enemyPosX;
    private double enemyPosY;
    //angle is in radians
    private double angle;
    private int vMultiplier=30;
    //private final ImageView imageView;
    private boolean exists;


    //Kan också testa med Enemy som arg istället. Blir lättare om projectiles inte kan missa eftersom man då har direkt tillgång till Enemy.
    //Om projectile inte är hitscan och tower har en Angle blir det lätt att implementera en riktig projektil (collision måste dock skapas då)
    public Projectile(double towerPosX, double towerPosY, double enemyPosX, double enemyPosY){
        this.posX=towerPosX;
        this.posY=towerPosY;
        this.enemyPosX=enemyPosX;
        this.enemyPosY=enemyPosY;
        /*
        this.enemyPosX=enemyPosX;
        this.enemyPosY=enemyPosY;
         */
        //this.angle=tower.getAngle();
        this.angle=angle;
        calculateVelocity();
        exists=true;
    }


    private void damageEnemy(Enemy enemy, DamageType damageType){
        //temporärt
        enemy.tookDamage(5,damageType);
    }
    private void disappearIfHit(){
        if (isColission()){

        }
    }

    private void calculateVelocity(){
        double distX = enemyPosX-posX;
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. angle reflekterar nu verkligheten.
        //i Projectile skapa finns det minus framför vy för att återställa detta igen
        double distY = -(enemyPosY-posY);
        this.angle = Math.atan2(distY, distX);
        vx = vMultiplier*Math.cos(angle);
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. vy blir nu korrekt
        vy = -(vMultiplier*Math.sin(angle));
    }
    public double getTargetPosX(){
        return enemyPosX;
    }
    public double getTargetPosY(){
        return enemyPosY;
    }
    public void move(){
        posX=posX+vx;
        posY=posY+vy;
        //System.out.println("posX= " + posX + ", posY= "+posY);
    }

    private boolean isColission(){
        //temporärt
        return false;
    }
    public boolean isExisting(){
        return exists;
    }

    public void update(){
        move();
        if(targetInFirstQuadrantWasHit() || targetInSecondQuadrantWasHit() || targetInThirdQuadrantWasHit() || targetInFourthQuadrantWasHit()){
            exists=false;

            //TODO borde kunna ta bort sig själv ur listan. Dock blir det ConcurrentModException om den är avkommenterad
            //updateModel.removeObserver(this);
        }
        disappearIfHit();
    }
    private boolean targetInFirstQuadrantWasHit(){
        return (vx>=0 && (enemyPosX-posX)<=0)  &&   (vy<=0 && (enemyPosY-posY)>=0);
    }
    private boolean targetInSecondQuadrantWasHit(){
        return (vx<=0 && (enemyPosX-posX)>=0)  &&   (vy<=0 && (enemyPosY-posY)>=0);
    }
    private boolean targetInThirdQuadrantWasHit(){
        return (vx<=0 && (enemyPosX-posX)>=0)  &&   (vy>=0 && (enemyPosY-posY)<=0);
    }
    private boolean targetInFourthQuadrantWasHit(){
        return (vx>=0 && (enemyPosX-posX)<=0)  &&   (vy>=0 && (enemyPosY-posY)<=0);
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
