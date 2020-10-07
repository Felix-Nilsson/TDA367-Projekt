package Model;

import java.util.List;

public class EnemyFactoryBlue implements EnemyFactory{
    List<BaseEnemy.Direction> enemyPath;

    public EnemyFactoryBlue(List<BaseEnemy.Direction> enemyPath){
        this.enemyPath = enemyPath;
    }

    @Override
    public BlueEnemy createEnemyEasy() {
        System.out.println("creating BlueEnemyEasy...");
        //x=15,y=25 is the upper left corner of the mapgrid
        return new BlueEnemy(100, 5,10,10,15,25,enemyPath);
    }
    @Override
    public BlueEnemy createEnemyMedium() {
        System.out.println("creating BlueEnemyMedium...");
        return new BlueEnemy(150,6,15,15,25,25,enemyPath);
    }
    @Override
    public BlueEnemy createEnemyHard() {
        System.out.println("creating BlueEnemyHard...");
        return new BlueEnemy(200,7,20,20,25,25,enemyPath);
    }
}
