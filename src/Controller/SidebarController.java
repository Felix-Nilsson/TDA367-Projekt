package Controller;

import Model.Game;
import Model.Observable;

import Model.Towers.*;
//import View.Observer;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.event.EventHandler;



import Model.WaveManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;


public class SidebarController extends AnchorPane implements Observer {
    @FXML private ImageView sidebarBackground;
    @FXML private ImageView moneyIcon;
    @FXML private ImageView healthIcon;
    @FXML private ImageView mageTower;
    @FXML private ImageView archerTower;
    @FXML private ImageView playButtonImg;

    @FXML private AnchorPane sidebarAnchorPane;
    @FXML private Label health;
    @FXML private Button play;
    @FXML private GridPane gridPane;
    @FXML private Label money;
    @FXML private AnchorPane toolbar;


    private final Game game;
    private final MapController parentController;



    public SidebarController(Game game,MapController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Sidebar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.game = game;
        this.parentController = parentController;
        game.addObserver(this);

        mageTower.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                Image tempIMG = new Image("/img/mageTower.png", 40, 40, false, false, false);
                content.putImage(tempIMG);
                db.setContent(content);
                mouseEvent.consume();

                MageTowerFactory mf = new MageTowerFactory();
                sendTowerToMap(mf);

            }
        });
        archerTower.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                Image tempIMG = new Image("/img/archerTower.png", 40, 40, false, false, false);
                content.putImage(tempIMG);
                db.setContent(content);
                mouseEvent.consume();

                ArcherTowerFactory af = new ArcherTowerFactory();
                sendTowerToMap(af);
            }
        });

    }
    private <TF extends TowerFactory> void sendTowerToMap(TF towerFactory){
        parentController.receiveTowerFactory(towerFactory);
    }

    public void update(){
        Platform.runLater(()->money.setText(""+ game.getMoney()));
        Platform.runLater(()->health.setText(""+ game.getHealth()));
        /*
        if(!parentController.isWaveRunning()){

            roundOver();
        }
        */

    }
    @FXML private void nextRound(){
        //pressed play
        if(!parentController.isWaveRunning()){
            parentController.nextRound();
            playButtonImg.setImage(new Image("/img/pause.png"));
        }
        //pressed pause
        else{
            System.out.println("pressed pause");
        }
    }
    public void roundOver(){
        playButtonImg.setImage(new Image("/img/play_button.png"));
    }

    @FXML private void settings(){
        parentController.openSettings();
    }

    @FXML public void toolbarButtonOnclick(){
        toolbar.toFront();
    }






}
