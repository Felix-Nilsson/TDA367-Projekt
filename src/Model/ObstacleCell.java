package Model;

import javafx.scene.paint.Color;

public class ObstacleCell implements Cell{

    BaseCell base;
    private Color color;

    public ObstacleCell(int x, int y, boolean occupied){
        base = new BaseCell(x, y, TerrainType.OBSTACLE, occupied, "#6f747a");

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
