package main.java.View;

import javafx.scene.image.Image;
import main.java.Model.Towers.Tower;

//Does not currently have a use but should be used in the future

public class TowerView {
    private final Tower tower;

    public TowerView(Tower tower){
        this.tower = tower;
    }

    public Image getImage() throws Exception {
        switch (tower.getId()){
            case 1 : return new Image("/img/mageTower.png");
            case 2 : return new Image("/img/archerTower.png");
        }

        throw new Exception("Invalid id");
    }


}
