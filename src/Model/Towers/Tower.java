package Model.Towers;

import Model.Updatable;
import javafx.scene.image.Image;

public interface Tower extends Updatable {
    void update();
    void checkRadius();
    void attack();
    Image getImage();
    int getPrice();
    int getPosX();
    int getPosY();
    int getRange();
    void setAngle(double angle);


}
