package Model.Towers;

import Model.Cell.Cell;

import Model.Enemy.Enemy;
import Model.UpdateModel;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class BaseTower implements Tower {
    private final Cell position;
    private final int physicalDmg;
    private final int magicDmg;
    private final int price;
    private final int range;
    private final double attackSpeed;
    private Image towerImage;
    private double angle;
    private final int posX;
    private final int posY;
    private Projectile currentProjectile;
    private int enemyPosX;
    private int enemyPosY;
    private double currentCooldown;
    private boolean isReadyToFire = true;
    private final int timerDelayInMilliseconds = 100;

    private UpdateModel updateModel;

    private Targeting target;

    public BaseTower(UpdateModel updateModel, Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.position = position;
        //längst upp till vänster är (25,15). Varje cell är 40 pixlar
        posX = position.getX()*40 +25;
        posY = position.getY()*40 +15;
        //System.out.println("x: "+ posX);
        //System.out.println("y: "+ posY);
        this.physicalDmg = physicalDmg;
        this.magicDmg = magicDmg;
        this.price = price;
        this.range = range;
        this.attackSpeed = attackSpeed;

        this.updateModel = updateModel;
        //tower ska kunna attackera direkt
        this.currentCooldown=0;
        //Temp, example of tower setting the color to the cell
        position.setColor("000000");

        //Default is closest
        target = Targeting.FIRST;
        timer.scheduleAtFixedRate(timerTask,timerDelayInMilliseconds,timerDelayInMilliseconds);
    }
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask(){
        @Override
        public void run() {
            checkCooldown();
        }
    };
    private void checkCooldown(){
        if (currentCooldown>0){
            currentCooldown--;
            System.out.println("IS NOOOOOOOOOOOOOOOOOOOOOT READY TO FIRE");
            System.out.println("currentCooldown: " + currentCooldown);
        }
        else{
            isReadyToFire = true;
            System.out.println("IS NOW READY TO FIRE AGAAAIN");
        }
    }

    @Override
    public boolean getIsReadyToFire() {
        return isReadyToFire;
    }

    @Override
    public int getPosX(){
        return posX;
    }
    @Override
    public int getPosY(){
        return posY;
    }

    @Override
    public void setAngle(double angle) {
        this.angle=angle;
    }


    @Override
    public void update() {
        //TODO måste finnas metod som kollar cooldown
    }

    @Override
    public void attackIfEnemyInRange(List<Enemy> enemyList) {
        for (Enemy e : enemyList){
            enemyPosX = e.getPositionX();
            enemyPosY = e.getPositionY();
            double distX = enemyPosX-posX;
            //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. angle reflekterar nu verkligheten.
            //i Projectile skapa finns det minus framför vy för att återställa detta igen
            double distY = -(enemyPosY-posY);
            double distHyp = Math.sqrt(distX*distX + distY*distY);
            //System.out.println(distHyp);
            if (distHyp<this.range) {
                this.angle = Math.atan2(distY, distX);
                attack();
                //e.tookDamage(5);
            }
        }
    }

    @Override
    public void attack() {
        System.out.println("attaaaaack");
        System.out.println("angle: " +Math.toDegrees(angle));
        //currentProjectile = new Projectile(this.posX,this.posY,angle, updateModel);
        currentProjectile = new Projectile(this.posX,this.posY, enemyPosX, enemyPosY, updateModel);
        resetCurrentCooldown();
    }

    //sätter cooldown beroende på attackspeed så att Tower inte kan attackera konstant
    private void resetCurrentCooldown(){
        //the attackspeed is attacks/second, for example:
        //attackspeed=0.5, then 1/attackspeed==2 attacks per second. However cooldown is modified more than once a second, in fact every (timerDelayInMilliseconds/1000) seconds
        // To compensate for this, the numerator is 1000 (milliseconds) and the denominator is attackspeed*timerDelayInMilliseconds

        currentCooldown = (1000/(attackSpeed*timerDelayInMilliseconds));
        isReadyToFire = false;
    }

    @Override
    public Projectile getProjectile(){
        Projectile tmp = currentProjectile;
        currentProjectile=null;
        return tmp;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public int getY() {
        return position.getY();
    }

    @Override
    public int getMagicDmg() {
        return magicDmg;
    }

    @Override
    public int getPhysicalDmg() {
        return physicalDmg;
    }

    @Override
    public double getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public String getImage() {
        return this.getImage();  //TODO might be wierd
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public Targeting getTarget() {
        return target;
    }

    @Override
    public void setTarget(Targeting target) {
        this.target = target;
    }

    @Override
    public Cell getPosition() {
        return position;
    }


    public void setTowerImage(String img){
        towerImage = new Image(getClass().getClassLoader().getResourceAsStream(img));
    }

    public void setColor(String s){
        this.position.setColor(s);
    }
}
