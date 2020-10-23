package main.java.View;

import javafx.scene.control.RadioButton;
import main.java.Controller.MapController;
import javafx.scene.layout.AnchorPane;

public class MenuHandler {
    private final AnchorPane map;
    private final AnchorPane options;
    private final AnchorPane mapSelectionAnchorPane;
    private final AnchorPane mainMenuAnchorPane;
    private final RadioButton easyButton;
    private final RadioButton mediumButton;
    private final RadioButton hardButton;


    public MenuHandler(AnchorPane map,AnchorPane options,AnchorPane mapSelectionAnchorPane,AnchorPane mainMenuAnchorPane,
                       RadioButton easyButton, RadioButton mediumButton, RadioButton hardButton){
        this.map = map;
        this.options = options;
        this.mapSelectionAnchorPane = mapSelectionAnchorPane;
        this.mainMenuAnchorPane = mainMenuAnchorPane;
        this.easyButton = easyButton;
        this.mediumButton = mediumButton;
        this.hardButton = hardButton;
    }
    public void mapToFront(MapController mapController){
        map.toFront();
        map.getChildren().add(mapController);
    }
    public void selectEasyButton(){
        easyButton.getStyleClass().clear();
        easyButton.getStyleClass().add("RadioButtonSelected");
        mediumButton.getStyleClass().add("RadioButtonNotSelected");
        hardButton.getStyleClass().add("RadioButtonNotSelected");
    }
    public void selectMediumButton(){
        mediumButton.getStyleClass().clear();
        mediumButton.getStyleClass().add("RadioButtonSelected");
        easyButton.getStyleClass().add("RadioButtonNotSelected");
        hardButton.getStyleClass().add("RadioButtonNotSelected");
    }
    public void selectHardButton(){
        hardButton.getStyleClass().clear();
        hardButton.getStyleClass().add("RadioButtonSelected");
        easyButton.getStyleClass().add("RadioButtonNotSelected");
        mediumButton.getStyleClass().add("RadioButtonNotSelected");
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
