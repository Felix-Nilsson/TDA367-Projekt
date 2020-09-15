package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    private int BOARD_SIZE = 20;
    @FXML private GridPane gameBoardGrid;
    @FXML private Rectangle tile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createMap();
    }

    public void createMap(){



    }


}
