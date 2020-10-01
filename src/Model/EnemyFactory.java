package Model;

import java.util.ArrayList;

public interface EnemyFactory {
    Enemy createEnemyEasy(ArrayList<BaseEnemy.Direction> path);
    Enemy createEnemyMedium(ArrayList<BaseEnemy.Direction> path);
    Enemy createEnemyHard(ArrayList<BaseEnemy.Direction> path);
}
