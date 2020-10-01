package Model;

public interface Cell {

    TerrainType getTerrainType();

    boolean isOccupied();

    String getColor();

    int getX();

    int getY();

    void setOccupiedTrue();

    void setOccupiedFalse();
}
