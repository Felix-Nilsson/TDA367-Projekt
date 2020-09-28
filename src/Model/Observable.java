package Model;

import Controller.Observer;
import java.util.ArrayList;
import java.util.List;

public final class Observable {
    private final List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState(){
        return state;
    }
    public void setState(int state){
        this.state = state;
    }
    public void add(Observer observer){
        observers.add(observer);
    }
    public void notifyAllObservers()  {
        for(Observer observer : observers){
            observer.update();
        }

    }

}
