package Model.Towers;

import Model.BaseCell;

public class MageTower implements Tower {
    private BaseTower baseTower ;

    public MageTower(BaseCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.baseTower = new BaseTower(position,physicalDmg,magicDmg,price,range,attackSpeed);
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
}
