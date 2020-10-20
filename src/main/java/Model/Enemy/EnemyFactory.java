package main.java.Model.Enemy;

public interface EnemyFactory {
    Enemy createEnemyEasy();
    Enemy createEnemyMedium();
    Enemy createEnemyHard();
}
