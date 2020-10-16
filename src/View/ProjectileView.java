package View;

import Model.Towers.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProjectileView {

    private Image image;
    private ImageView imageView;
    private Projectile projectile;

    public ProjectileView(Projectile projectile){
        //projectileList = game.getProjectileList();
        //temportärt. Bör kanske finnas ett interface med getImage och alla projectiles under det interfacet har sina egna images.
        image = new Image(getClass().getClassLoader().getResourceAsStream("img/blue_Monster.png"));
        imageView = new ImageView(image);
        System.out.println("innan fixImage");
        this.projectile=projectile;
        fixImage(imageView);

    }


    public Image getImage(){
        return image;
    }

    public ImageView getImageView(){
        return imageView;
    }


    private void fixImage(ImageView img){
        System.out.print("is image null i fixImage: "); System.out.println(img==null);
        System.out.print("is projectile null i fixImage: "); System.out.println( projectile==null);
        assert img != null;
        img.setX(projectile.getPosX());
        img.setY(projectile.getPosY());
        img.setFitHeight(5);
        img.setFitWidth(5);
        img.setPreserveRatio(true);
        img.toFront();
    }

}
