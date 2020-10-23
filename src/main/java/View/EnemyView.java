package main.java.View;

import javafx.scene.image.Image;
import main.java.Model.Enemy.Enemy;

public class EnemyView {
    private final Enemy enemy;

    public EnemyView(Enemy enemy){
        this.enemy = enemy;
    }

    public Image getImage() throws Exception {
        switch (enemy.getId()){
            case 1 : return new Image("img/blue_Monster.png");
            case 2 : return new Image("img/red_Monster.png");
        }

        throw new Exception("Invalid id");
    }
}
