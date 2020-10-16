package Model.Towers;

import Model.Cell.Cell;

import Model.Enemy.Enemy;

import java.util.List;

public class MageTower implements Tower {
    private BaseTower baseTower ;


    public MageTower(Cell position, int physicalDmg, int magicDmg, int price, int range, double attackSpeed) {
        this.baseTower = new BaseTower(position,physicalDmg,magicDmg,price,range,attackSpeed);
        baseTower.setTowerImage("img/mageTower.png");

    }


    @Override
    public void update() {
        baseTower.update();
    }

    @Override
    public void attackIfEnemyInRange(List<Enemy> enemyList) {
        baseTower.attackIfEnemyInRange(enemyList);
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
    public Projectile getProjectile() {
        return baseTower.getProjectile();
    }

    @Override
    public String toString(){
        return "Mage Tower";

    }
}
