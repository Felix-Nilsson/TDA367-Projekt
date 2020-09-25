package Controller;

import Model.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuController implements Initializable {

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

    private String difficulty;
    private int mapNumber;

    private final ToggleGroup radioButtonGroupDifficulty = new ToggleGroup();
    private final ToggleGroup radioButtonGroupMapNumber = new ToggleGroup();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    private void newGame(){
        playClickUIButtonSound();

        Game game = Game.getInstance();
        game.setDifficulty(difficulty);
        game.setMapNumber(mapNumber);

        MapController mapController = new MapController(game,game.getBoard());
        map.toFront();
        map.getChildren().add(mapController);
        game.update();


    }
    @FXML private void loadGame(){
        playClickUIButtonSound();
    }
    @FXML private void options(){
        playClickUIButtonSound();
        options.toFront();
    }
    @FXML private void mapSelection(){
        playClickUIButtonSound();
        mapSelectionAnchorPane.toFront();
    }
    @FXML private void backToMenu(){
        mainMenuAnchorPane.toFront();
    }
    @FXML private void exitGame(){ System.exit(0); }

    public void update(){
        if(radioButtonEasy.isSelected()){
            difficulty = "easy";
        }
        else if(radioButtonMedium.isSelected()){
            difficulty = "medium";
        }
        else if(radioButtonHard.isSelected()){
            difficulty = "hard";
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

    //These methods change the radio buttons css. Not very important
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
