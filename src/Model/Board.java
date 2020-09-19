package Model;

import View.MapController;


import java.util.ArrayList;
import java.util.List;

public class Board {
    private int BOARD_WIDTH = 26;
    private int BOARD_HEIGHT = 18;
    public Board(int mapNumber){
        createBoard(mapNumber);
    }


    private int[][] map_1= {
            {0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,3,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
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



    public ArrayList<Cell> createBoard(int mapNumber){
        ArrayList<Cell> map = new ArrayList<>();
        switch (mapNumber){
            case 1: map = createMapGrid(map_1);
        }

        return map;
    }

    public ArrayList<Cell> createMapGrid(int[][] map){
       

        ArrayList<Cell> mapList = new ArrayList<>();
        for (int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                if(map[j][i] == 0){
                    mapList.add(new GroundCell(i,j, false, 50, 50));
                }
                else if(map[j][i] == 1){
                    mapList.add(new PathCell(i, j, false, 50, 50));
                }
                else if(map[j][i] == 2){
                    mapList.add(new WaterCell(i, j, false, 50, 50));
                }
                else if(map[j][i] == 3){
                    mapList.add(new ObstacleCell(i, j, false, 50, 50));
                }
            }
        }

        return mapList;

    }



}
