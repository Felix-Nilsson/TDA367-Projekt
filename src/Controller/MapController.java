package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.io.IOException;
import java.util.ArrayList;


public class MapController extends AnchorPane implements Observer {
    @FXML private GridPane gameBoardGrid;
    @FXML private Rectangle tile;
    @FXML private ImageView toolbarBackgroundImage;
    @FXML private Label money;

    @FXML private Button continueButton;
    @FXML private Button home;
    @FXML private Button exit;
    @FXML private Button restart;

    @FXML private AnchorPane sidebar;
    @FXML private AnchorPane settings;
    @FXML private AnchorPane settingsPane;
    @FXML private AnchorPane mapAnchorPane;



    private final ArrayList<Cell> map;
    private final Game game;
    private SidebarController sidebarController;
    private final Observable observable;


    public MapController(Game game, ArrayList<Cell> map,Observable observable){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Map.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.map = map;
        this.game = game;
        this.observable = observable;


        observable.add(this);
        createMap();
    }

    public void createMap(){
        //add sidebar fxml
        sidebarController = new SidebarController(game,observable,this);
        sidebar.getChildren().add(sidebarController);

        //add all cells to GUI
        for (Cell p: map) {
            Rectangle tile = new Rectangle(50,50);
            tile.setFill(Color.web(p.getColor()));
            tile.setStroke(Color.BLACK);
            gameBoardGrid.add(tile, p.getX(), p.getY());
        }
    }
    public void update(){
        System.out.println("update mapcontroller");
        //sidebarController.update();
    }
    public void openSettings(){
        settingsPane.toFront();
    }
    @FXML
    public void openMap(){
        mapAnchorPane.toFront();
    }


}

