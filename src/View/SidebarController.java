package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class SidebarController extends AnchorPane {
    @FXML
    private Label money;
    @FXML
    private ImageView moneyIcon;
    @FXML
    private Label health;
    @FXML
    private Button nextRound;
    @FXML
    private GridPane gridPane;



    public SidebarController(){

        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Sidebar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.parentController = mapController;

         */

    }



}
