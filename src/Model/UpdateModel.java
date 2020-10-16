package Model;

import Controller.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class UpdateModel  {

    //TODO: rename class

    private List<Updatable> updatableList = new ArrayList<Updatable>();

    public void add(Updatable updatableObject){
        updatableList.add(updatableObject);
    }
    public void update(){
        for(Updatable a : updatableList){
            a.update();
        }
    }

    /**
     * removes
     * @param updatable
     * sends in a Interface Updatable
     * @return
     * returns if the updatable object is already observing
     */
    public boolean removeObserver(final Updatable updatable) {
        final boolean alreadyObserving = this.updatableList.contains(updatable);
        if (alreadyObserving) {
            this.updatableList.remove(updatable);

        }
        return alreadyObserving;
    }


}
