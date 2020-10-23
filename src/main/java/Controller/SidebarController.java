package main.java.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.java.Model.Game;
import main.java.Model.Towers.ArcherTowerFactory;
import main.java.Model.Towers.MageTowerFactory;
import main.java.Model.Towers.TowerFactory;
import main.java.View.SidebarHandler;

import java.io.IOException;


public class SidebarController extends AnchorPane  {
    @FXML private ImageView sidebarBackground;
    @FXML private ImageView moneyIcon;
    @FXML private ImageView healthIcon;
    @FXML private ImageView mageTower;
    @FXML private ImageView archerTower;
    @FXML private ImageView playButtonImg;

    @FXML private AnchorPane sidebarAnchorPane;
    @FXML private AnchorPane mageTowerAvailable;
    @FXML private AnchorPane archerTowerAvailable;
    @FXML private Label health;
    @FXML private Button play;
    @FXML private GridPane gridPane;
    @FXML private Label money;
    @FXML private AnchorPane toolbar;
    @FXML private Label archerTowerPriceLabel;
    @FXML private Label mageTowerPriceLabel;

    private final MapController parentController;
    private final SidebarHandler sidebarHandler;

    public SidebarController(Game game,MapController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Sidebar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.parentController = parentController;
        sidebarHandler = new SidebarHandler(game, mageTowerPriceLabel, archerTowerPriceLabel, health,money,playButtonImg,mageTowerAvailable,archerTowerAvailable);
        eventHandlers();
        updateAvailable();
    }
    private void eventHandlers(){
        mageTower.setOnDragDetected(mouseEvent -> {

            Dragboard db = startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            String imageURL = "/img/mageTower.png";
            Image tempIMG = new Image(imageURL, 40, 40, false, false, false);
            content.putString(imageURL);
            content.putImage(tempIMG);
            db.setContent(content);
            mouseEvent.consume();

            MageTowerFactory mf = new MageTowerFactory();
            sendTowerToMap(mf);
            updateAvailable();
            sidebarHandler.updatePlayerStats();
        });
        archerTower.setOnDragDetected(mouseEvent -> {
            Dragboard db = startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            String imageURL = "/img/archerTower.png";
            Image tempIMG = new Image(imageURL, 40, 40, false, false, false);
            content.putString(imageURL);
            content.putImage(tempIMG);
            db.setContent(content);
            mouseEvent.consume();

            ArcherTowerFactory af = new ArcherTowerFactory();
            sendTowerToMap(af);
            updateAvailable();
            sidebarHandler.updatePlayerStats();
        });
    }

    private <TF extends TowerFactory> void sendTowerToMap(TF towerFactory){
        parentController.receiveTowerFactory(towerFactory);
    }

    @FXML private void nextRound(){
        //pressed play
        if(!parentController.isWaveRunning()){
            //if there exists enemies on map
            if(parentController.getEnemies() != null){
                if(parentController.getEnemies().size() > 0 ){
                    sidebarHandler.setPauseImg();
                    parentController.play();
                }
                else{
                    sidebarHandler.setPauseImg();
                    parentController.nextRound();
                }
            }
            //first round when enemy list is null
            else{
                parentController.nextRound();
                sidebarHandler.setPauseImg();
            }

        }
        //pressed pause
        else{
            sidebarHandler.setPlayImg();
            parentController.pause();
        }
    }


    @FXML private void settings(){
        parentController.openSettings();
    }


    @FXML public void updateAvailable(){
        sidebarHandler.updateAvailable();
    }

}
