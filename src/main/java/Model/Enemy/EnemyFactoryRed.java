package main.java.Model.Enemy;

import main.java.Model.Direction;

import java.util.List;

public class EnemyFactoryRed implements EnemyFactory {
    private final List<Direction> enemyPath;
    private final int startPos;

    public EnemyFactoryRed(List<Direction> enemyPath, int startPos){
        this.enemyPath = enemyPath;
        this.startPos = startPos;
    }

    @Override
    public RedEnemy createEnemyEasy() {
        return new RedEnemy(100, 4,10,10,enemyPath,startPos);
    }
    @Override
    public RedEnemy createEnemyMedium() {
        return new RedEnemy(150,5,25,25,enemyPath,startPos);
    }
    @Override
    public RedEnemy createEnemyHard() {
        return new RedEnemy(200,6,50,50,enemyPath,startPos);
    }
}
