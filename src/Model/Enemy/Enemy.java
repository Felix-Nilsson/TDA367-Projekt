package Model.Enemy;

import Model.DamageType;
import Model.Updatable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public interface Enemy extends Updatable {
    void update();
    void followPath();
    void tookDamage(double damage, DamageType damageType);
    Image getImage();
    int getPositionX();
    int getPositionY();
    void move();
    boolean isDead();
    int spawnTime();
    int getHealth();

    int getMaxHealth();
}
