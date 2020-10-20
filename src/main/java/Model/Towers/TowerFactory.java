package Model.Towers;

import Model.Cell.Cell;

public interface TowerFactory {
    Tower createTower(Cell cell);

    int getPrice();
}


