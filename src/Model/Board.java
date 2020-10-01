package Model;


import java.util.ArrayList;
import java.util.List;


public class Board {
    private int BOARD_WIDTH = 26;
    private int BOARD_HEIGHT = 18;
    private  ArrayList<Cell> board;
    private int mapNumber;
    private final List<BaseEnemy.Direction> enemyPath = new ArrayList<>();
    private int[][] currentMap;


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


    public Board(int mapNumber){
        this.mapNumber = mapNumber;
        createBoard();

    }

    private void createBoard(){
        switch (mapNumber){
            case 1:
                createMapGrid(map_1);
                currentMap = map_1;
            case 2: /*create another map */;
        }
    }
    private void createMapGrid(int[][] map){
        ArrayList<Cell> tempBoard = new ArrayList<>();
        for (int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                if(map[j][i] == 0){
                    tempBoard.add(new GroundCell(i,j, false));
                }
                else if(map[j][i] == 1){
                    tempBoard.add(new PathCell(i, j, true));
                }
                else if(map[j][i] == 2){
                    tempBoard.add(new WaterCell(i, j, false));
                }
                else if(map[j][i] == 3){
                    tempBoard.add(new ObstacleCell(i, j, true));
                }
                else if(map[j][i] == 4){
                    //temp, example of adding a tower to a cell
                    //GroundCell cell = new GroundCell(i,j,false,50,50);
                    //ArcherTower archerTower = new ArcherTowerFactory().createTower(cell,);

                    //tempBoard.add(cell);
                }
                //skapar en path
                else if(map[j][i] == 8){
                    tempBoard.add(new PathCell(i, j, false));

                    fillPath(j,i,map);
                }
            }

        }
        setBoard(tempBoard);

    }

    public List<Cell> getBoard(){
        return this.board;
    }
    public Board getTmpBoard(){
        return this;
    }

    private void setBoard(ArrayList<Cell> board) {
        this.board = board;
    }
    public List<BaseEnemy.Direction> getPath(){
        return enemyPath;
    }

    // j och i är index för spawnet i första iterationen.
    private void fillPath(int j, int i, int[][]map){

        //sätter start direction till EAST för att enemies antagligen aldrig börjar åka åt vänster så while-loopen fungerar
        BaseEnemy.Direction prevDir = BaseEnemy.Direction.EAST;
        while(i<BOARD_WIDTH-1){
                //checks to the right
                if((map[j][i+1]==1) && prevDir!= BaseEnemy.Direction.WEST){
                    enemyPath.add(BaseEnemy.Direction.EAST);
                    prevDir=BaseEnemy.Direction.EAST;
                    i++;
                }
                //checks below
                else if((map[j+1][i]==1) && prevDir!= BaseEnemy.Direction.NORTH){
                    enemyPath.add(BaseEnemy.Direction.SOUTH);
                    prevDir=BaseEnemy.Direction.SOUTH;
                    j++;
                }
                //checks above
                else if((map[j-1][i]==1) && prevDir!= BaseEnemy.Direction.SOUTH){
                    enemyPath.add(BaseEnemy.Direction.NORTH);
                    prevDir=BaseEnemy.Direction.NORTH;
                    j--;
                }
                //checks left
                else if((map[j][i-1]==1) && prevDir!= BaseEnemy.Direction.EAST){
                    enemyPath.add(BaseEnemy.Direction.WEST);
                    prevDir=BaseEnemy.Direction.WEST;
                    i--;
                }
            }
        }

        public int getBOARD_WIDTH(){
            return this.BOARD_WIDTH;
        }
        public int getBOARD_HEIGHT(){
            return this.BOARD_HEIGHT;
        }

        public boolean isCellOccupied(int index){
            return board.get(index).isOccupied();
        }

        public void setCellOccupied(int index){
            board.get(index).setOccupiedTrue();
        }
    }


