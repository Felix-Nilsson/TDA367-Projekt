package Controller;

import Model.*;
import Model.Cell.Cell;
import Model.Enemy.Enemy;
import Model.Towers.Tower;
import Model.Towers.TowerFactory;
import View.MapHandler;
import View.ViewManager1;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javax.tools.Tool;
import java.awt.*;
import java.io.IOException;

import java.util.HashMap;

import java.util.List;


public class MapController extends AnchorPane implements Observer {
    @FXML private GridPane gameBoardGrid;
    @FXML private Rectangle tile;
    @FXML private ImageView toolbarBackgroundImage;
    @FXML private Label money;
    @FXML private Label waveNumber;

    @FXML private Button continueButton;
    @FXML private Button home;
    @FXML private Button exit;
    @FXML private Button restart;

    @FXML private RadioButton gridLayoutRadioButton;

    @FXML private AnchorPane sidebar;
    @FXML private AnchorPane settings;
    @FXML private AnchorPane settingsPane;
    @FXML private AnchorPane mapAnchorPane;
    @FXML private AnchorPane toolbarAnchorPane;
    @FXML private AnchorPane toolbarCover;
    @FXML private AnchorPane gameBoardAnchorPane;
    @FXML private GridPane toplayerGrid;

    @FXML private Pane gameOverScreen;
    @FXML private Pane gameWonScreen;

    private boolean paused = false;
    private final Game game;

    private List<Enemy> enemies;
    private HashMap<Enemy,ImageView> enemyHashMap;
    private final HashMap<Tower,ImageView> towerHashMap;
    private final HashMap<Tower, ToolbarController> toolbarTowerHashMap;
    //private ImageView cave;
    //private ImageView base;
    private final MapHandler mapHandler;
    private TowerFactory towerFactory;
    private final MenuController parentController;

    private  ProgressBar pb;
    private HashMap<Enemy,ProgressBar> progressBarHashMap;

