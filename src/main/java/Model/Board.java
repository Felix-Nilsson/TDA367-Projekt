package main.java.Model;


import main.java.Model.Cell.*;
import main.java.Model.Level.CurvySnakeFactory;
import main.java.Model.Level.Level;
import main.java.Model.Level.LevelFactory;

import java.util.ArrayList;
import java.util.List;


public class Board {
    private final int BOARD_WIDTH = 26;
    private final int BOARD_HEIGHT = 18;
    private ArrayList<Cell> board;
    private final int mapNumber;
    private final List<Direction> enemyPath = new ArrayList<>();
    private int[][] currentMap;
    private int startPos;
    private int endPos;


    public Board(int mapNumber) {
        this.mapNumber = mapNumber;
        createBoard();


    }

    public int getStartPos() {
        return startPos;
    }

    public int getEndPos() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            if (currentMap[i][BOARD_WIDTH - 1] == 1) {
                endPos = i + 1;
            }
        }
        return endPos;
    }

    private void createBoard() {
        switch (mapNumber) {
            case 1:
                //restrictions:
                // 8 is always the start position,
                // on the last column there can only be one "1" which is the end position
                LevelFactory levelFactory = new CurvySnakeFactory();
                Level level = levelFactory.createLevel();

                createMapGrid(level.getLayout());
                currentMap = level.getLayout();

        }
    }

    private void createMapGrid(int[][] map) {
        ArrayList<Cell> tempBoard = new ArrayList<>();
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                if (map[j][i] == 0) {
                    tempBoard.add(new GroundCell(i, j, false));
                } else if (map[j][i] == 1) {
                    tempBoard.add(new PathCell(i, j, true));
                } else if (map[j][i] == 2) {
                    tempBoard.add(new WaterCell(i, j, false));
                } else if (map[j][i] == 3) {
                    tempBoard.add(new ObstacleCell(i, j, true));
                }

                //skapar en path
                else if (map[j][i] == 8) {
                    tempBoard.add(new PathCell(i, j, false));
                    fillPath(j, i, map);
                    startPos = j + 1;
                }

            }

        }
        setBoard(tempBoard);

    }

    public List<Cell> getBoard() {
        return this.board;
    }

    private void setBoard(ArrayList<Cell> board) {
        this.board = board;
    }

    public List<Direction> getPath() {
        return enemyPath;
    }

    // j och i är index för spawnet i första iterationen.
    private void fillPath(int j, int i, int[][] map) {

        //sätter start direction till EAST för att enemies antagligen aldrig börjar åka åt vänster så while-loopen fungerar
        Direction prevDir = Direction.EAST;
        while (i < BOARD_WIDTH - 1) {
            //checks to the right
            if ((map[j][i + 1] == 1) && prevDir != Direction.WEST) {
                enemyPath.add(Direction.EAST);
                prevDir = Direction.EAST;
                i++;
            }
            //checks below
            else if ((map[j + 1][i] == 1) && prevDir != Direction.NORTH) {
                enemyPath.add(Direction.SOUTH);
                prevDir = Direction.SOUTH;
                j++;
            }
            //checks above
            else if ((map[j - 1][i] == 1) && prevDir != Direction.SOUTH) {
                enemyPath.add(Direction.NORTH);
                prevDir = Direction.NORTH;
                j--;
            }
            //checks left
            else if ((map[j][i - 1] == 1) && prevDir != Direction.EAST) {
                enemyPath.add(Direction.WEST);
                prevDir = Direction.WEST;
                i--;
            }
        }
    }

    public int getBOARD_WIDTH() {
        return this.BOARD_WIDTH;
    }

    public int getBOARD_HEIGHT() {
        return this.BOARD_HEIGHT;
    }

    public boolean isCellOccupied(int index) {
        return board.get(index).isOccupied();
    }

    public void setCellOccupied(int index) {
        board.get(index).setOccupiedTrue();
    }

    public void setCellUnoccupied(int index) {
        board.get(index).setOccupiedFalse();
    }
}


