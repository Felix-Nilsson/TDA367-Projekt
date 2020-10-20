package main.java.View;

import main.java.Controller.MapController;
import javafx.scene.layout.AnchorPane;

public class MenuHandler {
    private final AnchorPane map;
    private final AnchorPane options;
    private final AnchorPane mapSelectionAnchorPane;
    private final AnchorPane mainMenuAnchorPane;

    public MenuHandler(AnchorPane map,AnchorPane options,AnchorPane mapSelectionAnchorPane,AnchorPane mainMenuAnchorPane){
        this.map = map;
        this.options = options;
        this.mapSelectionAnchorPane = mapSelectionAnchorPane;
        this.mainMenuAnchorPane = mainMenuAnchorPane;
    }
    public void mapToFront(MapController mapController){
        map.toFront();
        map.getChildren().add(mapController);
    }
    public void optionsToFront(){
        options.toFront();
    }
    public void mapSelectionPaneToFront(){
        mapSelectionAnchorPane.toFront();
    }
    public void mainMenuAnchorPaneToFront(){
        mainMenuAnchorPane.toFront();
    }


}
