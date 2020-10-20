package Controller;

import Model.*;
import Model.Cell.Cell;
import Model.Enemy.Enemy;
import Model.Towers.Tower;
import Model.Towers.TowerFactory;
import View.MapHandler;
import View.ProjectileHandler;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;

import java.net.URL;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


public class MapController extends AnchorPane {
    @FXML
    private GridPane gameBoardGrid;
    @FXML
    private Rectangle tile;
    @FXML
    private ImageView toolbarBackgroundImage;
    @FXML
    private Label money;
    @FXML
    private Label waveNumber;

    @FXML
    private Button continueButton;
    @FXML
    private Button home;
    @FXML
    private Button exit;
    @FXML
    private Button restart;

    @FXML
    private RadioButton gridLayoutRadioButton;

    @FXML private RadioButton autoStartRadioButton;

    @FXML
    private AnchorPane sidebar;
    @FXML
    private AnchorPane settings;
    @FXML
    private AnchorPane settingsPane;
    @FXML
    private AnchorPane mapAnchorPane;
    @FXML
    private AnchorPane toolbarAnchorPane;
    @FXML
    private AnchorPane toolbarCover;
    @FXML
    private AnchorPane gameBoardAnchorPane;
    @FXML
    private GridPane toplayerGrid;

    @FXML
    private Pane gameOverScreen;
    @FXML
    private Pane gameWonScreen;

    private boolean paused = false;
    private final Game game;

    private List<Enemy> enemies;

    private HashMap<Enemy,ImageView> enemyHashMap;
    private final HashMap<Tower,ImageView> towerHashMap;  //If we want different images for upgarded towers, this need to be updated, however this wont be done here
    private final HashMap<Tower, ToolbarController> toolbarTowerHashMap;
    private boolean waveRunning;
    //private ImageView cave;
    //private ImageView base;
    private final MapHandler mapHandler;

    private TowerFactory towerFactory;
    private final MenuController parentController;

    private ProgressBar pb;
    private HashMap<Enemy, ProgressBar> progressBarHashMap;

    public MapController(Game game, List<Cell> map, MenuController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/fxml/Map.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.parentController = parentController;
        this.game = game;
        this.mapHandler = new MapHandler(game,waveNumber,gameOverScreen,gameWonScreen,gameBoardAnchorPane, gameBoardGrid, toplayerGrid, map);
        towerHashMap = new HashMap<>(); //Might need to move
        toolbarTowerHashMap = new HashMap<>();//Same
        progressBarHashMap = new HashMap<>();
        createSidebar();

        eventHandlers();
        new ProjectileHandler(this.game, gameBoardAnchorPane);
        playBackgroundMusic();
    }

    private void eventHandlers() {
        //EventHandlers

        //When dragged over GridPane
        gameBoardGrid.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != gameBoardGrid) {
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
                    boolean occupied = game.getCellOccupied(index);

                    if (!occupied && towerFactory.getPrice() <= game.getMoney()) {
                        //Place the image in the cell
                        ImageView image = new ImageView(db.getImage());

                        gameBoardGrid.add(image, x_placement, y_placement);
                        setTowerOnCell(index);

                        //After the tower has been added, add to hashmap
                        towerHashMap.put(game.getTower(x_placement, y_placement), image);

                        //Creates a new toolbar with the tower
                        createToolbar(game.getTower(x_placement, y_placement));

                        //Change money
                        game.addMoney(-towerFactory.getPrice());
                        updateSidebar(); //TODO Error here, need to update Toolbar Handler

                        //Updates the previous toolbarcontroller
                        updateToolbar();

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

                Tower t = game.getTower(x_placement, y_placement);
                System.out.println("inte labans prints hehe: "
                        + node.getLayoutX() + " " + node.getLayoutY() + " " + node);
                mapHandler.setSelectedTower(node);


                if (t != null) {
                    moveToolbarFront();
                    setToolCont(t);
                }
            }
        });


    }

    @FXML
    private void changeGridVisibilty() {
        mapHandler.changeToplayerGridVisible(gridLayoutRadioButton.isSelected());
    }
    @FXML private void autoStartPressed(){
        game.setAutostart(autoStartRadioButton.isSelected());
    }

    private void playBackgroundMusic(){
        URL resource = getClass().getResource("/View/sound/game_music.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }
    private int getGridX(Node node) {
        Integer cIndex = GridPane.getColumnIndex(node);
        return cIndex == null ? 0 : cIndex;
    }

    private int getGridY(Node node) {
        Integer rIndex = GridPane.getRowIndex(node);
        return rIndex == null ? 0 : rIndex;
    }

    private <T extends Tower> void createToolbar(T t) {
        ToolbarController<T> toolbarController = new ToolbarController(game, this, t);
        toolbarTowerHashMap.put(t, toolbarController);
    }

    private void setToolCont(Tower t) {
        toolbarAnchorPane.getChildren().clear();
        toolbarAnchorPane.getChildren().add(toolbarTowerHashMap.get(t));

    }

    public void removeToolFromHash(Tower t) {
        toolbarTowerHashMap.remove(t);
    }

    public void createSidebar() {
        SidebarController sidebarController = new SidebarController(game, this);
        sidebar.getChildren().add(sidebarController);
        mapHandler.createMap();
    }

    @FXML
    private void closeGame() {
        System.exit(0);
    }

    @FXML
    private void mainMenu() {
        parentController.openMenu();
    }

    @FXML
    private void restart() {
        parentController.newGame();

    }


    public void nextRound() {
        game.nextRound();
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
        return game.getWaveRunning();
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
        game.createTower(index,this.towerFactory);
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


    public void updateToolbar(){
        //Updates every controller in toolbarcontroller
        for(Map.Entry<Tower, ToolbarController> entry : toolbarTowerHashMap.entrySet()){
            entry.getValue().updateUpgradeAvaialble();
        }
    }

    private void updateToolbarHashmap(Tower oldT, Tower newT){
        //Uppdates the fking hashhhmap

        toolbarTowerHashMap.put(newT, toolbarTowerHashMap.get(oldT));
        toolbarTowerHashMap.remove(oldT, toolbarTowerHashMap.get(oldT));
        
        //Removes all null values
        toolbarTowerHashMap.entrySet().removeIf(e -> e.getValue() == null);

    }

    private void updateTowerHashmap(Tower oldT, Tower newT){
        //Uppdates the fking hashhhmap

        towerHashMap.put(newT, towerHashMap.get(oldT));
        towerHashMap.remove(oldT, towerHashMap.get(oldT));

        //Removes all null values
        towerHashMap.entrySet().removeIf(e -> e.getValue() == null);
    }


    public Tower leftUpgradeTower(Tower oldT){
        Tower newT = game.leftUpgradeTower(oldT);
        updateToolbarHashmap(oldT, newT);
        updateTowerHashmap(oldT, newT);
        return newT;
    }

    public Tower rightUpgradeTower(Tower oldT){
        Tower newT = game.rightUpgradeTower(oldT);
        updateToolbarHashmap(oldT, newT);
        updateTowerHashmap(oldT, newT);
        return newT;
    }


}

