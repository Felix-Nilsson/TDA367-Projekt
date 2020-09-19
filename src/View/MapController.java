package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;


public class MapController extends AnchorPane {
    @FXML private GridPane gameBoardGrid;
    @FXML private Rectangle tile;
    @FXML private ImageView toolbarBackgroundImage;
    @FXML private Label money;
    @FXML private AnchorPane sidebar;
    private ArrayList<Cell> map;

    public MapController(ArrayList<Cell> map){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.map = map;

        createMap();
    }



    public void createMap(){

        try {
            sidebar.getChildren().add(FXMLLoader.load(getClass().getResource("Sidebar.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Cell p: map) {
            Rectangle tile = new Rectangle(50,50);
            tile.setFill(Color.web(p.getColor()));
            tile.setStroke(Color.BLACK);
            gameBoardGrid.add(tile, p.getX(), p.getY());
        }


    }




        public int getMoney () {
            return Integer.parseInt(money.getText());
        }


}

