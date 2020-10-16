package Model;

import Model.Enemy.*;

import java.util.ArrayList;
import java.util.List;

public class WaveManager  {
    private final Difficulty difficulty;
    private  int difficultyModifier;
    private final List<BaseEnemy.Direction> enemyPath;
    private final int startPos;
    private EnemyFactory enemyFactory;
    private List<Enemy> wave;
    private enum enemies{
        ENEMY_BLUE,
        ENEMY_RED
    }

    public WaveManager(Difficulty difficulty, List<BaseEnemy.Direction> enemyPath, int startPos) {
        this.difficulty = difficulty;
        this.enemyPath = enemyPath;
        this.startPos = startPos;
        switch (difficulty){
            case EASY:
                difficultyModifier = 1;break;
            case MEDIUM:
                difficultyModifier = 2;break;
            case HARD:
                difficultyModifier = 3;break;
        }

    }


    /**
     * Every round has a set number of enemies.
     * These enemies are  created here and added to the list "wave".
     * After every wave the list resets.
     * @param round
     * the round of the wave
     */
    public void createWave(int round) {
        wave = new ArrayList<>();
        enemyCreator((2+round)*difficultyModifier, enemies.ENEMY_BLUE);
        enemyCreator((2+round)*difficultyModifier,enemies.ENEMY_RED);
    }

    public Enemy getEnemy(int counter){
        return wave.get(counter);

    }

    public int getWaveSize(int round){
        createWave(round);
        return wave.size()-1;
    }

    private void enemyCreator(int amount, enemies enemy) {

        switch (difficulty) {
            case EASY:
                for (int i = 0; i < amount; i++) {
                    Enemy tmpEnemy = getEnemyFactory(enemy).createEnemyEasy();
                    wave.add(tmpEnemy);
                }
                break;
            case MEDIUM:
                for (int i = 0; i < amount + 2; i++) {
                    Enemy tmpEnemy = getEnemyFactory(enemy).createEnemyMedium();
                    wave.add(tmpEnemy);
                }
               break;
            case HARD:
                for (int i = 0; i < amount + 5; i++) {
                    Enemy tmpEnemy = getEnemyFactory(enemy).createEnemyHard();
                    wave.add(tmpEnemy);
                }
                break;
        }
    }
    private EnemyFactory getEnemyFactory(enemies enemy){

        switch(enemy){
            case ENEMY_BLUE : return new EnemyFactoryBlue(enemyPath,startPos);
            case ENEMY_RED : return  new EnemyFactoryRed(enemyPath,startPos);
        }
        return null;
    }

}

