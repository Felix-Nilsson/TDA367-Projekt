
import javafx.stage.Stage;
import javafx.application.Application;


public class Main extends Application{
    public static Stage stage;

    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        System.setProperty("user.home", System.getProperty("user.dir"));
        primaryStage.setTitle("Tower Defense");
        //TODO Get instance of the startview(menu)
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO Backend when we implement it
            }
        }));

    }
}
