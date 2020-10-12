package Model.Towers;

import Model.Cell;
import Model.Updatable;
import javafx.scene.image.Image;

public interface Tower extends Updatable {
    void update();
    void checkRadius(double x, double y);
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

}
