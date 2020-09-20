package Model;

import View.MapController;

public class Game {
    private MapController mapController;

    public void startGame(){
        Board b= new Board();
        b.createBoard(1);
        mapController = new MapController(this,b.getBoard());



    }
    public MapController getMapController(){
        return mapController;
    }



}
