package main.java.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import main.java.Model.Cell.Cell;
import main.java.Model.Enemy.Enemy;
import main.java.Model.Game;
import main.java.Model.Towers.Tower;
import main.java.Model.Towers.TowerFactory;
import main.java.View.MapHandler;
import main.java.View.ProjectileHandler;

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
    @FXML private Slider musicSlider;
    @FXML private Label nameOfMapLabel;
    @FXML private Label difficultyLabel;

    private boolean paused = false;
    private final Game game;

    private MediaPlayer mediaPlayer;

    private final HashMap<Tower,ImageView> towerHashMap;  //If we want different images for upgarded towers, this need to be updated, however this wont be done here
    private final HashMap<Tower, ToolbarController<?>> toolbarTowerHashMap;

    private final MapHandler mapHandler;

    private TowerFactory towerFactory;
    private final MenuController parentController;

    private ProgressBar pb;

    public MapController(Game game, List<Cell> map, MenuController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Map.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.parentController = parentController;
        this.game = game;
        this.mapHandler = new MapHandler(game,nameOfMapLabel,difficultyLabel,waveNumber,gameOverScreen,gameWonScreen,gameBoardAnchorPane, gameBoardGrid, toplayerGrid, map,gridLayoutRadioButton,autoStartRadioButton);
        towerHashMap = new HashMap<>(); //Might need to move
        toolbarTowerHashMap = new HashMap<>();//Same
        HashMap<Enemy, ProgressBar> progressBarHashMap = new HashMap<>();
        createSidebar();

        eventHandlers();
        new ProjectileHandler(this.game, gameBoardAnchorPane);
        playBackgroundMusic();
    }

    private void eventHandlers() {
        //EventHandlers

        //When dragged over GridPane
        gameBoardGrid.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != gameBoardGrid) {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            dragEvent.consume();
        });


        //Upon releasing the mouse over a specific node
        gameBoardGrid.setOnDragDropped(dragEvent -> {
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
                    synchronized (towerHashMap) {
                        towerHashMap.put(game.getTower(x_placement, y_placement), image);
                    }

                    //Creates a new toolbar with the tower
                    try {
                        createToolbar(game.getTower(x_placement, y_placement), db.getString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Change money
                    game.addMoney(-towerFactory.getPrice());
                    updateView();

                    //Updates the previous toolbarcontroller
                    updateToolbar();

                } else {
                    try {
                        throw new Exception("Tower Placement Error: Can't place tower there!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            dragEvent.setDropCompleted(true);
            dragEvent.consume();
        });

        gameBoardGrid.setOnMouseClicked(mouseEvent -> {
            Node node = mouseEvent.getPickResult().getIntersectedNode();

            int x_placement = getGridX(node);
            int y_placement = getGridY(node);

            Tower t = game.getTower(x_placement, y_placement);
            mapHandler.setSelectedTower(node);


            if (t != null) {
                moveToolbarFront();
                setPaneWithController(t);
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
    @FXML private void setBackgroundSound(){
       mediaPlayer.setVolume(musicSlider.getValue());
    }
    private void playBackgroundMusic(){
        URL resource = getClass().getResource("/sound/game_music.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        musicSlider.setValue(1);
        musicSlider.setMax(0.05);
        musicSlider.setMin(0);
        mediaPlayer.setVolume(musicSlider.getMax());
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
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

    private <T extends Tower> void createToolbar(T t, String imageUrl) throws Exception {
        ToolbarController<?> toolbarController = new ToolbarController<>(game, this, t, imageUrl);
        toolbarTowerHashMap.put(t, toolbarController);
    }

    private void setPaneWithController(Tower t) {
        toolbarAnchorPane.getChildren().clear();
        toolbarAnchorPane.getChildren().add(toolbarTowerHashMap.get(t));

    }

    public void removeToolFromHash(Tower t) {
        synchronized (toolbarTowerHashMap){
            toolbarTowerHashMap.remove(t);
        }
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
        mediaPlayer.stop();
    }

    @FXML
    private void restart() {
        parentController.newGame();
        mediaPlayer.stop();

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
        mapHandler.moveToolbarBack(toolbarAnchorPane);
    }

    public void moveToolbarFront(){
        mapHandler.moveToolbarFront(toolbarAnchorPane);
    }

    public void removeImageFromGrid(Tower t){
        ImageView image = towerHashMap.get(t);
        mapHandler.removeImageFromGrid(image);
        synchronized (towerHashMap){
            towerHashMap.remove(t);
        }

    }

    public void updateView(){
        game.notifyAllObservers();
    }

    protected List<Enemy> getEnemies(){
        return game.getEnemiesInWave();
    }


    public void updateToolbar(){
        for(Map.Entry<Tower, ToolbarController<?>> entry : toolbarTowerHashMap.entrySet()){
            entry.getValue().updateUpgradeAvailable();
        }
    }

    private void updateToolbarHashmap(Tower oldT, Tower newT){
        toolbarTowerHashMap.put(newT, toolbarTowerHashMap.get(oldT));
        toolbarTowerHashMap.remove(oldT, toolbarTowerHashMap.get(oldT));
        toolbarTowerHashMap.entrySet().removeIf(e -> e.getValue() == null);

    }

    private void updateTowerHashmap(Tower oldT, Tower newT){
        towerHashMap.put(newT, towerHashMap.get(oldT));
        towerHashMap.remove(oldT, towerHashMap.get(oldT));
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

