package Model.Towers;

import Model.Cell.Cell;
import Model.Enemy.Enemy;
import Model.Updatable;
import javafx.scene.image.Image;

import java.util.List;

public interface Tower extends Updatable {
    void update();
    void attackIfEnemyInRange(List<Enemy> enemyList);
    void attack();
    Image getImage();
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


    Targeting getTarget();
    void setTarget(Targeting target);

    Cell getPosition();
    Projectile getProjectile();

}
