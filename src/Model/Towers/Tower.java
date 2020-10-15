package Model.Towers;

import Model.Cell.Cell;
import Model.Updatable;

public interface Tower extends Updatable {
    void update();
    void checkRadius(double x, double y);
    void attack();

    int getPrice();

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
    Targeting getTarget();
    void setTarget(Targeting target);
    Cell getPosition();

    Tower leftUpgrade(Tower t);
    Tower rightUpgrade(Tower t);

}
