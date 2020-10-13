package View;

import Model.Enemy.Enemy;
import Model.Game;
import Model.Towers.Projectile;
import Model.Updatable;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectileViewManager implements Updatable{
    private ImageView imageView;
    private List<Projectile> projectileList;
    private List<ImageView> projectileImages;
    private HashMap<Projectile,ImageView> projectileHashMap;

    public ProjectileViewManager(Game game){
        projectileList = game.getProjectileList();
    }

    @Override
    public void update() {
        if (projectileList.size()>0){
            for (Projectile p : projectileList){
                updateProjectile(projectileHashMap,p);
            }
        }
    }
    public void updateProjectile(HashMap<Projectile, ImageView> projectileHashMap, Projectile p){
        ImageView img = projectileHashMap.get(p);
        img.setX(p.getPosX());
        img.setY(p.getPosY());
    }
}
