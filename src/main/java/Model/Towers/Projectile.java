package main.java.Model.Towers;

public class Projectile {
    private double vx;
    private double vy;
    private double posX;
    private double posY;
    private final double enemyPosX;
    private final double enemyPosY;
    private boolean exists;


    //Kan också testa med Enemy som arg istället. Blir lättare om projectiles inte kan missa eftersom man då har direkt tillgång till Enemy.
    //Om projectile inte är hitscan och tower har en Angle blir det lätt att implementera en riktig projektil (collision måste dock skapas då)
    public Projectile(double towerPosX, double towerPosY, double enemyPosX, double enemyPosY){
        this.posX=towerPosX;
        this.posY=towerPosY;
        this.enemyPosX=enemyPosX;
        this.enemyPosY=enemyPosY;
        calculateVelocity();
        exists=true;
    }


    private void calculateVelocity(){
        double distX = enemyPosX-posX;
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. angle reflekterar nu verkligheten.
        //i Projectile skapa finns det minus framför vy för att återställa detta igen
        double distY = -(enemyPosY-posY);
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
