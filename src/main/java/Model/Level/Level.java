package main.java.Model.Level;

public class Level {
    private final int[][] layout;
    private final String name;

    public Level(int[][] layout, String name){
        this.layout = layout;
        this.name = name;
    }

    public String getLevelName(){
        return name;
    }

    public int[][] getLayout(){
        return layout;
    }

}
