package Model;

import Model.Towers.Projectile;

import java.util.ArrayList;
import java.util.List;

public class Observable1 {
    private List<Observer1> observers = new ArrayList<Observer1>();
    public void addObserver1(Observer1 observer){
        System.out.println("observer was adddeeeeeeeeeeeeeeeeed");
        observers.add(observer);
        System.out.println(observers.size());
    }
    public void notifyObservers1(Projectile p){
        System.out.println("for loop i notifyObservers");
        System.out.println("observers.size()= " + observers.size());
        for(Observer1 observer : observers){
            System.out.println(p==null);
            observer.addProjectileView(p);
            System.out.println("for loop i notifyObservers");
        }
        //System.out.println("list of updatable objects: "+ updatableList.toString());
    }
}
