package View;

import Model.Game;
import Model.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;


public class SidebarController extends AnchorPane implements Observer{
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

    private Observable observable;
    private Game game;
    private MapController parentController;

    public SidebarController(Game game, Observable observable,MapController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sidebar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.game = game;
        this.observable = observable;
        this.parentController = parentController;
        observable.add(this);

    }
    public void update(){
        money.setText(""+ game.getMoney());
        health.setText(""+ game.getHealth());
    }
    @FXML private void settings(){
        parentController.openSettings();
    }





}
