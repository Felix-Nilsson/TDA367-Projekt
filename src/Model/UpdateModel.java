package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class UpdateModel  {
    private List<Updatable> updatableList = new ArrayList<Updatable>();

    public boolean isPaused() {
        return paused;
    }

    private boolean paused = false;
    public void add(Updatable updatableObject){
        updatableList.add(updatableObject);
    }
    public void update(){
        { for(Updatable a : updatableList){
                if (!paused) {
                    a.update();
                }
            }
        }

        //System.out.println("list of updatable objects: "+ updatableList.toString());
    }
    public boolean pause(){
       if (!paused){
           paused = true;
           return true;
       }
       paused = false;
       return false;
    }
}
