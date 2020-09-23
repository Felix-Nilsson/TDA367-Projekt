package Model;

import Model.Towers.ArcherTower;
import Model.Towers.ArcherTowerFactory;
import Model.Towers.Tower;
import View.MapController;


import java.util.ArrayList;


public class Board {
    private int BOARD_WIDTH = 26;
    private int BOARD_HEIGHT = 18;
    private  ArrayList<Cell> board;
    private int mapNumber;


    public Board(int mapNumber){
        this.mapNumber = mapNumber;
        createBoard();

    }

    private void createBoard(){
        switch (mapNumber){
            case 1: createMapGrid(map_1);
            case 2: /*create another map */;
        }
    }
    private void createMapGrid(int[][] map){
        ArrayList<Cell> tempBoard = new ArrayList<>();
        for (int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                if(map[j][i] == 0){
                    tempBoard.add(new GroundCell(i,j, false, 50, 50));
                }
                else if(map[j][i] == 1){
                    tempBoard.add(new PathCell(i, j, false, 50, 50));
                }
                else if(map[j][i] == 2){
                    tempBoard.add(new WaterCell(i, j, false, 50, 50));
                }
                else if(map[j][i] == 3){
                    tempBoard.add(new ObstacleCell(i, j, true, 50, 50));
                }
                else if(map[j][i] == 4){
                    //temp, example of adding a tower to a cell
                    GroundCell cell = new GroundCell(i,j,false,50,50);
                    ArcherTower archerTower = new ArcherTowerFactory().createTower(cell);

                    tempBoard.add(cell);
                }
            }

        }
        setBoard(tempBoard);
    }

    public ArrayList<Cell> getBoard(){
        return this.board;
    }

    private void setBoard(ArrayList<Cell> board) {
        this.board = board;
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
            {0,0,1,4,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,2,2,2,2,2,2,2,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,1,2,2},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,3,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0},

    };



}