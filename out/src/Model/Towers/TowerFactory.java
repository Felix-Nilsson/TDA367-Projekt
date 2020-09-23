package Model.Towers;

import Model.GroundCell;

public interface TowerFactory {
    Tower createTower(GroundCell cell);
}
