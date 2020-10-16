package Model.Towers;

import Model.Cell.Cell;
import Model.Enemy.Enemy;
import Model.Updatable;

import java.util.List;

public interface Tower extends Updatable {
    void update();
    void attackIfEnemyInRange(List<Enemy> enemyList);
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

    String getImage();

    Targeting getTarget();
    void setTarget(Targeting target);

    Cell getPosition();
    Projectile getProjectile();

}
