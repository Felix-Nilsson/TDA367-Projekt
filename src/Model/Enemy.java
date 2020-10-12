package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public interface Enemy extends Updatable{
    void update();
    void followPath();
    void tookDamage(int damage);
    Image getImage();
    int getPositionX();
    int getPositionY();
    void move();
    boolean isDead();
    int spawnTime();
}
