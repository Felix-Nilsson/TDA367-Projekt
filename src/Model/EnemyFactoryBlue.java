package Model;

import java.util.ArrayList;

public class EnemyFactoryBlue implements EnemyFactory{

    @Override
    public BlueEnemy createEnemyEasy(ArrayList<BaseEnemy.Direction> path) {
        System.out.println("creating BlueEnemyEasy...");
        return new BlueEnemy(100, 5,10,10,25,25,path);
    }
    @Override
    public BlueEnemy createEnemyMedium(ArrayList<BaseEnemy.Direction> path) {
        System.out.println("creating BlueEnemyMedium...");
        return new BlueEnemy(150,6,15,15,25,25,path);
    }
    @Override
    public BlueEnemy createEnemyHard(ArrayList<BaseEnemy.Direction> path) {
        System.out.println("creating BlueEnemyHard...");
        return new BlueEnemy(200,7,20,20,25,25,path);
    }
}
