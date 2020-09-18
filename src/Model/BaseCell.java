package Model;

public class BaseCell implements Cell{
    private int x;
    private int y;
    private TerrainType terrain;
    private boolean occupied;
    private int width;
    private int height;
    private String color;

    public BaseCell(int x, int y, TerrainType terrain, boolean occupied, int width, int height, String color){
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.occupied = occupied;
        this.width = width;
        this.height = height;
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

    public String getColor(){return this.color;}

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
