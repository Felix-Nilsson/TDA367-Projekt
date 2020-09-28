package Controller;

import Model.*;
//import View.Observer;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
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

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.ls.LSOutput;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;


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



    private final ArrayList<Cell> map;
    private final Game game;
    private SidebarController sidebarController;
    //private final Observable observable;
    private int x_placement;
    private int y_placement;



    public MapController(Game game, ArrayList<Cell> map) {
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
        game.getObservable().add(this);

        createMap();

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
                System.out.println("hej");
                Dragboard db = dragEvent.getDragboard();
                Node node = dragEvent.getPickResult().getIntersectedNode();



                if(node != gameBoardGrid && db.hasImage()){



                    //Find Cell to place the tower
                    Integer cIndex = GridPane.getColumnIndex(node);
                    Integer rIndex = GridPane.getRowIndex(node);
                    x_placement = cIndex == null ? 0 : cIndex;
                    y_placement = rIndex == null ? 0 : rIndex;

                    //Check if the cell is available
                    int index = game.getArrayIndex(x_placement, y_placement);
                    boolean occupied = game.isCellOccupied(index);
                   


                    //Place the image in the cell
                    ImageView image = new ImageView(db.getImage());
                    gameBoardGrid.add(image, x_placement, y_placement ); // Just adds an image to the gridpane grid

                    //TODO Update the cell with the tower MIGHT MAKE METHOD

                    setTowerOnCell(index); //TODO futher down

                }


                dragEvent.setDropCompleted(true);
                dragEvent.consume();
            }
        });


    }

    public void createMap(){
        //add sidebar fxml
        SidebarController sidebarController = new SidebarController(game,this);
        sidebar.getChildren().add(sidebarController);

        //add all cells to GUI
        for (Cell p: map) {
            Rectangle tile = new Rectangle(50,50);
            tile.setX(p.getX());
            tile.setY(p.getY());
            tile.setFill(Color.web(p.getColor()));
            tile.setStroke(Color.BLACK);
            gameBoardGrid.add(tile, p.getX(), p.getY());
        }
    }
    public void update(){

    }
    public void openSettings(){
        settingsPane.toFront();
    }
    @FXML
    public void openMap(){
        mapAnchorPane.toFront();
    }


    private void setTowerOnCell(int index){
        //TODO Set Tower on the specified cell, should be done in Game
    }

}

