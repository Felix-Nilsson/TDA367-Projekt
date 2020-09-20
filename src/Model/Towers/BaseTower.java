package Model.Towers;

import Model.BaseCell;
import javafx.scene.image.Image;


public class BaseTower implements Tower {
    private BaseCell position;
    private int physicalDmg;
    private int magicDmg;
    private int price;
    private int range;
    private double attackSpeed;
    private Image towerImage;

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
}
