package Model;

import javafx.scene.paint.Color;

public interface Cell {

    TerrainType getTerrainType();

    boolean isOccupied();

    Color getColor();

    int getX();

    int getY();
}
