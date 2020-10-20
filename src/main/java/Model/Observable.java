package main.java.Model;

import main.java.Model.Enemy.Enemy;
import main.java.Model.Towers.Projectile;
import main.java.View.MapObserver;
import main.java.View.ProjectileObserver;

import java.util.ArrayList;
import java.util.List;

public class Observable implements MapObserver, ProjectileObserver {
    private final List<MapObserver> mapObservers;
    private List<ProjectileObserver> projectileObservers = new ArrayList<ProjectileObserver>();

    public Observable(){
        mapObservers = new ArrayList<>();
    }

    public void update(){
        for(MapObserver mapObserver : mapObservers){
            mapObserver.update();
        }
        for(ProjectileObserver projectileObserver : projectileObservers){
            projectileObserver.update();
        }
    }
    public boolean addObserver(MapObserver mapObserver){
        final boolean alreadyObserving = this.mapObservers.contains(mapObserver);
        if(!alreadyObserving){
            this.mapObservers.add(mapObserver);
        }
        return !alreadyObserving;
    }

    public void notifyGameOver(){
        for(MapObserver mapObserver : mapObservers){
            mapObserver.notifyGameOver();
        }
    }
    public void notifyRoundOver(){
        for(MapObserver mapObserver : mapObservers){
            mapObserver.notifyRoundOver();
        }
    }
    public void notifyRoundStart(){
        for(MapObserver mapObserver : mapObservers){
            mapObserver.notifyRoundStart();
        }
    }
    public void notifyGameWon(){
        for(MapObserver mapObserver : mapObservers){
            mapObserver.notifyGameWon();
        }
    }

    public void notifyEnemyDead(Enemy e) {
        for(MapObserver mapObserver : mapObservers){
            mapObserver.notifyEnemyDead(e);
        }
    }


    public boolean addObserver(ProjectileObserver projectileObserver){
        final boolean alreadyObserving = this.projectileObservers.contains(projectileObserver);
        if(!alreadyObserving){
            this.projectileObservers.add(projectileObserver);
        }
        return !alreadyObserving;
    }


    public void notifyProjectileAdded(Projectile p){
        for(ProjectileObserver observers : projectileObservers){
            observers.notifyProjectileAdded(p);
        }
    }
    public void notifyProjectileRemoved(Projectile p){
        for(ProjectileObserver observer : projectileObservers){
            observer.notifyProjectileRemoved(p);
        }
    }

}
