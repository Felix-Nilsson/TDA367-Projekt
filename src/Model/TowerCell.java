package Model;

import Model.Towers.Tower;
import javafx.scene.paint.Color;

public class TowerCell implements Cell{

    BaseCell base;
    Tower tower;
    private Color color;

    public TowerCell(Tower tower, int x, int y, boolean occupied, int width, int height){
        base = new BaseCell(x, y, TerrainType.GROUND, occupied, width, height, "ffffff");
        this.tower = tower;
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

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
