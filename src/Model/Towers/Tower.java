package Model.Towers;

import Model.Updatable;
import javafx.scene.image.Image;

public interface Tower extends Updatable {
    void update();
    void checkRadius();
    void attack();
    Image getImage();

}
