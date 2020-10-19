package Model.Towers;

import Model.Cell.Cell;
import Model.Enemy.Enemy;

import java.util.List;

public interface Tower {
    void update();
    void attackIfEnemyInRange(List<Enemy> enemyList);
    void attack();

    int getPrice();
    int getLeftUpgradeCost();
    int getRightUpgradeCost();
    String getLeftUpgradeLabel();
    String getRightUpgradeLabel();

    int getPosX();
    int getPosY();
    int getRange();
    void setAngle(double angle);

    int getX();
    int getY();

    int getMagicDmg();
    int getPhysicalDmg();
    double getAttackSpeed();
    boolean getIsReadyToFire();

    void setMagicDmg(int amount);
    void setPhysicalDmg(int amount);
    void setAttackSpeed(double amount);

    String getImage();
    String getLeftUpgradeImage();
    String getRightUpgradeImage();


    Targeting getTarget();
    void setTarget(Targeting target);
    Cell getPosition();
    Projectile getProjectile();

    Tower leftUpgrade(Tower t);
    Tower rightUpgrade(Tower t);

}
