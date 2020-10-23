package main.java.Model.Towers;

/**
 * Projectiles are fired by towers but at the moment it is hitscan so projectiles do not have any real purpose since there is no class that checks collision between objects.
 * Currently it is only a single class. In the future, it might develop into a strategy pattern or standard composition to account for different behaviours for different projectiles.
 */
public class Projectile {
    /**
     * Horizontal velocity
     */
    private double vx;
    /**
     * Vertical velocity
     */
    private double vy;
    private double posX;
    private double posY;
    private final double enemyPosX;
    private final double enemyPosY;
    private boolean exists;

    public Projectile(double towerPosX, double towerPosY, double enemyPosX, double enemyPosY){
        this.posX=towerPosX;
        this.posY=towerPosY;
        this.enemyPosX=enemyPosX + 15;
        this.enemyPosY=enemyPosY + 15;
        calculateVelocity();
        exists=true;
    }

    private void calculateVelocity(){
        double distX = enemyPosX-posX ;
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. angle reflekterar nu verkligheten.
        //i Projectile skapa finns det minus framför vy för att återställa detta igen
        double distY = -(enemyPosY-posY );
        //angle is in radians
        double angle = Math.atan2(distY, distX);
        int vMultiplier = 30;
        vx = vMultiplier *Math.cos(angle);
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. vy blir nu korrekt
        vy = -(vMultiplier *Math.sin(angle));
    }

    public void move(){
        posX=posX+vx;
        posY=posY+vy;
    }


    public boolean isExisting(){
        return exists;
    }

    /**
     * moves the projectile and checks if it has reached the position of the enemy when tower fired the projectile
     */
    public void update(){
        move();
        if(targetInFirstQuadrantWasHit() || targetInSecondQuadrantWasHit() || targetInThirdQuadrantWasHit() || targetInFourthQuadrantWasHit()){
            exists=false;
        }
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

    public double getPosX(){
        return posX;
    }
    public double getPosY(){
        return posY;
    }

}
