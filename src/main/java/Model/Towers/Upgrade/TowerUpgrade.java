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

    public void update() { 
        tower.update();
    }

    public void attack() {
        tower.attack();
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

    public int getPosX() {
        return tower.getPosX();
    }

    public int getPosY() {
        return tower.getPosY();
    }

    public int getRange() {
        return tower.getRange();
    }

    public void setAngle(double angle) {
        tower.setAngle(angle);
    }

    public int getX() {
        return tower.getX();
    }

    public int getY() {
        return tower.getY();
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

    public void setMagicDmg(int amount) {
        tower.setMagicDmg(amount);
    }

    public void setPhysicalDmg(int amount) {
        tower.setPhysicalDmg(amount);
    }

    public void setAttackSpeed(double amount) {
        tower.setAttackSpeed(amount);
    }

    public String getLeftUpgradeImage() {
        return tower.getLeftUpgradeImage();
    }

    public String getRightUpgradeImage() {
        return tower.getRightUpgradeImage();
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


    public void attackIfEnemyInRange(List<Enemy> enemyList) {
        tower.attackIfEnemyInRange(enemyList);
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
