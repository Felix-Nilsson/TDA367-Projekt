package Model;

import java.util.ArrayList;
import java.util.List;

public class WaveManager {
    private Difficulty difficulty;
    private List<Enemy> enemies;
    private ArrayList<BaseEnemy.Direction> path;


    public WaveManager(Difficulty difficulty) {
        this.difficulty = difficulty;

    }

    public List<Enemy> createWave(int round) {
        List<Enemy> wave = new ArrayList<>(enemyCreator(5 + (round * 2), "BlueEnemy"));
        //wave.addAll(enemyCreator(((round - 1) * 2),"RedEnemy"));
        //wave.addAll(enemyCreator(((round - 3) * 2),"GreenEnemy"));
        return wave;
    }


    private EnemyFactory getEnemyFactory(String enemy) {
        switch (enemy) {
            case "BlueEnemy":
                return new EnemyFactoryBlue();
            //case "RedEnemy " : return  new EnemyFactoryRed();
        }
        return null;
    }

    private List<Enemy> enemyCreator(int amount, String enemy) {
        List<Enemy> enemies = new ArrayList<>();
        switch (difficulty) {
            case EASY:
                for (int i = 0; i < amount; i++) {
                    Enemy tmpEnemy = getEnemyFactory(enemy).createEnemyEasy();
                    tmpEnemy.setPath(path);
                    enemies.add(tmpEnemy);
                }
            case MEDIUM:
                for (int i = 0; i < amount; i++) {
                    Enemy tmpEnemy = getEnemyFactory(enemy).createEnemyMedium();
                    tmpEnemy.setPath(path);
                    enemies.add(tmpEnemy);
                }
            case HARD:
                for (int i = 0; i < amount; i++) {
                    Enemy tmpEnemy = getEnemyFactory(enemy).createEnemyHard();
                    tmpEnemy.setPath(path);
                    enemies.add(tmpEnemy);
                }

        }
        return enemies;
    }
}

