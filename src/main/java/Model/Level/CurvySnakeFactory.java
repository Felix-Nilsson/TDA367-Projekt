package main.java.Model.Level;

public class CurvySnakeFactory implements LevelFactory{


    @Override
    public Level createLevel() {
        int[][] layout = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,3,0},
            {8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,0,3,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,3,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,3,0,0,0,0,0,2,2,3,2,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,2,2,2,2,2,2,2,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,1,2,2},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,3,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0},

        };

        Level cs = new Level(layout,"Curvy Snake");

        return cs;

    }

    @Override
    public String getlevelName() {
        return "Curvy Snake";
    }
}
