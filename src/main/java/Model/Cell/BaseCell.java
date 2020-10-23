package main.java.Model.Cell;

public class BaseCell implements Cell {
    private final int x;
    private final int y;
    private final TerrainType terrain;
    private boolean occupied;

    public BaseCell(int x, int y, TerrainType terrain, boolean occupied, String color){
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.occupied = occupied;

    }

    @Override
    public TerrainType getTerrainType() {
        return this.terrain;
    }

    @Override
    public boolean isOccupied() {
        return this.occupied;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setOccupiedTrue(){
        this.occupied = true;
    }

    public void setOccupiedFalse(){this.occupied = false;}


}
