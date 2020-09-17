package Model.Towers;

import Model.Updatable;

public interface Tower extends Updatable {
    void update();
    void checkRadius();
    void attack();

}
