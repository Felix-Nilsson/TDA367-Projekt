package Model.Towers;

import Model.Cell.Cell;
import Model.Updatable;

public interface Tower<T extends Tower> extends Updatable {
    void update();
    void checkRadius(double x, double y);
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

    void setMagicDmg(int amount);
    void setPhysicalDmg(int amount);
    void setAttackSpeed(double amount);

    String getImage();
    String getLeftUpgradeImage();
    String getRightUpgradeImage();


    Targeting getTarget();
    void setTarget(Targeting target);
    Cell getPosition();

    Tower leftUpgrade(Tower t);
    Tower rightUpgrade(Tower t);

}
