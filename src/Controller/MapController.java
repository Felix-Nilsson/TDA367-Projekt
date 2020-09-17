package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    private int BOARD_WIDTH = 26;
    private int BOARD_HEIGHT = 18;
    @FXML private GridPane gameBoardGrid;
    @FXML private Rectangle tile;
    @FXML private ImageView toolbarBackgroundImage;
    @FXML private Label money;
    @FXML private ImageView redTower;
    @FXML private ImageView blueTower;

    private int[][] map_1= {
            {0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,3,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,0,3,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,3,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,3,0,0,0,0,0,2,2,3,2,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,0,0,0,0,0,0,0},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,2,2,2,2,2,2,2,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,1,2,2},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,3,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0},

    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createMap();
        money.setText("7558");

    }

    private ArrayList<Cell> createMapGrid(int[][] map){

        ArrayList<Cell> mapList = new ArrayList<>();
        for (int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                if(map[j][i] == 0){
                    mapList.add(new GroundCell(i,j, false, 50, 50));
                }
                else if(map[j][i] == 1){
                    mapList.add(new PathCell(i, j, false, 50, 50));
                }
                else if(map[j][i] == 2){
                    mapList.add(new WaterCell(i, j, false, 50, 50));
                }
                else if(map[j][i] == 3){
                    mapList.add(new ObstacleCell(i, j, false, 50, 50));
                }
            }
        }

        return mapList;
    }



    public void createMap(){
        gameBoardGrid.setPrefSize(1100, 768);
        /*
        ArrayList<Model.Cell> mapList = new ArrayList<>();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if(i == j) {
                    mapList.add(new PathCell(i, j, false, 50, 50));
                }
                else{
                    mapList.add(new GroundCell(i,j, false, 50, 50));
                }
            }
        }


         */

        ArrayList<Model.Cell> mapList = createMapGrid(map_1);
        for (Cell p: mapList) {

            Rectangle tile = new Rectangle(50,50);
            tile.setFill(p.getColor());
            tile.setStroke(Color.BLACK);
            gameBoardGrid.add(tile, p.getX(), p.getY());
        }

        /*
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {

                Rectangle tile = new Rectangle(50, 50);
                tile.setFill(Color.BURLYWOOD);
                tile.setStroke(Color.BLACK);
                gameBoardGrid.add(tile, j, i);

            }
        }

         */

    }

    public int getMoney(){
        return Integer.parseInt(money.getText());
    }

}

