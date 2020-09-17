package Model;

import javafx.scene.paint.Color;

public class WaterCell implements Cell{

    BaseCell base;
    private Color color;

    public WaterCell(int x, int y, boolean occupied, int width, int height){
        base = new BaseCell(x, y, TerrainType.WATER, occupied, width, height, Color.BLUE);

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
    public Color getColor() {
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
