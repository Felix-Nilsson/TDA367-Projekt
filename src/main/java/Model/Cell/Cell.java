package main.java.Model.Cell;

public interface Cell {

    TerrainType getTerrainType();

    boolean isOccupied();

    String getColor();

    void setColor(String s);

    int getX();

    int getY();

    void setOccupiedTrue();

    void setOccupiedFalse();
}
