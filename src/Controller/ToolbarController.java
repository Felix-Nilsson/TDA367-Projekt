package Controller;

import Model.Game;
import Model.Towers.ArcherTower;
import Model.Towers.Tower;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ToolbarController extends AnchorPane implements Observer {
    @FXML private Label towerLabel;
    @FXML private Label attackLabel;
    @FXML private Label magicLabel;
    @FXML private Label rangeLabel;
    @FXML private Label leftUpgradeCostLabel;
    @FXML private Label rightUpgradeCostLabel;

    @FXML private Button sellButton;
    @FXML private Button leftUpgradeButton;
    @FXML private Button rightUpgradeButton;

    @FXML private ImageView towerImageView;

    @FXML private RadioButton firstRadioButton;
    @FXML private RadioButton strongestRadioButton;
    @FXML private RadioButton closestRadioButton;

    private final Game game;
    private final MapController parentController;

    private ArcherTower tower; // temp should be more general

    public ToolbarController(Game game, MapController parentController){
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

        init();
    }

    @Override
    public void update() {

    }
    
    private void init(){
        towerImageView.setImage(tower.getImage());
        //TODO: init all attributes w tower
    }

    //TODO add connection so that testing ingame is possible
}
