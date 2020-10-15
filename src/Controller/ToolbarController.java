package Controller;

import Model.Game;
import Model.Towers.Targeting;
import Model.Towers.Tower;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ToolbarController <T extends Tower> extends AnchorPane implements Observer {
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
    private T tower;
    private final Game game;
    private final MapController parentController;

    public ToolbarController(Game game, MapController parentController, T t){
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
        this.tower = t;
        game.addObserver(this);
        targetingToggleGroup = new ToggleGroup();
        init();
        eventHandlers();
        updateToolbar();
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


    private void updateToolbar(){
        towerLabel.setText(tower.toString());
        tImageView.setImage(new Image(tower.getImage()));
        sellButton.setText("Sell: "+ (int)(tower.getPrice() * 0.5));

        magicLabel.setText("Magic: " + tower.getMagicDmg());
        attackLabel.setText("Physical: " + tower.getPhysicalDmg());
        attackSpeedLabel.setText("Speed: " + tower.getAttackSpeed());
        rangeLabel.setText("Range: " + tower.getRange());

        switch(tower.getTarget()){
            case FIRST: firstRadioButton.setSelected(true); break;
            case STRONGEST: strongestRadioButton.setSelected(true); break;
            case CLOSEST: closestRadioButton.setSelected(true); break;
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
        parentController.updateSidebar(); //Might cause cycle dependency

        //Removes the tower and controller from the hashmap
        parentController.removeToolFromHash(tower);
        game.removeTower(tower);


    }

}
