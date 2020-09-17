package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SidebarController extends AnchorPane {
    @FXML
    private Label balance;

    private MapController parentController;

    public SidebarController(MapController mapController){
        balance.setText("$" + 100);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/Sidebar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.parentController = mapController;

    }


}
