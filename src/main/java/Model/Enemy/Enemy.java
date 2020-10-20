package main.java.Model.Enemy;

import main.java.Model.DamageType;
import javafx.scene.image.Image;


public interface Enemy  {
    void update();
    void move();
    void followPath();
    void tookDamage(double damage, DamageType damageType);

    boolean isKilled();
    boolean isOut();
    int spawnTime();
    int getPositionX();
    int getPositionY();
    int getHealth();
    int getMaxHealth();
    Image getImage();
}
