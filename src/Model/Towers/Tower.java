package Model.Towers;

import Model.Cell;
import Model.Updatable;
import javafx.scene.image.Image;

public interface Tower extends Updatable {
    void update();
    void checkRadius();
    void attack();
    Image getImage();
    int getPrice();
    int getX();
    int getY();

    int getMagicDmg();
    int getPhysicalDmg();
    double getAttackSpeed();
    int getRange();

    Targeting getTarget();
    void setTarget(Targeting target);



}
