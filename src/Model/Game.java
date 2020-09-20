package Model;

import View.MapController;
import View.MenuController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
