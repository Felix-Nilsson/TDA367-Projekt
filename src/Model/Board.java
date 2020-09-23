package Model;

import java.awt.*;
import java.util.ArrayList;



public class Board {
    private int BOARD_WIDTH = 26;
    private int BOARD_HEIGHT = 18;
    private  ArrayList<Cell> board;

    private int[][] map_1= {
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

    public ArrayList<Cell> getBoard(){
        return this.board;
    }

    public void setBoard(ArrayList<Cell> board) {
        this.board = board;
    }

    public void createBoard(int id){
        switch (id){

            case 1:
                createMapGrid(map_1);
            case 2:
                System.out.println("hi");

        }

    }

    public void createMapGrid(int[][] map){
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
                    tempBoard.add(new ObstacleCell(i, j, false, 50, 50));
                }
                else if(map[j][i] == 8){
                    tempBoard.add(new PathCell(i, j, false, 50, 50));
                    System.out.println("j= " + (j*50 + 25) + ", i=" + (i*50 + 25));
                }
            }

        }
        setBoard(tempBoard);
    }

    // j och i är index för spawnet i första iterationen.
    public ArrayList<BaseEnemy.Direction> fillPath(int j, int i, int[][]map){
        ArrayList<BaseEnemy.Direction> path = new ArrayList<>();
        //sätter start direction till EAST för att enemies antagligen aldrig börjar åka åt vänster så while-loopen fungerar
        BaseEnemy.Direction prevDir = BaseEnemy.Direction.EAST;
        while(i+1<BOARD_WIDTH){
                //checks to the right
                if((map[j][i+1]==1) && prevDir!= BaseEnemy.Direction.WEST){
                    path.add(BaseEnemy.Direction.EAST);
                    prevDir=BaseEnemy.Direction.EAST;
                    i=i+1;
                }
                //checks below
                else if((map[j+1][i]==1) && prevDir!= BaseEnemy.Direction.NORTH){
                    path.add(BaseEnemy.Direction.SOUTH);
                    prevDir=BaseEnemy.Direction.SOUTH;
                    j=j+1;
                }
                //checks above
                else if((map[j-1][i]==1) && prevDir!= BaseEnemy.Direction.SOUTH){
                    path.add(BaseEnemy.Direction.NORTH);
                    prevDir=BaseEnemy.Direction.NORTH;
                    j=j-1;
                }
                //checks left
                else if((map[j][i-1]==1) && prevDir!= BaseEnemy.Direction.EAST){
                    path.add(BaseEnemy.Direction.WEST);
                    prevDir=BaseEnemy.Direction.WEST;
                    i=i-1;
                }
            }
        return path;
        }
    }

