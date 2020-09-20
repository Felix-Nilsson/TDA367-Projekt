package Model.Towers;

import Model.BaseCell;
import javafx.scene.image.Image;

public class ArcherTower implements Tower {

    private BaseTower baseTower ;


    public ArcherTower(BaseCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.baseTower = new BaseTower(position,physicalDmg,magicDmg,price,range,attackSpeed);
        baseTower.setTowerImage("img/archerTower.png");
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
}
