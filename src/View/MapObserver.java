package View;

import Model.Enemy.Enemy;

public interface MapObserver {
    void notifyGameOver();
    void notifyRoundOver();
    void notifyGameWon();
    void notifyEnemyDead(Enemy e);
    void update();
}
