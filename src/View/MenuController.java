package View;

import Model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import View.MapController;

public class MenuController implements Initializable {

    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button optionsButton;
    @FXML private Button creditsButton;
    @FXML private Button exitGameButton;
    @FXML private AnchorPane mainMenuAnchorPane;
    private MapController mapController;
    @FXML private AnchorPane map;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hi");
    }

    @FXML
    private void newGame(){
        Game game = new Game();
        game.startGame();
        map.toFront();
        mapController = game.getMapController();
        //mainMenuAnchorPane.getChildren().clear();
        map.getChildren().add(mapController);

    }
    @FXML
    private void loadGame(){ }
    @FXML
    private void options(){ }
    @FXML
    private void credits(){ }
    @FXML
    private void exitGame(){ }



}
