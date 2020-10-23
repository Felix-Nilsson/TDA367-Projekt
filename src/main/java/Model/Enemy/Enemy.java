package main.java.Model.Enemy;

import main.java.Model.DamageType;


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
