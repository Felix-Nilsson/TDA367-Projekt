package main.java.Model.Cell;

import javafx.scene.paint.Color;

public class GroundCell implements Cell {
    BaseCell base;
    private Color color;

    public GroundCell(int x, int y, boolean occupied){
        base = new BaseCell(x, y, TerrainType.GROUND, occupied,  "2aa84c");

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
