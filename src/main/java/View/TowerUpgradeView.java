package main.java.View;

import javafx.scene.image.Image;
import main.java.Model.Towers.Tower;

public class TowerUpgradeView {
    private final Tower tower;

    public TowerUpgradeView(Tower tower){
        this.tower = tower;
    }

    public Image getImage(int id) throws Exception {
        switch (id){
            case 1 : return new Image("/img/mageTowerDmgUpgrade.png");
            case 2 : return new Image("/img/archerTowerDmgUpgrade.png");
            case 3 : return new Image("/img/atkSpdUpgrade.png");
        }

        throw new Exception("Invalid id");
    }
}
