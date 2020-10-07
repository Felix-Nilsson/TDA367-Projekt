package Model.Towers;

import Model.BaseCell;

import Model.GroundCell;

import Model.UpdateModel;
import javafx.scene.image.Image;

public class MageTower implements Tower {
    private BaseTower baseTower ;


    public MageTower(GroundCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed,UpdateModel updateModel) {
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
    public int getPosX() {
        return baseTower.getPosX();
    }

    @Override
    public int getPosY() {
        return baseTower.getPosY();
    }

    @Override
    public int getRange() {
        return baseTower.getRange();
    }

    @Override
    public void setAngle(double angle) {
        baseTower.setAngle(angle);
    }
}
