package Model;

import javafx.scene.paint.Color;

public class PathCell implements Cell{

    BaseCell base;
    private Color color;

    public PathCell(int x, int y, boolean occupied, int width, int height){
        base = new BaseCell(x, y, TerrainType.PATH, occupied, width, height, "#7C4E4E");

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
}
