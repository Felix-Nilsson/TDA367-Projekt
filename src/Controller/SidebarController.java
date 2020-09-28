package Controller;

import Model.Game;
import Model.Observable;

import Model.Towers.MageTower;
import View.Observer;
import javafx.event.EventHandler;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class SidebarController extends AnchorPane implements Observer {
    @FXML private ImageView sidebarBackground;
    @FXML private ImageView moneyIcon;
    @FXML private ImageView healthIcon;
    @FXML private ImageView mageTower;
    @FXML private ImageView archerTower;
    @FXML private ImageView playButton;

    @FXML private AnchorPane sidebarAnchorPane;
    @FXML private Label health;
    @FXML private Button nextRound;
    @FXML private GridPane gridPane;
    @FXML private Label money;

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
        game.getObservable().add(this);


    }
    public void update(){
        money.setText(""+ game.getMoney());
        health.setText(""+ game.getHealth());
    }
    @FXML private void settings(){
        parentController.openSettings();
    }

    @FXML private void dragTower(){
        mageTower.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                Image tempIMG = new Image("/img/mageTower.png", 40, 40, false, false, false);

                content.putImage(tempIMG);
                db.setContent(content);
                mouseEvent.consume();
            }
        });
    }



}