    public MapController(Game game, List<Cell> map,MenuController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Map.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.parentController = parentController;
        this.game = game;
        this.mapHandler = new MapHandler(gameBoardAnchorPane, gameBoardGrid, toplayerGrid,map);
        towerHashMap = new HashMap<>(); //Might need to move
        toolbarTowerHashMap = new HashMap<>();//Same
        progressBarHashMap = new HashMap<>();

        game.addObserver(this);
        createSidebar();

        eventHandlers();
        new ViewManager1(this.game, gameBoardAnchorPane);
    }
    private void eventHandlers(){
        //EventHandlers

        //When dragged over GridPane
        gameBoardGrid.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if(dragEvent.getGestureSource() != gameBoardGrid){
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                dragEvent.consume();
            }
        });


        //Upon releasing the mouse over a specific node
        gameBoardGrid.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                Node node = dragEvent.getPickResult().getIntersectedNode();

                if (node != gameBoardGrid && db.hasImage()) {

                    //Find Cell to place the tower
                    int x_placement = getGridX(node);
                    int y_placement = getGridY(node);

                    //Check if the cell is available
                    int index = game.getArrayIndex(x_placement, y_placement);
                    boolean occupied = game.isCellOccupied(index);
                    if (!occupied && towerFactory.getPrice()<=game.getMoney()) {
                        //Place the image in the cell
                        ImageView image = new ImageView(db.getImage());

                        gameBoardGrid.add(image, x_placement, y_placement); // Just adds an image to the gridpane grid
                        setTowerOnCell(index);

                        //After the tower has been added, add to hashmap
                        towerHashMap.put(game.getTowerInCell(x_placement, y_placement), image);

                        //Change money
                        game.addMoney(- towerFactory.getPrice());
                        updateSidebar();

                        //Creates a new toolbar with the tower
                        createToolbar(game.getTowerInCell(x_placement, y_placement));


                    } else {
                        //TODO Some sort of error or could just leave it empty
                    }



                }

                dragEvent.setDropCompleted(true);
                dragEvent.consume();
            }
        });

        gameBoardGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Node node = mouseEvent.getPickResult().getIntersectedNode();

                int x_placement = getGridX(node);
                int y_placement = getGridY(node);

                Tower t = game.getTowerInCell(x_placement, y_placement);
                if(t != null){
                    moveToolbarFront();
                    setToolCont(t);
                }
            }
        });

        


    }

    @FXML private void changeGridVisibilty(){
        mapHandler.changeToplayerGridVisible(gridLayoutRadioButton.isSelected());
    }
    private int getGridX(Node node){
        Integer cIndex = GridPane.getColumnIndex(node);
        int x = cIndex == null ? 0 :cIndex;
        return x;
    }

    private int getGridY(Node node){
        Integer rIndex = GridPane.getRowIndex(node);
        int y = rIndex == null ? 0 :rIndex;
        return y;
    }

    private <T extends Tower> void createToolbar(T t){
        ToolbarController<T> toolbarController = new ToolbarController(game, this, t);
        toolbarTowerHashMap.put(t, toolbarController);
    }

    private void setToolCont(Tower t){
        toolbarAnchorPane.getChildren().clear();
        toolbarAnchorPane.getChildren().add(toolbarTowerHashMap.get(t));
    }

    public void removeToolFromHash(Tower t){
        toolbarTowerHashMap.remove(t);
    }

    public void createSidebar(){
        //add sidebar fxml
        SidebarController sidebarController = new SidebarController(game, this);
        sidebar.getChildren().add(sidebarController);
        int startPos = game.getStartPos();
        int endPos = game.getEndPos();

        //Creating map

        mapHandler.createMap(startPos,endPos);
    }
    @FXML private void closeGame(){
        System.exit(0);}
    @FXML private void mainMenu(){
        parentController.openMenu();
    }
    @FXML private void restart(){
        parentController.newGame();

    }

    public void nextRound(){
        waveNumber.setText("Wave: " + game.getRound());
        enemyHashMap = new HashMap<>();
        game.nextRound();

    }

    @Override
    public void notifyGameOver() {
        Platform.runLater(()->gameOverScreen.toFront());
    }


    @Override
    public void notifyRoundOver() {

    }

    @Override
    public void notifyGameWon() {
        Platform.runLater(()->gameWonScreen.toFront());
    }

    /**
     * prints out enemies from (game : enemiesInWave)
     * or if enemy already exist, then update x y position of enemy image
     */
    public void update(){

        if (game.getEnemiesInWave()!= null ) {
            if(game.getEnemiesInWave().size() > 0){
                for(Enemy e : game.getEnemiesInWave()){
                    if(e.isDead()){
                        Platform.runLater(()->gameBoardAnchorPane.getChildren().remove(enemyHashMap.get(e)));
                        game.getEnemiesInWave().remove(e);
                        game.enemyIsOut(e);
                        break;
                    }
                    else{
                        if (!enemyHashMap.containsKey(e) && !progressBarHashMap.containsKey(e)) {

                            ImageView img = new ImageView(e.getImage()); //TODO change later if keeping maphandler
                            fixImage(img,e);
                            enemyHashMap.put(e,img);
                            mapHandler.drawEnemy(img);

                            ProgressBar pb = new ProgressBar((double)(e.getHealth())/e.getMaxHealth());
                            pb.setMaxWidth(45);
                            pb.setMaxHeight(10);
                            pb.styleProperty().set("-fx-accent: red");

                            pb.setLayoutX(e.getPositionX());
                            pb.setLayoutY(e.getPositionY());

                            Platform.runLater(()->gameBoardAnchorPane.getChildren().add(pb));
                            progressBarHashMap.put(e,pb);


                        }
                        mapHandler.updateEnemy(enemyHashMap,e);
                        mapHandler.updateProgressBar(progressBarHashMap,e);

                    }

                }
            }
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

    protected void pause(){
        game.pause();
        paused = true;
    }
    protected void play(){
        game.play();
        paused = false;
    }

    protected boolean isWaveRunning(){
        return game.isWaveRunning();
    }

    public void openSettings(){
        if(isWaveRunning()){
            pause();
        }
        mapHandler.openSettings(settingsPane);
    }


    @FXML private void openMap(){
        if (paused){
            play();
        }
        mapHandler.closeSettings(mapAnchorPane);
    }


    public void setTowerOnCell(int index){
        game.updateArrayWithTower(index,this.towerFactory);
    }

    public void receiveTowerFactory(TowerFactory towerFactory){
        this.towerFactory = towerFactory;
    }

    public void moveToolbarBack(){
        mapHandler.moveToolbarBack(toolbarAnchorPane, toolbarCover);
    }

    public void moveToolbarFront(){
        mapHandler.moveToolbarFront(toolbarAnchorPane, toolbarCover);
    }

    public void removeImageFromGrid(Tower t){
        ImageView image = towerHashMap.get(t);
        mapHandler.removeImageFromGrid(image);
        towerHashMap.remove(t);

    }


    public void updateSidebar(){
        game.notifyAllObservers();
    }
    protected List<Enemy> getEnemies(){
        return game.getEnemiesInWave();
    }

}

