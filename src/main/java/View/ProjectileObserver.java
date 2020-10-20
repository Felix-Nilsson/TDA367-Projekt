package main.java.View;

import main.java.Model.Towers.Projectile;

public interface ProjectileObserver {
    void notifyProjectileAdded(Projectile p);
    void notifyProjectileRemoved(Projectile p);
    void update();
}
