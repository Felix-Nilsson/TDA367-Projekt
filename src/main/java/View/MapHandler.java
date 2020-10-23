package main.java.View;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import main.java.Model.Cell.Cell;
import main.java.Model.Enemy.Enemy;
import main.java.Model.Game;

import java.util.HashMap;
import java.util.List;

public class MapHandler implements MapObserver {

    private final AnchorPane gameBoardAnchorPane;
    private final GridPane gameBoardGrid;
    private final GridPane toplayerGrid;
    private final Pane gameWonScreen;
    private final Pane gameOverScreen;
    private HashMap<Enemy, ImageView> enemyHashMap;
    private final Label waveNumber;

    private RadioButton gridLayout;
    private RadioButton autoStart;


    private final List<Cell> map;
    private ImageView cave;
    private ImageView base;
    private final Game game;
    private HashMap<Enemy, ProgressBar> progressBarHashMap;
    private Label nameOfMapLabel;
    private Label difficultyLabel;

    private Rectangle selectedTower;


    //TODO: Add a blurb justifying design choices


    public MapHandler(Game game, Label nameOfMapLabel,Label difficultyLabel, Label waveNumber, Pane gameOverScreen, Pane gameWonScreen, AnchorPane gameBoardAnchorPane,
                      GridPane gameBoardGrid, GridPane toplayerGrid, List<Cell> map,RadioButton gridLayout, RadioButton autoStart){
        this.gameBoardAnchorPane = gameBoardAnchorPane;
        this.gameBoardGrid = gameBoardGrid;
        this.toplayerGrid = toplayerGrid;
        this.map = map;
        this.game = game;
        this.gameOverScreen = gameOverScreen;
        this.gameWonScreen = gameWonScreen;
        this.waveNumber = waveNumber;
        this.nameOfMapLabel = nameOfMapLabel;
        this.difficultyLabel = difficultyLabel;
        this.gridLayout = gridLayout;
        this.autoStart = autoStart;
        progressBarHashMap = new HashMap<>();
        enemyHashMap = new HashMap<>();

        game.addMapObserver(this);
        setLabels();

    }
    private void setLabels(){
        nameOfMapLabel.setText(game.getNameOfMap());

        difficultyLabel.setText(game.getDifficulty().name());
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

            switch (p.getTerrainType()){
                case PATH -> tile.setFill(Paint.valueOf("78410a")); //brown
                case WATER -> tile.setFill(Paint.valueOf("0f79ba")); //blue
                case GROUND -> tile.setFill(Paint.valueOf("489a49")); //green
                case OBSTACLE -> tile.setFill(Paint.valueOf("696969")); //grey
            }
            //tile.setFill(Color.web(p.getColor()));
            gameBoardGrid.add(tile, p.getX(), p.getY());
        }
    }



    public void changeToplayerGridVisible(boolean visible){
        toplayerGrid.setGridLinesVisible(visible);
        gridLayout.getStyleClass().clear();
        if(visible){
            gridLayout.getStyleClass().add("RadioButtonSelected");
        }
        else{
            gridLayout.getStyleClass().add("RadioButtonNotSelected");
        }

    }
    public void autoStartView(){
        autoStart.getStyleClass().clear();
        if(autoStart.isSelected()){
            autoStart.getStyleClass().add("RadioButtonSelected");
        }
        else{
            autoStart.getStyleClass().add("RadioButtonNotSelected");
        }
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

    public void moveToolbarBack(AnchorPane toolbarAnchorPane){
        toolbarAnchorPane.toBack();

    }

    public void moveToolbarFront(AnchorPane toolbarAnchorPane){
        toolbarAnchorPane.toFront();

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

        //actual coordinates in the grid
        int logicalX = (int)(node.getLayoutX()/39);
        int logicalY = (int)(node.getLayoutY()/39);

        selectedTower = new Rectangle();

        selectedTower.setOpacity(0.5);
        selectedTower.setStyle("-fx-background-color: yellow");

        GridPane.setColumnIndex(selectedTower,logicalX);
        GridPane.setRowIndex(selectedTower,logicalY);

        gameBoardGrid.getChildren().add(selectedTower);
    }
    @Override
    public  void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(game.getEnemiesInWave()!=null) {
                    synchronized (game.getEnemiesInWave()) {
                        for (Enemy e : game.getEnemiesInWave()) {
                            if (!enemyHashMap.containsKey(e) && !progressBarHashMap.containsKey(e)) {
                                EnemyView enemyImage= new EnemyView(e);
                                ImageView img = null;
                                try {
                                    img = new ImageView(enemyImage.getImage());
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                                fixImage(img, e);
                                enemyHashMap.put(e, img);
                                ImageView finalImg = img;
                                Platform.runLater(() -> gameBoardAnchorPane.getChildren().add(finalImg));
                                Platform.runLater(() -> cave.toFront()); //sets the cave to be in front of the enemies
                                Platform.runLater(() -> base.toFront());

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
        });

    }

}
