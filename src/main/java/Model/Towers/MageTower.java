package main.java.Model.Towers;

import main.java.Model.Cell.Cell;

import main.java.Model.Towers.Upgrade.MageTowerLeftUpgrade;
import main.java.Model.Towers.Upgrade.MageTowerRightUpgrade;
import main.java.Model.Enemy.Enemy;
import java.util.List;

public class MageTower implements Tower {
    private final BaseTower baseTower ;



    public MageTower(Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed, int leftUpgradeCost, int rightUpgradeCost) {
        this.baseTower = new BaseTower(position,physicalDmg,magicDmg,price,range,attackSpeed, leftUpgradeCost, rightUpgradeCost);


    }


    @Override
    public boolean attackIfEnemyInRange(List<Enemy> enemyList) {
        return baseTower.attackIfEnemyInRange(enemyList);

    }



    @Override
    public int getPrice() {
        return baseTower.getPrice();
    }

    @Override
    public int getLeftUpgradeCost() {
        return baseTower.getLeftUpgradeCost();
    }

    @Override
    public int getRightUpgradeCost() {
        return baseTower.getRightUpgradeCost();
    }

    @Override
    public String getLeftUpgradeLabel() {
        return "Add 20 magic damage";
    }

    @Override
    public String getRightUpgradeLabel() {
        return "Add 1 speed";
    }


    @Override
    public int getCellIndexX() {
        return baseTower.getCellIndexX();
    }

    @Override
    public int getCellIndexY() {
        return baseTower.getCellIndexY();
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


    public boolean getIsReadyToFire() {
        return baseTower.getIsReadyToFire();

    }

    @Override
    public void stopTimer() {
        baseTower.stopTimer();
    }

    @Override
    public void startTimer() {
        baseTower.startTimer();
    }


    @Override
    public String getLeftUpgradeImage() {
        return "/img/mageTowerDmgUpgrade.png";
    }

    @Override
    public String getRightUpgradeImage() {
        return "/img/atkSpdUpgrade.png";
    }

    @Override
    public int getRange() {
        return baseTower.getRange();
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
        MageTowerLeftUpgrade mtlu = new MageTowerLeftUpgrade(t);
        mtlu.leftUpgrade(t);
        return mtlu;
    }

    @Override
    public Tower rightUpgrade(Tower t) {
        MageTowerRightUpgrade mtru = new MageTowerRightUpgrade(t);
        mtru.rightUpgrade();
        return mtru;
    }


    public Projectile getProjectile() {
        return baseTower.getProjectile();

    }

    @Override
    public String toString(){
        return "Mage Tower";

    }
}
