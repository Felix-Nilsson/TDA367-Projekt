package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class WaveManager  {
    private Difficulty difficulty;
    private List<Enemy> enemies;

    private List<BaseEnemy.Direction> enemyPath;
    private long delayTime;
    private long startTime;



    public WaveManager(Difficulty difficulty,List<BaseEnemy.Direction> enemyPath) {
        this.difficulty = difficulty;
        this.enemyPath = enemyPath;
        this.delayTime = 1;

    }

    public List<Enemy> createWave(int round) {
        List<Enemy> wave = new ArrayList<>(enemyCreator(5 + (round * 2), "BlueEnemy"));
        //wave.addAll(enemyCreator(((round - 1) * 2),"RedEnemy"));
        //wave.addAll(enemyCreator(((round - 3) * 2),"GreenEnemy"));
        return wave;
    }




    private EnemyFactory getEnemyFactory(String enemy){
        switch(enemy){
            case "BlueEnemy": return new EnemyFactoryBlue(enemyPath);

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
                    enemies.add(tmpEnemy);

                }
                return enemies;
            case MEDIUM:
                for (int i = 0; i < amount; i++) {
                    Enemy tmpEnemy = getEnemyFactory(enemy).createEnemyMedium();
                    enemies.add(tmpEnemy);

                }
                return enemies;
            case HARD:
                for (int i = 0; i < amount; i++) {
                    Enemy tmpEnemy = getEnemyFactory(enemy).createEnemyHard();
                    enemies.add(tmpEnemy);

                }
                return enemies;

        }
        return enemies;
    }

}

