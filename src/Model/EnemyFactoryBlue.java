package Model;

public class EnemyFactoryBlue implements EnemyFactory{

    @Override
    public Enemy createEnemyEasy() {
        System.out.println("creating BlueEnemyEasy...");
        return new BlueEnemy(10,10, BaseEnemy.Direction.SOUTH, BaseEnemy.Difficulty.EASY);
    }
    @Override
    public Enemy createEnemyMedium() {
        System.out.println("creating BlueEnemyMedium...");
        return new BlueEnemy(10,10, BaseEnemy.Direction.SOUTH, BaseEnemy.Difficulty.MEDIUM);
    }
    @Override
    public Enemy createEnemyHard() {
        System.out.println("creating BlueEnemyHard...");
        return new BlueEnemy(10,10, BaseEnemy.Direction.SOUTH, BaseEnemy.Difficulty.HARD);
    }
}
