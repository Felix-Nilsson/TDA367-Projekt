package Model.Towers;

import Model.Cell;

import Model.GroundCell;

import Model.UpdateModel;
import javafx.scene.image.Image;

public class MageTower implements Tower {
    private BaseTower baseTower ;


    public MageTower(Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed,UpdateModel updateModel) {
        this.baseTower = new BaseTower(updateModel,position,physicalDmg,magicDmg,price,range,attackSpeed);
        baseTower.setTowerImage("img/mageTower.png");
        updateModel.add(this);
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

    @Override
    public int getX() {
        return baseTower.getX();
    }

    @Override
    public int getY() {
        return baseTower.getY();
    }
}
