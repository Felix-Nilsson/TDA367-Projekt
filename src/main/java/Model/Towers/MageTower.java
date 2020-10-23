package main.java.Model.Towers;

import main.java.Model.Cell.Cell;

import main.java.Model.Towers.Upgrade.MageTowerLeftUpgrade;
import main.java.Model.Towers.Upgrade.MageTowerRightUpgrade;
import main.java.Model.Enemy.Enemy;
import java.util.List;

public class MageTower implements Tower {
    private final BaseTower baseTower ;

    private final int id;

    private final double attackSpeedAdder = 0.5;
    private final int physicalDamageAdder;
    private final int magicDamageAdder;




    public MageTower(Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed, int leftUpgradeCost, int rightUpgradeCost) {
        this.baseTower = new BaseTower(position,physicalDmg,magicDmg,price,range,attackSpeed, leftUpgradeCost, rightUpgradeCost);

        id = 1;
        physicalDamageAdder=0;
        magicDamageAdder=20;
    }






    public int getId(){
        return this.id;
    }

    @Override
    public int getLeftUpgradeId(){
        return 1;
    }

    @Override
    public int getRightUpgradeId(){
        return 3;
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
        return "Add "+ magicDamageAdder + " magic damage";
    }

    @Override
    public String getRightUpgradeLabel() {
        return "Add " + attackSpeedAdder + " attackspeed";
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
    public void upgradeMagicDmg() {
        baseTower.upgradeMagicDmgBy(magicDamageAdder);
    }

    @Override
    public void upgradePhysicalDmg() {
        baseTower.upgradePhysicalDmgBy(physicalDamageAdder);
    }

    @Override
    public void upgradeAttackSpeed() {
        baseTower.upgradeAttackSpeedBy( attackSpeedAdder);
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
