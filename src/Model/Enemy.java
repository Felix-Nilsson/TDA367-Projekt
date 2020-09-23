package Model;

public interface Enemy extends Updatable{
    void update();
    void followPath();
    void tookDamage(int damage);
}
