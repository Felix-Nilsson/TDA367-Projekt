package Controller;
import Model.*;
import Model.Cell.Cell;
import Model.Enemy.BlueEnemy;
import Model.Enemy.Enemy;
import Model.Towers.Tower;
import Model.Towers.TowerFactory;
import View.MapHandler;
import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


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
    @FXML private AnchorPane toolbarAnchorPane;
    @FXML private AnchorPane toolbarCover;
    @FXML private AnchorPane gameBoardAnchorPane;


    private final Game game;
    private ToolbarController toolbarController;
    private final List<Cell> map;
    private BlueEnemy tmpEnemy;
    //private List<ImageView> enemyImages;
    private List<Enemy> enemies;
    private HashMap<Enemy,ImageView> enemyHashMap;
    private HashMap<Tower,ImageView> towerHashMap;
    private boolean waveRunning;
    private ImageView cave;
    private ImageView base;
    private MapHandler mapHandler;


    private TowerFactory towerFactory;
    public MapController(Game game, List<Cell> map) {

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
        this.waveRunning = game.isWaveRunning();
        this.mapHandler = new MapHandler(gameBoardAnchorPane, gameBoardGrid, map);
        towerHashMap = new HashMap<>(); //Might need to move
        game.addObserver(this);
        createMap();
        addToolbar();
        eventHandlers();
        
    }
    @FXML private void pauseGame(){
        game.pauseGame();
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

        //When entering a node in GridPane
        gameBoardGrid.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                //TODO add a image to the cell that is hovered over
            }
        });

        //When exiting a node in GridPane
        gameBoardGrid.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                //TODO Remove the image from the cell when the hover leaves
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
                    if (occupied == false) {
                        //Place the image in the cell
                        ImageView image = new ImageView(db.getImage());

                        gameBoardGrid.add(image, x_placement, y_placement); // Just adds an image to the gridpane grid
                        setTowerOnCell(index);

                        //After the tower has been added, add to hashmap
                        towerHashMap.put(game.getTowerInCell(x_placement, y_placement), image);

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
                    moveToolbarFront(t);
                }
            }
        });

        


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

    private void addToolbar(){
        toolbarController = new ToolbarController(game,this);
        toolbarAnchorPane.getChildren().add(toolbarController);

    }

    public void createMap(){
        //add sidebar fxml
        SidebarController sidebarController = new SidebarController(game,this);
        sidebar.getChildren().add(sidebarController);
        int startPos = game.getStartPos();
        int endPos = game.getEndPos();
        mapHandler.createMap(startPos,endPos, cave, base);

    }

    public void nextRound(){
        waveRunning = true;
        enemyHashMap = new HashMap<>();
        game.nextRound();
        if(game.getEnemiesInWave()!=null) {
            enemies = game.getEnemiesInWave();
            mapHandler.drawEnemies(enemies, enemyHashMap);
        }
        game.putEnemyInUpdateModel();
        game.run();
    }

    public void update(){
        if (enemies!= null ) {
            if(enemies.size() > 0){
                for(Enemy e : enemies){
                    if(e.isDead()){
                        Platform.runLater(()->gameBoardAnchorPane.getChildren().remove(enemyHashMap.get(e)));
                        enemies.remove(e);
                        game.enemyIsOut();
                        System.out.println("is dead");
                        break;
                    }
                    else{
                        mapHandler.updateEnemy(enemyHashMap, e);
                    }
                }
            }
            else{
                waveRunning = false;
                System.out.println("Round over");
                game.gameLoopThread.stop();
            }
        }
    }

    protected boolean isWaveRunning(){
        return waveRunning;
    }

    public void openSettings(){
        mapHandler.openSettings(settingsPane);
    }

    @FXML //TODO Not currently implementet
    public void openMap(){
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

    public void moveToolbarFront(Tower t){
        toolbarController.recieveTower(t);
        mapHandler.moveToolbarFront(toolbarAnchorPane, toolbarCover);
    }

    public void removeImageFromGrid(Tower t){
        ImageView image = towerHashMap.get(t);
        mapHandler.removeImageFromGrid(image);
        towerHashMap.remove(t);


    }



}

