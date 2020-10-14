package Model.Towers;

import Model.Cell.Cell;

public class TowerLeftUpgrade extends TowerUpgrade{

    public TowerLeftUpgrade(Tower leftTowerUpgrade){
        super(leftTowerUpgrade);

    }


    @Override
    public void leftUpgrade(){
        super.leftUpgrade();

    }


    @Override
    public void update() {

    }

    @Override
    public void checkRadius(double x, double y) {

    }

    @Override
    public void attack() {

    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public int getPosX() {
        return 0;
    }

    @Override
    public int getPosY() {
        return 0;
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public void setAngle(double angle) {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getMagicDmg() {
        return 0;
    }

    @Override
    public int getPhysicalDmg() {
        return 0;
    }

    @Override
    public double getAttackSpeed() {
        return 0;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Targeting getTarget() {
        return null;
    }

    @Override
    public void setTarget(Targeting target) {

    }

    @Override
    public Cell getPosition() {
        return null;
    }
}
