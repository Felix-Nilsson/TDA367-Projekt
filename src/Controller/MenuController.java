package Controller;

import Controller.MapController;
import Model.Difficulty;
import Model.Game;
import View.MenuHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuController extends AnchorPane implements Initializable {

    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button optionsButton;
    @FXML private Button mapSelectionButton;
    @FXML private Button exitGameButton;

    @FXML private RadioButton radioButtonEasy;
    @FXML private RadioButton radioButtonMedium;
    @FXML private RadioButton radioButtonHard;

    @FXML private RadioButton radioButtonMap1;
    @FXML private RadioButton radioButtonMap2;
    @FXML private RadioButton radioButtonMap3;

    @FXML private AnchorPane mainMenuAnchorPane;
    @FXML private AnchorPane map;
    @FXML private AnchorPane options;
    @FXML private AnchorPane mapSelectionAnchorPane;

    private Difficulty difficulty;
    private int mapNumber;

    private final ToggleGroup radioButtonGroupDifficulty = new ToggleGroup();
    private final ToggleGroup radioButtonGroupMapNumber = new ToggleGroup();
    private  MenuHandler menuHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuHandler = new MenuHandler(map,options,mapSelectionAnchorPane,mainMenuAnchorPane);
        activateRadioButtons();
        update();
    }
    private void activateRadioButtons(){
        radioButtonEasy.setToggleGroup(radioButtonGroupDifficulty);
        radioButtonMedium.setToggleGroup(radioButtonGroupDifficulty);
        radioButtonHard.setToggleGroup(radioButtonGroupDifficulty);
        radioButtonEasy.setSelected(true);

        radioButtonMap1.setToggleGroup(radioButtonGroupMapNumber);
        radioButtonMap2.setToggleGroup(radioButtonGroupMapNumber);
        radioButtonMap3.setToggleGroup(radioButtonGroupMapNumber);
        radioButtonMap1.setSelected(true);
    }

    @FXML
    protected void newGame(){
        playClickUIButtonSound();
        Game game = new Game(difficulty,mapNumber);
        MapController mapController = new MapController(game,game.getBoard(),this);
        menuHandler.mapToFront(mapController);
    }
    @FXML private void loadGame(){
        playClickUIButtonSound();
    }
    @FXML private void options(){
        playClickUIButtonSound();
        menuHandler.optionsToFront();
    }
    @FXML private void mapSelection(){
        playClickUIButtonSound();
        menuHandler.mapSelectionPaneToFront();
    }
    @FXML protected void openMenu(){
        menuHandler.mainMenuAnchorPaneToFront();
    }
    @FXML private void exitGame(){ System.exit(0); }

    public void update(){
        if(radioButtonEasy.isSelected()){
            difficulty = Difficulty.EASY;
        }
        else if(radioButtonMedium.isSelected()){
            difficulty = Difficulty.MEDIUM;
        }
        else if(radioButtonHard.isSelected()){
            difficulty = Difficulty.HARD;
        }
        if(radioButtonMap1.isSelected()){
            mapNumber = 1;
        }
        else if(radioButtonMap2.isSelected()){
            mapNumber = 2;
        }
        else if(radioButtonMap3.isSelected()){
            mapNumber = 3;
        }
    }

    /**
     * These methods change the css/style of buttons, not very important
     * Can be argued that these need to go into VIEW. But these methods have strong controller behaviours
     */
    @FXML private void selectEasyDifficulty(){
        radioButtonEasy.getStyleClass().clear();
        radioButtonEasy.getStyleClass().add("RadioButtonSelected");
        radioButtonMedium.getStyleClass().add("RadioButtonNotSelected");
        radioButtonHard.getStyleClass().add("RadioButtonNotSelected");
        update();
    }
    @FXML private void selectMediumDifficulty(){

        radioButtonEasy.getStyleClass().add("RadioButtonNotSelected");
        radioButtonMedium.getStyleClass().clear();
        radioButtonMedium.getStyleClass().add("RadioButtonSelected");
        radioButtonHard.getStyleClass().add("RadioButtonNotSelected");
        update();
    }
    @FXML private void selectHardDifficulty(){
        radioButtonEasy.getStyleClass().add("RadioButtonNotSelected");
        radioButtonMedium.getStyleClass().add("RadioButtonNotSelected");
        radioButtonHard.getStyleClass().clear();
        radioButtonHard.getStyleClass().add("RadioButtonSelected");
        update();
    }

    @FXML private void selectMap1(){
        radioButtonMap1.getStyleClass().clear();
        radioButtonMap1.getStyleClass().add("RadioButtonSelected");
        radioButtonMap2.getStyleClass().add("RadioButtonNotSelected");
        radioButtonMap3.getStyleClass().add("RadioButtonNotSelected");
        update();
    }
    @FXML private void selectMap2(){
        radioButtonMap2.getStyleClass().clear();
        radioButtonMap2.getStyleClass().add("RadioButtonSelected");
        radioButtonMap1.getStyleClass().add("RadioButtonNotSelected");
        radioButtonMap3.getStyleClass().add("RadioButtonNotSelected");
        update();
    }
    @FXML private void selectMap3(){
        radioButtonMap3.getStyleClass().clear();
        radioButtonMap3.getStyleClass().add("RadioButtonSelected");
        radioButtonMap1.getStyleClass().add("RadioButtonNotSelected");
        radioButtonMap2.getStyleClass().add("RadioButtonNotSelected");
        update();
    }



     private void playClickUIButtonSound(){
         URL resource = getClass().getResource("/sound/click.mp3");
         Media media = new Media(resource.toString());
         MediaPlayer mediaPlayer = new MediaPlayer(media);
         mediaPlayer.play();

    }





}
