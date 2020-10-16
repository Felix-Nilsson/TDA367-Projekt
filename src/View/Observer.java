package View;

import Model.Enemy.Enemy;

public interface Observer {
    void notifyGameOver();
    void notifyRoundOver();
    void notifyGameWon();
    void notifyEnemyDead(Enemy e);
    void update();
}
