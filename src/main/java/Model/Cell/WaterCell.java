package Model.Cell;

import javafx.scene.paint.Color;

public class WaterCell implements Cell {

    BaseCell base;
    private Color color;

    public WaterCell(int x, int y, boolean occupied){
        base = new BaseCell(x, y, TerrainType.WATER, occupied, "2d6ecf");

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
    public String getColor() {
        return base.getColor();
    }

    @Override
    public void setColor(String s) {base.setColor(s); }

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
