package Model.Towers;

import Model.BaseCell;

import Model.Cell;
import Model.GroundCell;

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

    private UpdateModel updateModel;

    public BaseTower(UpdateModel updateModel, Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.position = position;
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

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public int getY() {
        return position.getY();
    }




    public void setTowerImage(String img){
        towerImage = new Image(getClass().getClassLoader().getResourceAsStream(img));
    }

    public void setColor(String s){
        this.position.setColor(s);
    }
}
