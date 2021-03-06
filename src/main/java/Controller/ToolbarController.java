package main.java.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.java.Model.Game;
import main.java.Model.Towers.Targeting;
import main.java.Model.Towers.Tower;
import main.java.View.ToolbarHandler;

import java.io.IOException;

public class ToolbarController <T extends Tower> extends AnchorPane  {

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
    @FXML private ImageView lUImageView;
    @FXML private ImageView rUImageView;

    @FXML private RadioButton firstRadioButton;
    @FXML private RadioButton strongestRadioButton;
    @FXML private RadioButton closestRadioButton;

    private final ToggleGroup targetingToggleGroup;
    private Tower tower;
    private final Game game;
    private final MapController parentController;
    private final ToolbarHandler toolbarHandler;
    private final Image towerImage; //Stays here for future development (Upgrading tower images)

    public ToolbarController(Game game, MapController parentController, T t, String towerImage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Toolbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.game = game;
        this.parentController = parentController;
        this.tower = t;
        this.towerImage = new Image(towerImage);

        targetingToggleGroup = new ToggleGroup();
        toolbarHandler = new ToolbarHandler(game, tower,towerLabel,attackLabel,magicLabel,attackSpeedLabel,rangeLabel,
                leftUpgradeCostLabel,rightUpgradeCostLabel,sellButton,tImageView, leftUpgradeButton, rightUpgradeButton,
                this.towerImage, lUImageView, rUImageView);

        init();
        eventHandlers();
        updateToolbar();

    }



    private void eventHandlers(){
        closeButton.setOnMousePressed(mouseEvent -> parentController.moveToolbarBack());
    }

    
    private void init() throws Exception {
        strongestRadioButton.setToggleGroup(targetingToggleGroup);
        firstRadioButton.setToggleGroup(targetingToggleGroup);
        closestRadioButton.setToggleGroup(targetingToggleGroup);
        firstRadioButton.setSelected(true);



        toolbarHandler.setToolbarUpgradeView(tower);



    }


    private void updateToolbar(){
        toolbarHandler.setTextOfObjects();
        updateUpgradeAvailable();
        switch (tower.getTarget()) {
            case FIRST -> firstRadioButton.setSelected(true);
            case STRONGEST -> strongestRadioButton.setSelected(true);
            case CLOSEST -> closestRadioButton.setSelected(true);
        }

    }

    @FXML
    private void updateTargeting(){
        RadioButton r = (RadioButton)(targetingToggleGroup.getSelectedToggle());
        if(r.equals(firstRadioButton)){
            tower.setTarget(Targeting.FIRST);
        }
        if(r.equals(strongestRadioButton)){
            tower.setTarget(Targeting.STRONGEST);
        }
        if(r.equals(closestRadioButton)){
            tower.setTarget(Targeting.CLOSEST);
        }
    }

    @FXML
    private void sellTowerClicked(){

        //Move toolbarback
        parentController.moveToolbarBack();

        //Set tower cell to available
        tower.getPosition().setOccupiedFalse(); // should be replaced to avoid method chaining

        //Removes image of tower from grid
        parentController.removeImageFromGrid(tower);

        //Changes the money amount when selling
        game.addMoney((int)(tower.getPrice()*0.5));
        parentController.updateView();

        //Removes the tower and controller from the hashmap
        parentController.removeToolFromHash(tower);
        game.removeTower(tower);

        //Uppdates the upgrade view
        updateUpgradeAvailable();

    }

    @FXML
    private void towerUpgradeLeft(){
        if(game.getMoney() >= tower.getLeftUpgradeCost()){
            //Upgrades tower and updates the the controller
            this.tower = parentController.leftUpgradeTower(tower);

            //JavaFX
            toolbarHandler.updateLeftUpgrade();

            //Changes money
            game.addMoney(-tower.getLeftUpgradeCost());

            //JavaFX again
            updateToolbar();

            //updatesSidebar
            parentController.updateView();
        }
    }

    @FXML
    private void towerUpgradeRight(){
        if(game.getMoney() >= tower.getRightUpgradeCost()){

            //Upgrades tower and updates the the controller
            this.tower = parentController.rightUpgradeTower(tower);

            //JavaFX
            toolbarHandler.updateRightUpgrade();

            //Changes money
            game.addMoney(-tower.getLeftUpgradeCost());

            //JavaFX again
            updateToolbar();

            //updates Sidebar
            parentController.updateView();
        }

    }

    public void updateUpgradeAvailable() {
        toolbarHandler.updateUpgradeAvailable();
    }





}
