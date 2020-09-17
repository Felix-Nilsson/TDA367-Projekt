package View;

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
import java.util.ResourceBundle;

public class MapController extends AnchorPane implements Initializable {

    private int BOARD_WIDTH = 26;
    private int BOARD_HEIGHT = 18;
    @FXML private GridPane gameBoardGrid;
    @FXML private Rectangle tile;
    @FXML private ImageView toolbarBackgroundImage;
    @FXML private Label money;
    @FXML private ImageView redTower;
    @FXML private ImageView blueTower;

    @FXML private AnchorPane sidebarController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createMap();
        money.setText("7558");
        this.getChildren().clear();

        sidebarController = new SidebarController(this);


        this.getChildren().add(sidebarController);


    }

    public void createMap(){
        gameBoardGrid.setPrefSize(1100, 768);
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                Rectangle tile = new Rectangle(50, 50);
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);
                gameBoardGrid.add(tile, j, i);
            }
        }

    }

    public int getMoney(){
        return Integer.parseInt(money.getText());
    }



}

