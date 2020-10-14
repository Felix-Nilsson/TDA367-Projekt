package Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class RootController {
    /**
     * Root anchorpane which children are switchable
     */
    @FXML
    private AnchorPane fxmlHolder;
    private Node node;

    public Node getNode() {
        return node;
    }

    /**
     * Replace children for Anchorpane
     *
     * @param node the node to replace with
     */
    public void setFxml(Node node) {
        fxmlHolder.getChildren().add(node);
        this.node = node;
    }
}
