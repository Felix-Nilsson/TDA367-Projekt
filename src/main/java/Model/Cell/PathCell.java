package main.java.Model.Cell;

public class PathCell implements Cell {

    private final BaseCell base;

    public PathCell(int x, int y, boolean occupied){
        base = new BaseCell(x, y, TerrainType.PATH, occupied, "#7C4E4E");

    }

    @Override
    public TerrainType getTerrainType() {
        return base.getTerrainType();
    }

    @Override
    public boolean isOccupied() {
        return base.isOccupied();
    }

    @Override
    public int getX() {
        return base.getX();
    }

    @Override
    public int getY() {
        return base.getY();
    }

    @Override
    public void setOccupiedTrue() {
        base.setOccupiedTrue();
    }

    @Override
    public void setOccupiedFalse() {
        base.setOccupiedFalse();
    }
}
