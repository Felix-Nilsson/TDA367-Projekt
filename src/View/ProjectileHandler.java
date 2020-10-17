package View;

import Model.Game;
import Model.Towers.Projectile;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.List;

public class ProjectileHandler implements ProjectileObserver {

    private List<Projectile> projectileList;
    private List<ImageView> projectileImages;
    private final HashMap<Projectile,ImageView> projectileHashMap;
    private final Game game;
    private final AnchorPane gameBoardAnchorPane;

    public ProjectileHandler(Game game, AnchorPane gameBoardAnchorPane){
        this.game=game;
        this.gameBoardAnchorPane=gameBoardAnchorPane;
        projectileList = game.getProjectileList();
        game.addProjectileObserver(this);
        projectileHashMap = new HashMap<>();
    }
    @Override
    public void notifyProjectileAdded(Projectile p) {
        ImageView projectileImage = new ImageView("/img/projectile_1.png");
        fixProjectileImage(projectileImage,p);

        projectileHashMap.put(p, projectileImage);
        Platform.runLater(()->gameBoardAnchorPane.getChildren().add(projectileImage));

    }

    @Override
    public void notifyProjectileRemoved(Projectile p) {


        System.out.println("projectile was removed from hashMap1");
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                System.out.println("----------------------------------------------");
                System.out.println("HASHMAP SIZE BEFORE: ---------- "+projectileHashMap.size());

                if(gameBoardAnchorPane.getChildren().remove(projectileHashMap.get(p))){
                    projectileHashMap.remove(p);

                    System.out.println("HASHMAP SIZE AFTER: ---------- "+projectileHashMap.size());
                    System.out.println("----------------------------------------------");

                }
                System.out.println("projectile was removed from hashMap2");


            }
        });

    }


    private void fixProjectileImage(ImageView img,Projectile projectile){
        assert img != null;
        img.setX(projectile.getPosX());
        img.setY(projectile.getPosY());
        img.setFitHeight(5);
        img.setFitWidth(5);
        img.setPreserveRatio(true);
        img.toFront();
    }

    @Override
    public void update() {
        projectileList = game.getProjectileList();
        if (projectileList.size()>0){
            for (Projectile p : projectileList) {
                updateProjectile(p);
            }
        }
    }
    public void updateProjectile(Projectile p){
        System.out.println("projectileHashMap.size() should be==projectileList: "+ projectileHashMap.size() +"==" + projectileList.size());
        ImageView img = projectileHashMap.get(p);
        img.setX(p.getPosX());
        img.setY(p.getPosY());
    }



}
