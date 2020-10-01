package Model;

import java.util.ArrayList;

public interface EnemyFactory {
    Enemy createEnemyEasy();
    Enemy createEnemyMedium();
    Enemy createEnemyHard();
}
