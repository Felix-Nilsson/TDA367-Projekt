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
import java.util.ResourceBundle;


public class MapController extends AnchorPane {

    @FXML private GridPane gameBoardGrid;
    @FXML private Rectangle tile;
    @FXML private ImageView toolbarBackgroundImage;
    @FXML private Label money;
    @FXML private AnchorPane sidebarController;


    public MapController(ArrayList<Cell> map){
        createMap(map);
    }


    private void createMap(ArrayList<Cell> map){
        gameBoardGrid.setPrefSize(1100, 768);


        try {
            sidebarController.getChildren().add(FXMLLoader.load(getClass().getResource("Sidebar.fxml")));

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

