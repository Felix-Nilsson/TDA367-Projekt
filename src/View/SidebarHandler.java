package View;

import Model.Enemy.Enemy;
import Model.Game;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SidebarHandler implements Observer{
    private ImageView playButtonImg;
    private final Label health;
    private final Label money;
    private final Game game;
    private final AnchorPane mageTowerAvailable;
    private final AnchorPane archerTowerAvailable;

    public SidebarHandler(Game game, Label health, Label money, ImageView playButtonImg, AnchorPane mageTowerAvailable, AnchorPane archerTowerAvailable){
        this.playButtonImg = playButtonImg;
        this.health = health;
        this.money = money;
        this.game = game;
        this.mageTowerAvailable = mageTowerAvailable;
        this.archerTowerAvailable = archerTowerAvailable;
        updatePlayerStats();
        game.addObserver(this);
    }
    @Override
    public void notifyGameOver() {

    }

    @Override
    public void notifyRoundOver() {
        playButtonImg.setImage(new Image("/img/play_button.png"));
    }

    @Override
    public void notifyGameWon() {

    }

    @Override
    public void notifyEnemyDead(Enemy e) {

    }

    public void updatePlayerStats(){
        Platform.runLater(()->health.setText(game.getHealth()+""));
        Platform.runLater(()->money.setText(game.getMoney()+""));
    }
    @Override
    public void update(){
        updatePlayerStats();
        updateAvailable();
    }
    public void updateAvailable(){
        if(game.getMoney() >= 150){ //Price of mageTower might need to get
            mageTowerAvailable.setStyle("-fx-background-color:Lightgreen");
        }
        else {
            mageTowerAvailable.setStyle("-fx-background-color:red");
        }
        if(game.getMoney() >= 100){ //Price of archerTower might need to get
            archerTowerAvailable.setStyle("-fx-background-color:Lightgreen");
        }
        else {
            archerTowerAvailable.setStyle("-fx-background-color:red");
        }
    }
    public void setPauseImg(){
        playButtonImg.setImage(new Image("/img/pause.png"));
    }
    public void setPlayImg(){
        playButtonImg.setImage(new Image("/img/pause.png"));
    }
}
