package main.java.View;

import main.java.Model.Enemy.Enemy;

public interface MapObserver {
    void notifyGameOver();
    void notifyRoundOver();
    void notifyRoundStart();
    void notifyGameWon();
    void notifyEnemyDead(Enemy e);
    void update();

}
