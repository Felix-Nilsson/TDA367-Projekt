package main.java.Model.Cell;

public interface Cell {

    TerrainType getTerrainType();

    boolean isOccupied();

    int getX();

    int getY();

    void setOccupiedTrue();

    void setOccupiedFalse();
}
