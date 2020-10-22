package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Menu.fxml"));
        Scene scene = new Scene(root,1366,768);
        stage.setResizable(false);
        stage.setTitle("Tower Defense");
        stage.setScene(scene);
        stage.show();
    }

    public void begin(String[] args){
        launch();
    }



}
