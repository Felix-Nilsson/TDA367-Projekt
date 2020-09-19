
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Parent;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Menu.fxml"));
        Scene scene = new Scene(root,1366,768);
        stage.setTitle("Tower Defense");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) { launch(args); }

}
