package Model.Towers;

import Model.BaseCell;

import Model.GroundCell;

import Model.Updatable;
import Model.UpdateModel;
import javafx.scene.image.Image;


public class ArcherTower implements Tower {

    private BaseTower baseTower ;


    public ArcherTower(UpdateModel updateModel,GroundCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {

        this.baseTower = new BaseTower(updateModel,position,physicalDmg,magicDmg,price,range,attackSpeed);
        baseTower.setTowerImage("img/archerTower.png");
        baseTower.setColor("000000");

        updateModel.add(this);

    }


    @Override
    public void update() {
        System.out.println("archertowerUpdated");
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
