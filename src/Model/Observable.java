package Model;

import Controller.Observer;
import java.util.ArrayList;
import java.util.List;

public class Observable implements Observer{
    private final List<Observer> observers;

    public Observable(){
        observers = new ArrayList<>();
    }


    public boolean addObserver(Observer observer){
        final boolean alreadyObserving = this.observers.contains(observer);
        if(!alreadyObserving){
            this.observers.add(observer);
        }
        return !alreadyObserving;
    }

    public boolean removeObserver(final Observer observer) {
        final boolean alreadyObserving = this.observers.contains(observer);
        if (alreadyObserving) {
            this.observers.remove(observer);
        }
        return alreadyObserving;
    }

    public void update(){
        for(Observer observer : observers){
            observer.update();
        }


    }

}
