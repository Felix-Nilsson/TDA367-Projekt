package Controller;
import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
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


    private final Game game;
    private SidebarController sidebarController;
    private int x_placement;
    private int y_placement;
    private final List<Cell> map;
    private BlueEnemy tmpEnemy;


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
        game.addObserver(this);
        createMap();
        addToolbar();


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
                    Integer cIndex = GridPane.getColumnIndex(node);
                    Integer rIndex = GridPane.getRowIndex(node);
                    x_placement = cIndex == null ? 0 : cIndex;
                    y_placement = rIndex == null ? 0 : rIndex;

                    //Check if the cell is available
                    int index = game.getArrayIndex(x_placement, y_placement);
                    boolean occupied = game.isCellOccupied(index);
                    if (occupied == false) {
                        //Place the image in the cell
                        ImageView image = new ImageView(db.getImage());
                        gameBoardGrid.add(image, x_placement, y_placement); // Just adds an image to the gridpane grid

                        setTowerOnCell(index); //TODO futher down
                    } else {
                        //TODO Some sort of error or could just leave it empty
                    }


                }


                dragEvent.setDropCompleted(true);
                dragEvent.consume();
            }
        });

        //Finns endast här för att genomföra tester. OBS! tmpEnemy.update() kallas 40 gånger innan man tryckt på start just nu och därför startar enemy på 65
        tmpEnemy = new BlueEnemy(10,1,1,1,25,105);
        tmpEnemy.setPath(game.getTmpBoard().getPath());
        mapAnchorPane.getChildren().add(tmpEnemy.getImageView());
        //fungerar som ett test: tryck på knapp så rör sig tmpEnemy.
        Button updateButton = new Button();
        mapAnchorPane.getChildren().add(updateButton);
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                System.out.println("just clicked");

                for (int i=0; i<40;i++){
                    tmpEnemy.update();
                }
            }
        });


    }
    private void addToolbar(){
        ToolbarController toolbarController = new ToolbarController(game,this);
        toolbarAnchorPane.getChildren().add(toolbarController);
    }

    public void createMap(){
        //add sidebar fxml
        SidebarController sidebarController = new SidebarController(game,this);
        sidebar.getChildren().add(sidebarController);

        //add all cells to GUI
        for (Cell p: map) {
            Rectangle tile = new Rectangle(40,40);
            tile.setX(p.getX());
            tile.setY(p.getY());
            tile.setFill(Color.web(p.getColor()));
            tile.setStroke(Color.BLACK);
            gameBoardGrid.add(tile, p.getX(), p.getY());
        }
    }
    //kallas i game loopen (bara 1 gång/sekund just nu)
    public void update(){
        for (int i=0; i<40;i++){
            tmpEnemy.update();
        }
    }


    public void openSettings(){
        settingsPane.toFront();
    }
    @FXML
    public void openMap(){
        mapAnchorPane.toFront();
    }



    private void setTowerOnCell(int index/*Tower tower */){
        //TODO Set Tower on the specified cell, should be done in Game, Send with index of array and the tower placed
        //TODO Call game.updateArrayWithTower to update what you want. Index is the place in the array that represents the cell that has the tower placed on it.
        //TODO REMEMBER to send some sort of identification for what sort of tower is placed e.g. String or int
    }


}

