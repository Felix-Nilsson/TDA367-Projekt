package Model.Enemy;

import java.util.List;

public class EnemyFactoryRed implements EnemyFactory {
    private final List<BaseEnemy.Direction> enemyPath;
    private final int startPos;

    public EnemyFactoryRed(List<BaseEnemy.Direction> enemyPath, int startPos){
        this.enemyPath = enemyPath;
        this.startPos = startPos;
    }

    @Override
    public RedEnemy createEnemyEasy() {
        return new RedEnemy(100, 4,10,10,enemyPath,startPos);
    }
    @Override
    public RedEnemy createEnemyMedium() {
        return new RedEnemy(150,5,15,15,enemyPath,startPos);
    }
    @Override
    public RedEnemy createEnemyHard() {
        return new RedEnemy(200,6,20,20,enemyPath,startPos);
    }
}
