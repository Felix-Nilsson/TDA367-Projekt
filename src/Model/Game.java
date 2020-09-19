package Model;

import View.MapController;

import java.util.ArrayList;

public class Game {
    public Game(){
        startGame();
    }
    private void startGame(){
        Board board= new Board(1);

        ArrayList<Cell> map = new ArrayList<>();
        map=board.createBoard(1);
        MapController mapController = new MapController(map);
    }


}
