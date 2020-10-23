package main.java.View;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.java.Model.Game;
import main.java.Model.Towers.Projectile;

import java.util.HashMap;

public class ProjectileHandler implements ProjectileObserver {

    private final HashMap<Projectile,ImageView> projectileHashMap;
    private final Game game;
    private final AnchorPane gameBoardAnchorPane;

    public ProjectileHandler(Game game, AnchorPane gameBoardAnchorPane){
        this.game=game;
        this.gameBoardAnchorPane=gameBoardAnchorPane;

        projectileHashMap = new HashMap<>();

        game.addProjectileObserver(this);

    }
    @Override
    public void notifyProjectileAdded(Projectile p) {

            ImageView projectileImage = new ImageView("/img/projectile_1.png");
            fixProjectileImage(projectileImage, p);
            synchronized (projectileHashMap) {
            projectileHashMap.put(p, projectileImage);
            }
            Platform.runLater(() -> gameBoardAnchorPane.getChildren().add(projectileImage));


    }

    @Override
    public  void notifyProjectileRemoved(Projectile p) {


        Platform.runLater(() -> {
            if (gameBoardAnchorPane.getChildren().remove(projectileHashMap.get(p))) {
                synchronized (projectileHashMap) {
                    projectileHashMap.remove(p);
                }
            }
        });
    }



    private void fixProjectileImage(ImageView img,Projectile projectile){
        assert img != null;
        img.setX(projectile.getPosX());
        img.setY(projectile.getPosY());
        img.setFitHeight(10);
        img.setFitWidth(10);
        img.setPreserveRatio(true);
        img.toFront();
    }

    @Override
    public synchronized void update() {

            for (Projectile p : game.getProjectileList()) {
                if(projectileHashMap.get(p) != null){
                ImageView img = projectileHashMap.get(p);
                img.setX(p.getPosX());
                img.setY(p.getPosY());
                }
            }
    }

}
