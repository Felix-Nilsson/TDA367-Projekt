package Model;

public interface Enemy extends Updatable{
    void update();
    void move();
    void tookDamage(int damage);
}
