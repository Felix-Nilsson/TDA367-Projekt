package main.java.Model.Towers;

import main.java.Model.Cell.Cell;
import main.java.Model.Enemy.Enemy;

import java.util.List;

/**
 * The interface shared by all towers. As long as the reference variable is of type Tower, these methods are available.
 * At the moment it is too large and could be separated further to follow the Interface Segregation Principle.
 */
public interface Tower {
    boolean attackIfEnemyInRange(List<Enemy> enemyList);

    int getPrice();
    int getLeftUpgradeCost();
    int getRightUpgradeCost();
    String getLeftUpgradeLabel();
    String getRightUpgradeLabel();

    int getRange();

    int getCellIndexX();
    int getCellIndexY();

    int getMagicDmg();
    int getPhysicalDmg();
    double getAttackSpeed();
    boolean getIsReadyToFire();
    void stopTimer();
    void startTimer();

    void setMagicDmg(int amount);
    void setPhysicalDmg(int amount);
    void setAttackSpeed(double amount);

    /**
     * These should not be here since images are not part of the model
     * @return image of current tower
     */
    String getLeftUpgradeImage();
    String getRightUpgradeImage();

    Targeting getTarget();
    void setTarget(Targeting target);
    Cell getPosition();
    Projectile getProjectile();

    Tower leftUpgrade(Tower t);
    Tower rightUpgrade(Tower t);

}
