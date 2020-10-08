package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class WaveManager  {
    private final Difficulty difficulty;
    private final List<BaseEnemy.Direction> enemyPath;
    private final int startPos;
    private enum enemies{
        ENEMY_BLUE,
        ENEMY_RED
    }




    public WaveManager(Difficulty difficulty,List<BaseEnemy.Direction> enemyPath,int startPos) {
        this.difficulty = difficulty;
        this.enemyPath = enemyPath;
        this.startPos = startPos;
    }

    public List<Enemy> createWave(int round) {
        List<Enemy> wave = new ArrayList<>(enemyCreator(5 + (round * 2), enemies.ENEMY_BLUE));
        wave.addAll(enemyCreator((round * 2),enemies.ENEMY_RED));
        //wave.addAll(enemyCreator(((round - 3) * 2),"GreenEnemy"));
        System.out.println(wave.toString());
        return wave;
    }

    private List<Enemy> enemyCreator(int amount, enemies enemy) {
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
    private EnemyFactory getEnemyFactory(enemies enemy){

        switch(enemy){
            case ENEMY_BLUE : return new EnemyFactoryBlue(enemyPath,startPos);
            case ENEMY_RED : return  new EnemyFactoryRed(enemyPath,startPos);
        }
        return null;
    }

}

