package Model;

import Controller.Observer;
import java.util.ArrayList;
import java.util.List;

public final class Observable {
    private final List<Observer> observers = new ArrayList<Observer>();


    public void addObserver(Observer observer){
        final boolean alreadyObserving = this.observers.contains(observer);
        if(!alreadyObserving){
            this.observers.add(observer);
        }
    }

    public void update(){
        for(Observer observer : observers){
            observer.update();
        }

    }

}
