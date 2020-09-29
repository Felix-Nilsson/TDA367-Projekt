package Model;

import java.util.ArrayList;
import java.util.List;

public class WaveManager {
    ArrayList<BaseEnemy.Direction> path;
    int round;
    Difficulty difficulty;

    public WaveManager(ArrayList<BaseEnemy.Direction> path, int round, Difficulty difficulty) {
        this.path = path;
        this.round = round;
        this.difficulty = difficulty;

    }
    //Difficulty difficulty;

    private EnemyFactory getEnemyFactory(String choice){
        if (choice == "BlueEnemy"){
            return new EnemyFactoryBlue();
        }
        return null;
    }

    private List<Enemy> enemyCreator(int amount, String choice){
        List<Enemy> enemies = new ArrayList<>();
        if (difficulty == Difficulty.EASY){
            for (int i = 0; i < amount; i++){
                enemies.add(getEnemyFactory(choice).createEnemyEasy(path));
            }
        }
        else if (difficulty == Difficulty.MEDIUM){
            for (int i = 0; i < amount; i++){
                enemies.add(getEnemyFactory(choice).createEnemyMedium(path));
            }
        }
        if (difficulty == Difficulty.HARD){
            for (int i = 0; i < amount; i++){
                enemies.add(getEnemyFactory(choice).createEnemyHard(path));
            }
        }
        return enemies;
    }

    private List<Enemy> createWave(){
        List<Enemy> wave = new ArrayList<>();
        wave.addAll(enemyCreator(5 + (round*2),"BlueEnemy"));
        //wave.addAll(enemyCreator(((round - 1) * 2),"RedEnemy"));
        //wave.addAll(enemyCreator(((round - 3) * 2),"GreenEnemy"));
        return wave;
    }

}
