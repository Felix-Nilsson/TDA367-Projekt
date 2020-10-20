package main.java.Model.Towers;

import main.java.Model.Cell.Cell;

public interface TowerFactory {
    Tower createTower(Cell cell);

    int getPrice();
}


