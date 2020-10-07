package Model.Towers;

import Model.BaseCell;

import Model.GroundCell;

import Model.Projectile;
import Model.UpdateModel;
import javafx.scene.image.Image;



public class BaseTower implements Tower {
    private GroundCell position;
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

    public BaseTower(UpdateModel updateModel, GroundCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
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
    public int getRange(){
        return range;
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
    public void checkRadius() {

    }

    @Override
    public void attack() {
        System.out.println("attaaaaack");
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

    public void setTowerImage(String img){
        towerImage = new Image(getClass().getClassLoader().getResourceAsStream(img));
    }

    public void setColor(String s){
        this.position.setColor(s);
    }
}
