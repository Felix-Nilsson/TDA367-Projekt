package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SidebarController extends AnchorPane {
    @FXML
    private Label balance;

    @FXML
    private ImageView moneyIcon;

    private MapController parentController;

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

    public void setBalance(String s){
        balance.setText(s);
    }


}
