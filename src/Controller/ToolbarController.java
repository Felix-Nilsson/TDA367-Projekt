package Controller;

import Model.Game;
import Model.Observable;
import Model.Towers.ArcherTower;
import Model.Towers.Targeting;
import Model.Towers.Tower;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;

public class ToolbarController extends AnchorPane implements Observer {
    @FXML private AnchorPane toolbarPane;

    @FXML private Label towerLabel;
    @FXML private Label attackLabel;
    @FXML private Label magicLabel;
    @FXML private Label attackSpeedLabel;
    @FXML private Label rangeLabel;
    @FXML private Label leftUpgradeCostLabel;
    @FXML private Label rightUpgradeCostLabel;

    @FXML private Button sellButton;
    @FXML private Button leftUpgradeButton;
    @FXML private Button rightUpgradeButton;
    @FXML private Button closeButton;

    @FXML private ImageView tImageView;

    @FXML private RadioButton firstRadioButton;
    @FXML private RadioButton strongestRadioButton;
    @FXML private RadioButton closestRadioButton;

    private ToggleGroup targetingToggleGroup;

    private final Game game;
    private final MapController parentController;
    private Tower selectedTower; // temp should be more general

    public ToolbarController(Game game, MapController parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Toolbar.fxml"));
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
        targetingToggleGroup = new ToggleGroup();
        init();
        eventHandlers();

    }

    @Override
    public void update() {

    }

    private void eventHandlers(){
        closeButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentController.moveToolbarBack();
            }
        });
    }
    
    private void init(){
        strongestRadioButton.setToggleGroup(targetingToggleGroup);
        firstRadioButton.setToggleGroup(targetingToggleGroup);
        closestRadioButton.setToggleGroup(targetingToggleGroup);
        firstRadioButton.setSelected(true);

        //towerImageView.setImage(tower.getImage());
        //towerLabel.setText("Archer"); //temp

        //sellButton.setText((tower.getPrice()*(0.5)) + "");

    }

    public void recieveTower(Tower t){
        selectedTower = t;
        updateToolbar();
    }

    private void updateToolbar(){
        towerLabel.setText(selectedTower.toString());
        tImageView.setImage(selectedTower.getImage());
        sellButton.setText("Sell: "+ (int)(selectedTower.getPrice() * 0.5));

        magicLabel.setText("Magic: " + selectedTower.getMagicDmg());
        attackLabel.setText("Physical: " + selectedTower.getPhysicalDmg());
        attackSpeedLabel.setText("Speed: " + selectedTower.getAttackSpeed());
        rangeLabel.setText("Range: " + selectedTower.getRange());

        updateTargeting();

        switch(selectedTower.getTarget()){
            case FIRST: firstRadioButton.setSelected(true); break;
            case STRONGEST: strongestRadioButton.setSelected(true); break;
            case CLOSEST: closestRadioButton.setSelected(true); break;
        }
        //TODO Update tower with information that needs to be stoooooored
    }

    private void updateTargeting(){
        RadioButton r = (RadioButton)(targetingToggleGroup.getSelectedToggle());

        if(r.equals(firstRadioButton)){
            selectedTower.setTarget(Targeting.FIRST);
        }
        if(r.equals(strongestRadioButton)){
            selectedTower.setTarget(Targeting.STRONGEST);
        }
        if(r.equals(closestRadioButton)){
            selectedTower.setTarget(Targeting.CLOSEST);
        }
    }

}
