package View;

import Model.Cell.Cell;
import Model.Enemy.Enemy;
import Model.Game;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MapHandler implements MapObserver {

    private final AnchorPane gameBoardAnchorPane;
    private final GridPane gameBoardGrid;
    private final GridPane toplayerGrid;
    private final Pane gameWonScreen;
    private final Pane gameOverScreen;
    private HashMap<Enemy, ImageView> enemyHashMap;
    private Label waveNumber;

    private final List<Cell> map;
    private ImageView cave;
    private ImageView base;
    private final Game game;
    private HashMap<Enemy, ProgressBar> progressBarHashMap;

    private Rectangle selectedTower;

    //TODO: Add a reference to game to get info from, rather than map

    //TODO: Add a blurb justifying design choices


    public MapHandler(Game game, Label waveNumber, Pane gameOverScreen, Pane gameWonScreen, AnchorPane gameBoardAnchorPane, GridPane gameBoardGrid, GridPane toplayerGrid, List<Cell> map){
        this.gameBoardAnchorPane = gameBoardAnchorPane;
        this.gameBoardGrid = gameBoardGrid;
        this.toplayerGrid = toplayerGrid;
        this.map = map;
        this.game = game;
        this.gameOverScreen = gameOverScreen;
        this.gameWonScreen = gameWonScreen;
        this.waveNumber = waveNumber;
        progressBarHashMap = new HashMap<>();
        enemyHashMap = new HashMap<>();
        game.addMapObserver(this);

    }


    public void createMap(){
        int startPos = game.getStartPos();
        int endPos = game.getEndPos();

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
        base.setX(1040-cave.getFitWidth()); //TODO gameboardanchorpane width does not work in map handler for some reason
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
    public void updateEnemy( Enemy e){
        ImageView img = enemyHashMap.get(e);
        img.setX(e.getPositionX());
        img.setY(e.getPositionY());
    }

    public void updateProgressBar(Enemy e){
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                ProgressBar pb = progressBarHashMap.get(e);
                pb.setProgress((double)(e.getHealth())/(double)e.getMaxHealth());

                pb.setLayoutX(e.getPositionX()-10);
                pb.setLayoutY(e.getPositionY()-15);
            }
        });

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

    @Override
    public void notifyGameOver() {
        Platform.runLater(gameOverScreen::toFront);
    }

    @Override
    public void notifyRoundOver() {

        enemyHashMap = new HashMap<>();
    }

    @Override
    public void notifyRoundStart() {
        Platform.runLater(() ->waveNumber.setText("Wave: " + game.getRound()));
    }

    @Override
    public void notifyGameWon() {
        Platform.runLater(gameWonScreen::toFront);
    }

    @Override
    public void notifyEnemyDead(Enemy e) {
        Platform.runLater(() -> gameBoardAnchorPane.getChildren().remove(progressBarHashMap.get(e)));
        Platform.runLater(() -> gameBoardAnchorPane.getChildren().remove(enemyHashMap.get(e)));
    }
    public void setSelectedTower(Node node){
        if (gameBoardGrid.getChildren().contains(selectedTower)){
            gameBoardGrid.getChildren().remove(selectedTower);
        }

        //System.out.println("HERERERE");

        //actual coordinates in the grid
        int logicalX = (int)(node.getLayoutX()/39);
        int logicalY = (int)(node.getLayoutY()/39);

        selectedTower = new Rectangle();

        selectedTower.setOpacity(0.5);
        selectedTower.setStyle("-fx-background-color: yellow");

        gameBoardGrid.setColumnIndex(selectedTower,logicalX);
        gameBoardGrid.setRowIndex(selectedTower,logicalY);

        gameBoardGrid.getChildren().add(selectedTower);
    }
    @Override
    public synchronized void update() {
        if(game.getEnemiesInWave() != null){
            Iterator<Enemy> enemyIterator = game.getEnemiesInWave().listIterator();
            while(enemyIterator.hasNext()){
                Enemy e = enemyIterator.next();
                if (!enemyHashMap.containsKey(e) && !progressBarHashMap.containsKey(e)) {

                    ImageView img = new ImageView(e.getImage());
                    fixImage(img, e);
                    enemyHashMap.put(e, img);
                    Platform.runLater(()->gameBoardAnchorPane.getChildren().add(img));
                    Platform.runLater(()->cave.toFront()); //sets the cave to be in front of the enemies
                    Platform.runLater(()->base.toFront());

                    ProgressBar pb = new ProgressBar((double) (e.getHealth()) / e.getMaxHealth());
                    pb.setLayoutX(e.getPositionX());
                    pb.setLayoutY(e.getPositionY());

                    pb.setMaxWidth(45);
                    pb.setMaxHeight(10);
                    pb.styleProperty().set("-fx-accent: red");

                    Platform.runLater(() -> gameBoardAnchorPane.getChildren().add(pb));
                    progressBarHashMap.put(e, pb);


                }
                updateEnemy(e);
                updateProgressBar(e);
            }
        }

    }

}
