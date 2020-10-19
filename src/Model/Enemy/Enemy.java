package Model.Enemy;

import Model.DamageType;
import javafx.scene.image.Image;


public interface Enemy  {
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
