package Model;

import java.util.ArrayList;

public class Game {
    Board board= new Board();
    ArrayList<Cell> map = new ArrayList<>();
    public void startGame(){

        map=board.createBoard(1);
    }
}
