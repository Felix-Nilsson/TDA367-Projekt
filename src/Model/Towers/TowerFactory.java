package Model.Towers;

import Model.Cell;
import Model.UpdateModel;

public interface TowerFactory {
    Tower createTower(Cell cell, UpdateModel updateModel);
}
