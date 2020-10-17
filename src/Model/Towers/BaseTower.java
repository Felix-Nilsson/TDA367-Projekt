package Model.Towers;

import Model.Cell.Cell;

import Model.Enemy.Enemy;
import javafx.scene.image.Image;

import java.util.List;


public class BaseTower implements Tower {
    private final Cell position;
    private final int physicalDmg;
    private final int magicDmg;
    private final int price;
    private final int range;
    private final double attackSpeed;
    private Image towerImage;
    private double angle;
    private int posX;
    private int posY;
    private Projectile currentProjectile;
    private int enemyPosX;
    private int enemyPosY;



    private Targeting target;

    public BaseTower( Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
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

        //Temp, example of tower setting the color to the cell
        position.setColor("000000");

        //Default is closest
        target = Targeting.FIRST;

    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }


    public void setAngle(double angle) {
        this.angle=angle;
    }



    public void update() {
        //TODO måste finnas metod som kollar cooldown
    }


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


    public void attack() {
        currentProjectile = new Projectile(this.posX,this.posY, enemyPosX, enemyPosY);
    }


    public Projectile getProjectile(){
        Projectile tmp = currentProjectile;
        currentProjectile=null;
        return tmp;
    }


    public int getPrice() {
        return price;
    }


    public int getX() {
        return position.getX();
    }


    public int getY() {
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


    public String getImage() {
        return this.getImage();  //TODO might be wierd
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


    public void setTowerImage(String img){
        towerImage = new Image(getClass().getClassLoader().getResourceAsStream(img));
    }

    public void setColor(String s){
        this.position.setColor(s);
    }
}
