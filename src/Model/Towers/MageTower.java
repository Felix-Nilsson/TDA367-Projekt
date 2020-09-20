package Model.Towers;

import Model.BaseCell;
import javafx.scene.image.Image;

public class MageTower implements Tower {
    private BaseTower baseTower ;

    public MageTower(BaseCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.baseTower = new BaseTower(position,physicalDmg,magicDmg,price,range,attackSpeed);
        baseTower.setTowerImage("img/mageTower.png");
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
