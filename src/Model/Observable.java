package Model;

import Controller.Observer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public  class Observable {
    private List<Observer> observers = new ArrayList<Observer>();
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
