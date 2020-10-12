package Model.Towers;

import Model.BaseCell;

import Model.Cell;
import Model.GroundCell;

import Model.Projectile;
import Model.UpdateModel;
import javafx.scene.image.Image;



public class BaseTower implements Tower {
    private Cell position;
    private int physicalDmg;
    private int magicDmg;
    private int price;
    private int range;
    private double attackSpeed;
    private Image towerImage;
    private double angle;
    private int posX;
    private int posY;


    private UpdateModel updateModel;

    private Targeting target;

    public BaseTower(UpdateModel updateModel, Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.position = position;
        //längst upp till vänster är (25,15). Varje cell är 40 pixlar
        posX = position.getX()*40 +25;
        posY = position.getY()*40 +15;
        System.out.println("x: "+ posX);
        System.out.println("y: "+ posY);
        this.physicalDmg = physicalDmg;
        this.magicDmg = magicDmg;
        this.price = price;
        this.range = range;
        this.attackSpeed = attackSpeed;

        this.updateModel = updateModel;

        //Temp, example of tower setting the color to the cell
        position.setColor("000000");

        //Default is closest
        target = Targeting.FIRST;

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
    public void checkRadius(double x, double y) {

        double distX = x-posX;
        //minus framför eftersom större y går nedåt i GUI men uppåt i enhetscirkeln. angle reflekterar nu verkligheten.
        //i Projectile skapa finns det minus framför vy för att återställa detta igen
        double distY = -(y-posY);
        double distHyp = Math.sqrt(distX*distX + distY*distY);
        //System.out.println(distHyp);
        if (distHyp<this.range) {
            this.angle = Math.atan2(distY, distX);
            attack();
        }


    }

    @Override
    public void attack() {
        System.out.println("attaaaaack");
        System.out.println("angle: " +Math.toDegrees(angle));
        new Projectile(posX,posY,angle);
    }

    @Override
    public Image getImage() {
        return towerImage;
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
