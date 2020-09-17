package Model.Towers;

import Model.BaseCell;
import Model.Towers.Tower;

public class BaseTower implements Tower {
    private BaseCell position;
    private int physicalDmg;
    private int magicDmg;
    private int price;
    private int range;
    private double attackSpeed;

    public BaseTower(BaseCell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.position = position;
        this.physicalDmg = physicalDmg;
        this.magicDmg = magicDmg;
        this.price = price;
        this.range = range;
        this.attackSpeed = attackSpeed;
    }


    @Override
    public void update() {

    }

    @Override
    public void checkRadius() {

    }

    @Override
    public void attack() {

    }
}
