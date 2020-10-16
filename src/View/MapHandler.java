package View;

import Model.Cell.Cell;
import Model.Enemy.Enemy;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.List;

public class MapHandler {

    @FXML private AnchorPane gameBoardAnchorPane;
    @FXML private GridPane gameBoardGrid;
    @FXML private GridPane toplayerGrid;

    private final List<Cell> map;
    private ImageView cave;
    private ImageView base;


    public MapHandler(AnchorPane gameBoardAnchorPane, GridPane gameBoardGrid,GridPane toplayerGrid, List<Cell> map){
        this.gameBoardAnchorPane = gameBoardAnchorPane;
        this.gameBoardGrid = gameBoardGrid;
        this.toplayerGrid = toplayerGrid;
        this.map = map;
    }


    public void createMap(int startPos, int endPos, ImageView caveView, ImageView baseView){
        this.cave = caveView;
        this.base = baseView;

        //add startcave
        cave = new ImageView("/img/cave.png");
        cave.setX(0);
        cave.setY((startPos - 1) *40);
        cave.setFitHeight(40);
        cave.setFitWidth(40);
        cave.setPreserveRatio(true);
        cave.toFront();
        gameBoardAnchorPane.getChildren().add(cave);

        //add endcave

        base = new ImageView("/img/base.png");
        base.setFitHeight(40);
        base.setFitWidth(40);
        base.setPreserveRatio(true);
        base.toFront();
        base.setX(gameBoardAnchorPane.getWidth()-cave.getFitWidth()); //TODO gameboardanchorpane width does not work in map handler for some reason
        base.setY((endPos - 1) *40);
        gameBoardAnchorPane.getChildren().add(base);

        //add all cells to GUI
        for (Cell p: map) {
            Rectangle tile = new Rectangle(40,40);
            tile.setX(p.getX());
            tile.setY(p.getY());
            tile.setFill(Color.web(p.getColor()));
            gameBoardGrid.add(tile, p.getX(), p.getY());
        }
    }

    public void drawEnemy(ImageView img){

        Platform.runLater(()->gameBoardAnchorPane.getChildren().add(img));
        Platform.runLater(()->cave.toFront()); //sets the cave to be in front of the enemies
        Platform.runLater(()->base.toFront());
    }

    public void changeToplayerGridVisible(boolean visible){
        toplayerGrid.setGridLinesVisible(visible);
    }
    private void fixImage(ImageView img,Enemy e){
        img.setX(e.getPositionX());
        img.setY(e.getPositionY());
        img.setFitHeight(25);
        img.setFitWidth(25);
        img.setPreserveRatio(true);
        img.toBack();
    }
    public void updateEnemy(HashMap<Enemy, ImageView> enemyHashMap, Enemy e){
        ImageView img = enemyHashMap.get(e);
        img.setX(e.getPositionX());
        img.setY(e.getPositionY());
    }

    public void openSettings(AnchorPane settingsPane){
        settingsPane.toFront();
    }

    public void closeSettings(AnchorPane mapAnchorPane){
        mapAnchorPane.toFront();
    }

    public void moveToolbarBack(AnchorPane toolbarAnchorPane, AnchorPane toolbarCover){
        toolbarAnchorPane.toBack();
        toolbarCover.toFront();
    }

    public void moveToolbarFront(AnchorPane toolbarAnchorPane, AnchorPane toolbarCover){
        toolbarAnchorPane.toFront();
        toolbarCover.toBack();
    }

    public void removeImageFromGrid(ImageView image){
        gameBoardGrid.getChildren().remove(image);
    }
}
