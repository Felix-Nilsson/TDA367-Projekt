package main.java.View;

import javafx.scene.image.Image;
import main.java.Model.Enemy.Enemy;


public class EnemyView {
    private  Image image;
    private final Enemy enemy;

    public EnemyView(Enemy enemy){
        this.enemy = enemy;
    }

    public Image getImage() {
        if(enemy.getId() == 1){
            image = new Image("img/blue_Monster.png");
        }
        else if(enemy.getId() == 2){
            image = new Image("img/red_Monster.png");
        }
        return image;
    }
}
