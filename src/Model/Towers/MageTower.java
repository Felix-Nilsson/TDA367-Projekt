package Model.Towers;

import Model.Cell.Cell;

import Model.UpdateModel;

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
    public void checkRadius(double x, double y) {
        baseTower.checkRadius(x,y);
    }

    @Override
    public void attack() {
        baseTower.attack();
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
    public int getX() {
        return baseTower.getX();
    }

    @Override
    public int getY() {
        return baseTower.getY();
    }

    @Override
    public int getMagicDmg() {
        return baseTower.getMagicDmg();
    }

    @Override
    public int getPhysicalDmg() {
        return baseTower.getPhysicalDmg();
    }

    @Override
    public double getAttackSpeed() {
        return baseTower.getAttackSpeed();

    }

    @Override
    public void setMagicDmg(int amount) {
        baseTower.setMagicDmg(amount);
    }

    @Override
    public void setPhysicalDmg(int amount) {
        baseTower.setPhysicalDmg(amount);
    }

    @Override
    public void setAttackSpeed(double amount) {
        baseTower.setAttackSpeed(amount);
    }

    @Override
    public String getImage() {
        return "/img/mageTower.png";
    }

    @Override
    public int getRange() {
        return baseTower.getRange();
    }

    @Override
    public void setAngle(double angle){
            baseTower.setAngle(angle);
        }
    public Targeting getTarget() {
        return baseTower.getTarget();
    }

    @Override
    public void setTarget(Targeting target) {
        baseTower.setTarget(target);
    }

    @Override
    public Cell getPosition() {
        return baseTower.getPosition();
    }

    @Override
    public Tower leftUpgrade(Tower t) {
        MageTowerUpgrade mtu = new MageTowerUpgrade(t);
        mtu.leftUpgrade();
        return this;
    }

    @Override
    public Tower rightUpgrade(Tower t) {
        MageTowerUpgrade mtu = new MageTowerUpgrade(t);
        mtu.rightUpgrade();
        return this;
    }

    @Override
    public String toString(){
        return "Mage Tower";

    }
}
