package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class WaveManager  {
    private final Difficulty difficulty;
    private final List<BaseEnemy.Direction> enemyPath;
    private final int startPos;
    private EnemyFactory enemyFactory;
    private List<Enemy> wave;
    private enum enemies{
        ENEMY_BLUE,
        ENEMY_RED
    }

    public WaveManager(Difficulty difficulty,List<BaseEnemy.Direction> enemyPath,int startPos) {
        this.difficulty = difficulty;
        this.enemyPath = enemyPath;
        this.startPos = startPos;
    }


    /**
     * Every round has a set number of enemies.
     * These enemies are  created here and added to the list "wave".
     * After every wave the list resets.
     * @param round
     * @return
     */
    public void createWave(int round) {
        wave = new ArrayList<>();
        switch (round){
            case 1:
                enemyCreator(5, enemies.ENEMY_BLUE);
                enemyCreator(5,enemies.ENEMY_RED); break;
            case 2:
                enemyCreator(5,enemies.ENEMY_RED); break;

            case 3:
                //enemyCreator(5,enemies.ENEMY_RED); break;
            case 4:
                //enemyCreator(5,enemies.ENEMY_RED); break;
            case 5:
                //enemyCreator(5,enemies.ENEMY_RED); break;
            case 6:
                //enemyCreator(5,enemies.ENEMY_RED); break;
            case 7:
                //enemyCreator(5,enemies.ENEMY_RED); break;
            case 8:
                //enemyCreator(5,enemies.ENEMY_RED); break;
            case 9:
                //enemyCreator(5,enemies.ENEMY_RED); break;
            case 10:
                //enemyCreator(5,enemies.ENEMY_RED); break;
        }
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

    public List<Enemy> getWave(){
        return wave;
    }

}

