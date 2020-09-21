package Model;

public interface Enemy {
    void update();
    void followPath();
    void tookDamage(int damage);
}
