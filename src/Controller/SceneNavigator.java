package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

    public class SceneNavigator {
        /**
         * Constants for fxml files
         * Seems it uses the file location as reference point
         */
       // public static final String ROOT = "C:\Users\JonEE\OneDrive\Dokument\GitHub\TDA367-Projekt\src\View";
        public static final String MAP = "../../../View/Map.fxml";
        public static final String MENU = "../../../View/Menu.fxml";
        public static final String SIDEBAR = "../../../View/Sidebar.fxml";
        public static final String TOOLBAR = "../../../View/Toolbar.fxml";


        /**
         * Controller for the main FXML file
         */
        private static RootController rootController;


        /**
         * Set controller to be able to change children in FXML
         *
         * @param rootController controller for the main FXML file.
         */
        public static void setRootController(RootController rootController) {
            SceneNavigator.rootController = rootController;
        }


        private static final Map<String, Node> test = new HashMap<>();

        /**
         * Loads fxml file into the Main Anchor.
         * If already loaded once use cached version
         * <p>
         *
         * @param fxml the file to be loaded.
         */
        public static void loadFxml(String fxml) {
            if (!test.containsKey(fxml)) {
                try {
                    rootController.setFxml(
                            FXMLLoader.load(
                                    SceneNavigator.class.getResource(fxml)
                            )
                    );
                    test.put(fxml, rootController.getNode());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                test.get(fxml).toFront();
            }
        }
    }


