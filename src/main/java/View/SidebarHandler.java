package main.java.View;

import main.java.Model.Enemy.Enemy;
import main.java.Model.Game;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.java.Model.Towers.ArcherTowerFactory;
import main.java.Model.Towers.MageTowerFactory;

public class SidebarHandler implements MapObserver {
    private final ImageView playButtonImg;
    private final Label health;
    private final Label money;
    private final Game game;
    private final AnchorPane mageTowerAvailable;
    private final AnchorPane archerTowerAvailable;
    private final Label archerTowerPriceLabel;
    private final Label mageTowerPriceLabel;

    public SidebarHandler(Game game,Label mageTowerPriceLabel,Label archerTowerPriceLabel, Label health, Label money, ImageView playButtonImg, AnchorPane mageTowerAvailable, AnchorPane archerTowerAvailable){
        this.playButtonImg = playButtonImg;
        this.health = health;
        this.money = money;
        this.game = game;
        this.mageTowerAvailable = mageTowerAvailable;
        this.archerTowerAvailable = archerTowerAvailable;
        this.archerTowerPriceLabel = archerTowerPriceLabel;
        this.mageTowerPriceLabel = mageTowerPriceLabel;
        setTowerPrices();
        updatePlayerStats();
        game.addMapObserver(this);
    }
    @Override
    public void notifyGameOver() {

    }

    @Override
    public void notifyRoundOver() {
        playButtonImg.setImage(new Image("/img/play_button.png"));
    }

    @Override
    public void notifyRoundStart() {
        playButtonImg.setImage(new Image("/img/pause.png"));
    }

    @Override
    public void notifyGameWon() {

    }

    @Override
    public void notifyEnemyDead(Enemy e) {

    }
    private void setTowerPrices(){
        archerTowerPriceLabel.setText( "$"+game.getArcherTowerPrice());
        mageTowerPriceLabel.setText("$"+game.getMageTowerPrice());
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
        if(game.getMoney() >= new MageTowerFactory().getPrice()){
            mageTowerAvailable.setStyle("-fx-background-color:Lightgreen");
        }
        else {
            mageTowerAvailable.setStyle("-fx-background-color:red");
        }
        if(game.getMoney() >= new ArcherTowerFactory().getPrice()){
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
        playButtonImg.setImage(new Image("/img/play_button.png"));
    }
}
