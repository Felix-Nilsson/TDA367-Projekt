package Model;

import java.util.ArrayList;

public interface Enemy extends Updatable{
    void update();
    void followPath();
    void tookDamage(int damage);
    void setPath(ArrayList<BaseEnemy.Direction> path);
}
