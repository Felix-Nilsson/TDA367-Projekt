package Model.Towers;

import Model.GroundCell;
import Model.UpdateModel;

public interface TowerFactory {
    Tower createTower(GroundCell cell, UpdateModel updateModel);
}
