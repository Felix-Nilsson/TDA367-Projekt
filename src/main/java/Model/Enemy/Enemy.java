package main.java.Model.Enemy;

import main.java.Model.DamageType;

/**
 * The interface shared by all enemies. As long as the reference variable is of type Enemy, these methods are available.
 * Some methods, such as getPosX, getPosY could be in a separate interface that is implemented by towers, enemies and projectiles
 * because all of these have an actual position (x,y) that is relevant to the class. This would avoid code duplication and follow the Interface Segregation Principle.
 */
public interface Enemy  {
    void update();
    void move();
    void followPath();
    void tookDamage(double damage, DamageType damageType);
    int getId();
    boolean isKilled();
    boolean isOut();
    int spawnTime();
    int getPositionX();
    int getPositionY();
    int getHealth();
    int getMaxHealth();



}
