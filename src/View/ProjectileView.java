package View;

import Model.Game;
import Model.Towers.Projectile;
import Model.Updatable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ProjectileView {

    private Image image;
    private ImageView imageView;
    private Projectile projectile;

    public ProjectileView(Projectile projectile){
        //projectileList = game.getProjectileList();
        //temportärt. Bör kanske finnas ett interface med getImage och alla projectiles under det interfacet har sina egna images.
        image = new Image(getClass().getClassLoader().getResourceAsStream("img/blue_Monster.png"));
        imageView = new ImageView(image);
        fixImage(imageView);
        this.projectile=projectile;
    }


    public Image getImage(){
        return image;
    }

    public ImageView getImageView(){
        return imageView;
    }


    private void fixImage(ImageView img){
        img.setX(projectile.getPosX());
        img.setY(projectile.getPosY());
        img.setFitHeight(25);
        img.setFitWidth(25);
        img.setPreserveRatio(true);
        img.toFront();
    }

}
