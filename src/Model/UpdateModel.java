package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class UpdateModel  {
    private List<Updatable> updatableList = new ArrayList<Updatable>();
    public void add(Updatable updatableObject){
        updatableList.add(updatableObject);
    }
    public void update(){
        for(Updatable a : updatableList){
            a.update();

        }

        //System.out.println("list of updatable objects: "+ updatableList.toString());
    }


}
