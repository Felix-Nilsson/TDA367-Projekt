package Model.Towers;

import Model.BaseCell;

import Model.GroundCell;

import javafx.scene.image.Image;



public class BaseTower implements Tower {
    private GroundCell position;
    private int physicalDmg;
    private int magicDmg;
    private int price;
    private int range;
    private double attackSpeed;
    private Image towerImage;

    public BaseTower(GroundCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.position = position;
        this.physicalDmg = physicalDmg;
        this.magicDmg = magicDmg;
        this.price = price;
        this.range = range;
        this.attackSpeed = attackSpeed;


        //Temp, example of tower setting the color to the cell
        position.setColor("000000");

    }



    @Override
    public void update() {
        System.out.println("updated basetower");

    }

    @Override
    public void checkRadius() {

    }

    @Override
    public void attack() {

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
