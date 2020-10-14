package View;

import Model.Game;
import Model.Towers.Projectile;
import Model.Updatable;
import Model.Observer1;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.List;

public class ViewManager1 implements Updatable, Observer1 {

    private List<Projectile> projectileList;
    private List<ImageView> projectileImages;
    private HashMap<Projectile,ImageView> projectileHashMap;
    private Game game;

    public ViewManager1(Game game){
        this.game=game;
        projectileList = game.getProjectileList();
        //lite skevt att game har en egen addUpdatable istället för att ärva add från Updatemodel då Game extends Updatemodel(fungerar som Observable)
        game.addUpdatable(this);
        game.addObserver1(this);
        projectileHashMap = new HashMap<Projectile, ImageView>();
        System.out.println("ViewManager was created and has been added as an observer1");
    }

    @Override
    public void addProjectileView(Projectile p) {
        addViewToHashMap(p);
        System.out.println("projectileView was added to HashMap");
    }

    private void addViewToHashMap(Projectile p){
        ProjectileView pV = new ProjectileView(p);
        //ImageView imageView = new ImageView(pV.getImage());
        //projectileHashMap.put(p, imageView);pV.getImageView()
        projectileHashMap.put(p, pV.getImageView());
        System.out.println("HashMap.size: " + projectileHashMap.size());
    }

    @Override
    public void update() {
        projectileList = game.getProjectileList();
        if (projectileList.size()>0){
            System.out.println(projectileList.size());
            for (Projectile p : projectileList){
                updateProjectile(projectileHashMap,p);
                System.out.println("PROJECTILEUPDATE");
            }
        }
    }
    public void updateProjectile(HashMap<Projectile, ImageView> projectileHashMap, Projectile p){
        /*
        ImageView img = projectileHashMap.get(p);
        img.setX(p.getPosX());
        img.setY(p.getPosY());

         */
    }


}
