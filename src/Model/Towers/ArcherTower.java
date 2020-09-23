package Model.Towers;

import Model.BaseCell;

import Model.GroundCell;

import javafx.scene.image.Image;


public class ArcherTower implements Tower {

    private BaseTower baseTower ;


    public ArcherTower(GroundCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {

        this.baseTower = new BaseTower(position,physicalDmg,magicDmg,price,range,attackSpeed);
        baseTower.setTowerImage("img/archerTower.png");
        baseTower.setColor("000000");
    }


    @Override
    public void update() {
        baseTower.update();
    }

    @Override
    public void checkRadius() {
        baseTower.checkRadius();
    }

    @Override
    public void attack() {
        baseTower.attack();
    }

    @Override
    public Image getImage() {
        return baseTower.getImage();
    }

    @Override
    public int getPrice() {
        return baseTower.getPrice();
    }

}