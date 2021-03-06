package main.java.Model.Towers.Upgrade;


import main.java.Model.Cell.Cell;
import main.java.Model.Enemy.Enemy;
import main.java.Model.Towers.Projectile;
import main.java.Model.Towers.Targeting;
import main.java.Model.Towers.Tower;

import java.util.List;

public abstract class TowerUpgrade implements Tower {
    protected Tower tower;

    public TowerUpgrade(Tower tower){
        this.tower = tower;
    }


    public Tower leftUpgrade(Tower t) {
        return t;
    }

    public Tower rightUpgrade(Tower t){
        return t;
    }


    public int getPrice() {
        return tower.getPrice();
    }

    public int getLeftUpgradeCost() {
        return tower.getLeftUpgradeCost();
    }

    public int getRightUpgradeCost() {
        return tower.getRightUpgradeCost();
    }

    public String getLeftUpgradeLabel() {
        return tower.getLeftUpgradeLabel();
    }

    public String getRightUpgradeLabel() {
        return tower.getRightUpgradeLabel();
    }

    public int getRange() {
        return tower.getRange();
    }

    public int getCellIndexX() {
        return tower.getCellIndexX();
    }

    public int getCellIndexY() {
        return tower.getCellIndexY();
    }

    public int getMagicDmg() {
        return tower.getMagicDmg();
    }

    public int getPhysicalDmg() {
        return tower.getPhysicalDmg();
    }

    public double getAttackSpeed() {
        return tower.getAttackSpeed();
    }

    public void upgradeMagicDmg() {
        tower.upgradeMagicDmg();
    }

    public void upgradePhysicalDmg() {
        tower.upgradePhysicalDmg();
    }

    public void upgradeAttackSpeed() {
        tower.upgradeAttackSpeed();
    }

    public Targeting getTarget() {
        return tower.getTarget();
    }

    public void setTarget(Targeting target) {
        tower.setTarget(target);
    }

    public Cell getPosition() {
        return tower.getPosition();
    }

    @Override
    public String toString() {
        return tower.toString();
        //Can append tower upgrades here, but not neccessary in current version
    }

    @Override
    public int getId(){
        return tower.getId();
        /*
        Should be moved to each upgraded tower in the future so to easier
        see differences
         */
    }

    public int getLeftUpgradeId(){
        return tower.getLeftUpgradeId();
    }


    public int getRightUpgradeId(){
        return tower.getRightUpgradeId();
    }


    public boolean attackIfEnemyInRange(List<Enemy> enemyList) {
        return tower.attackIfEnemyInRange(enemyList);
    }

    @Override
    public boolean getIsReadyToFire() {
        return tower.getIsReadyToFire();
    }

    public Projectile getProjectile() {
        return tower.getProjectile();
    }

    @Override
    public void stopTimer() {
        tower.stopTimer();
    }

    @Override
    public void startTimer() {
        tower.startTimer();

    }
}
