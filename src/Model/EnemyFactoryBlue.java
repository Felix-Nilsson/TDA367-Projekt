package Model;

public class EnemyFactoryBlue implements EnemyFactory{

    @Override
    public BlueEnemy createEnemyEasy() {
        System.out.println("creating BlueEnemyEasy...");
        return new BlueEnemy(100, 5,10,10,10,10,BaseEnemy.Direction.SOUTH);
    }
    @Override
    public BlueEnemy createEnemyMedium() {
        System.out.println("creating BlueEnemyMedium...");
        return new BlueEnemy(150,6,15,15,10,10,BaseEnemy.Direction.SOUTH);
    }
    @Override
    public BlueEnemy createEnemyHard() {
        System.out.println("creating BlueEnemyHard...");
        return new BlueEnemy(200,7,20,20,10,10,BaseEnemy.Direction.SOUTH);
    }
}
