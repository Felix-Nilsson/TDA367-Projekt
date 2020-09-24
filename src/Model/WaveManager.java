package Model;

import java.util.ArrayList;
import java.util.List;

public class WaveManager {
    ArrayList<BaseEnemy.Direction> path;

    public WaveManager(ArrayList<BaseEnemy.Direction> path) {
        this.path = path;
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
        for (int i = 0; i < amount; i++){
            enemies.add(getEnemyFactory(choice).createEnemyEasy(path));
        }
        return enemies;
    }

    private List<Enemy> createWave(){

        List<Enemy> wave = new ArrayList<>();

        wave.addAll(enemyCreator(5,"BlueEnemy"));
        return wave;
    }

}
