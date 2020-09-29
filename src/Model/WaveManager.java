package Model;

import java.util.ArrayList;
import java.util.List;

public class WaveManager {
    ArrayList<BaseEnemy.Direction> path;
    int round;

    public WaveManager(ArrayList<BaseEnemy.Direction> path, int round) {
        this.path = path;
        this.round = round;
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
            Enemy tmpEnemy=getEnemyFactory(choice).createEnemyEasy();
            tmpEnemy.setPath(path);
            enemies.add(tmpEnemy);
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
