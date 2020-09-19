package Model;

import View.MapController;



import java.util.List;

public class Board {
    MapController mapController = new MapController();
    List<List<Cell>> grid;
    // GameHeight = 768
    // ToolbarWidth = 266
    // GameBoardWidth = 1366 - ToolbarWidth = 1100




    public void createBoard(){
        mapController.createMap();
    }



}
