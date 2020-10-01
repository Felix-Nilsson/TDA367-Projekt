package Model;

import java.util.ArrayList;
import java.util.List;

public final class UpdateModel  {
    private List<Updatable> updatableList = new ArrayList<Updatable>();
    public void add(Updatable updatableObject){
        updatableList.add(updatableObject);
    }
    public void update(){
        for(Updatable a : updatableList){
            a.update();
        }
    }

}
