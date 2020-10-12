package Model.Enemy;

import Model.Enemy.Enemy;

public interface EnemyFactory {
    Enemy createEnemyEasy();
    Enemy createEnemyMedium();
    Enemy createEnemyHard();
}
