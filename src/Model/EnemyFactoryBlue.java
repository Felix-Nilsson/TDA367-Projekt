package Model;

import java.util.List;

public class EnemyFactoryBlue implements EnemyFactory{
    private final List<BaseEnemy.Direction> enemyPath;
    private final int startPos;

    public EnemyFactoryBlue(List<BaseEnemy.Direction> enemyPath,int startPos){
        this.enemyPath = enemyPath;
        this.startPos = startPos;
    }

    @Override
    public BlueEnemy createEnemyEasy() {
        return new BlueEnemy(100, 1,10,10,enemyPath,startPos);
    }
    @Override
    public BlueEnemy createEnemyMedium() {
        return new BlueEnemy(150,2,15,15,enemyPath,startPos);
    }
    @Override
    public BlueEnemy createEnemyHard() {
        return new BlueEnemy(200,3,20,20,enemyPath,startPos);
    }
}
