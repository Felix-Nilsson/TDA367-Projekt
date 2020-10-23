package main.java.Model.Towers;

import main.java.Model.Cell.Cell;

import main.java.Model.DamageType;
import main.java.Model.Enemy.Enemy;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The BaseTower holds the standard implementation for methods declared in the Tower interface
 */
public class BaseTower implements Tower {
    private final Cell position;
    private int physicalDmg;
    private int magicDmg;
    private final int price;

    private final int range;

    private double attackSpeed;
    private double angle;
    private final int posX;
    private final int posY;
    private final int leftUpgradeCost;
    private final int rightUpgradeCost;

    private Projectile currentProjectile;
    private int enemyPosX;
    private int enemyPosY;
    private double currentCooldown;
    private final int timerDelayInMilliseconds;
    private boolean isReadyToFire;
    private Timer timer;
    private boolean timerIsRunning;

    private Targeting target;

    public BaseTower(Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed, int leftUpgradeCost, int rightUpgradeCost) {
        this.position = position;
        //längst upp till vänster är (25,15). Varje cell är 40 pixlar
        posX = position.getX()*40 +25;
        posY = position.getY()*40 +15;
        this.physicalDmg = physicalDmg;
        this.magicDmg = magicDmg;
        this.price = price;
        this.range = range;
        this.attackSpeed = attackSpeed;
        this.leftUpgradeCost = leftUpgradeCost;
        this.rightUpgradeCost = rightUpgradeCost;

        //Tower ska kunna attackera direkt
        currentCooldown=0;
        isReadyToFire=true;


        timerIsRunning=false;

        timerDelayInMilliseconds=100;



        //Default is closest
        target = Targeting.FIRST;

        startTimer();


    }
    @Override
    public void stopTimer(){
        if(timerIsRunning){
            timer.cancel();
            timer.purge();
            timerIsRunning=false;
        }

    }
    @Override
    public void startTimer(){
        if(!timerIsRunning){
            TimerTask timerTask = new TimerTask(){
                @Override
                public void run() {
                    checkCooldown();
                }
            };
            this.timer = new Timer(true);
            timer.scheduleAtFixedRate(timerTask,timerDelayInMilliseconds,timerDelayInMilliseconds);
            timerIsRunning=true;
        }
    }


    private void checkCooldown(){
        if(currentCooldown>0){
            currentCooldown--;
        }
        else{
            isReadyToFire=true;
        }
    }

    @Override
    public boolean getIsReadyToFire(){
        return isReadyToFire;
    }

    public boolean attackIfEnemyInRange(List<Enemy> enemyList) {
        for (Enemy e : enemyList){
            enemyPosX = e.getPositionX();
            enemyPosY = e.getPositionY();
            double distX = enemyPosX-posX;
            //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. angle reflekterar nu verkligheten.
            //i Projectile skapa finns det minus framför vy för att återställa detta igen
            double distY = -(enemyPosY-posY);
            double distHyp = Math.sqrt(distX*distX + distY*distY);
            if (distHyp<this.range) {
                this.angle = Math.atan2(distY, distX);
                if(physicalDmg>0){
                    e.tookDamage(physicalDmg, DamageType.PHYSICAL);
                }
                if(magicDmg>0){
                    e.tookDamage(magicDmg, DamageType.MAGICAL);
                }
                resetCurrentCooldown();
                return true;
            }
        }
        return false;
    }

    @Override
    public int getId() {
        return 0;
    }
    @Override
    public int getLeftUpgradeId(){
        return 0;
    }
    @Override
    public int getRightUpgradeId(){
        return 0;
    }

    //sätter cooldown beroende på attackspeed så att Tower inte kan attackera konstant
    private void resetCurrentCooldown(){
        //the attackspeed is attacks/second, for example:
        //attackspeed=0.5, then 1/attackspeed==2 attacks per second. However cooldown is modified more than once a second, in fact every (timerDelayInMilliseconds/1000) seconds
        // To compensate for this, the numerator is 1000 (milliseconds) and the denominator is attackspeed*timerDelayInMilliseconds

        currentCooldown = (1000/(attackSpeed*timerDelayInMilliseconds));
        isReadyToFire = false;
    }


    public Projectile getProjectile(){
        return new Projectile(this.posX,this.posY, enemyPosX, enemyPosY);
    }


    public int getPrice() {
        return price;
    }


    @Override
    public int getLeftUpgradeCost() {
        return this.leftUpgradeCost;
    }

    @Override
    public int getRightUpgradeCost() {
        return this.rightUpgradeCost;
    }

    @Override
    public String getLeftUpgradeLabel() {
        return null;
    }

    @Override
    public String getRightUpgradeLabel() {
        return null;
    }


    public int getCellIndexX() {
        return position.getX();
    }


    public int getCellIndexY() {
        return position.getY();
    }


    public int getMagicDmg() {
        return magicDmg;
    }


    public int getPhysicalDmg() {
        return physicalDmg;
    }


    public double getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public void setMagicDmg(int amount) {
        this.magicDmg = amount;
    }

    @Override
    public void setPhysicalDmg(int amount) {
        this.physicalDmg = amount;
    }

    @Override
    public void setAttackSpeed(double amount) {
        this.attackSpeed = amount;
    }


    public String getImage() {
        return this.getImage();  //not good practice but not enough time to change
    }



    public int getRange() {
        return range;
    }


    public Targeting getTarget() {
        return target;
    }


    public void setTarget(Targeting target) {
        this.target = target;
    }


    public Cell getPosition() {
        return position;
    }

    public Tower leftUpgrade(Tower t) {
        return null; //Error
    }

    public Tower rightUpgrade(Tower t) {
        return null; //Error
    }


}
