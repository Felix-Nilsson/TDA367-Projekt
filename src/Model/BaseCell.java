package Model;

public class BaseCell implements Cell{
    private int x;
    private int y;
    private TerrainType terrain;
    private boolean occupied;
    private String color;

    public BaseCell(int x, int y, TerrainType terrain, boolean occupied, String color){
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.occupied = occupied;
        this.color = color;
    }

    @Override
    public TerrainType getTerrainType() {
        return this.terrain;
    }

    @Override
    public boolean isOccupied() {
        return this.occupied;
    }

    public void setColor(String s){ this.color = s; }

    public String getColor(){return this.color;}

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
