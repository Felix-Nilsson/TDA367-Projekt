package main.java.View;

import main.java.Model.Enemy.Enemy;
import main.java.Model.Game;
import main.java.Model.Towers.Tower;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToolbarHandler implements MapObserver{

    private final Label towerLabel;
    private final Label attackLabel;
    private final Label magicLabel;
    private final Label attackSpeedLabel;
    private final Label rangeLabel;
    private final Label leftUpgradeCostLabel;
    private final Label rightUpgradeCostLabel;
    private final Button leftUpgradeButton;
    private final Button rightUpgradeButton;



    private final Tower tower;

    private final Button sellButton;

    private final ImageView tImageView;

    private final Game game;

    public ToolbarHandler(Game game, Tower tower, Label towerLabel, Label attackLabel, Label magicLabel,
                          Label attackSpeedLabel, Label rangeLabel, Label leftUpgradeCostLabel, Label rightUpgradeCostLabel, Button sellButton, ImageView tImageView, Button leftUpgradeButton, Button rightUpgradeButton){
        this.game = game;
        this.tower = tower;
        this.towerLabel = towerLabel;
        this.attackLabel = attackLabel;
        this.magicLabel = magicLabel;
        this.attackSpeedLabel = attackSpeedLabel;
        this.rangeLabel = rangeLabel;
        this.leftUpgradeCostLabel = leftUpgradeCostLabel;
        this.rightUpgradeCostLabel = rightUpgradeCostLabel;
        this.sellButton = sellButton;
        this.tImageView = tImageView;
        this.leftUpgradeButton = leftUpgradeButton;
        this.rightUpgradeButton = rightUpgradeButton;

    }
    public void setTextOfObjects(){
        towerLabel.setText(tower.toString());
        tImageView.setImage(new Image(tower.getImage()));
        sellButton.setText("Sell: "+ (int)(tower.getPrice() * 0.5));
        magicLabel.setText("Magic: " + tower.getMagicDmg());
        attackLabel.setText("Physical: " + tower.getPhysicalDmg());
        attackSpeedLabel.setText("Speed: " + tower.getAttackSpeed());
        rangeLabel.setText("Range: " + tower.getRange());
    }


    @Override
    public void notifyGameOver() {

    }

    @Override
    public void notifyRoundOver() {

    }

    @Override
    public void notifyRoundStart() {

    }

    @Override
    public void notifyGameWon() {

    }

    @Override
    public void notifyEnemyDead(Enemy e) {

    }

    @Override
    public void update() {
        updateUpgradeAvaialble();
        updateLeftUpgrade();
        updateRightUpgrade();
    }

    public void updateUpgradeAvaialble(){
        if(game.getMoney()>=tower.getLeftUpgradeCost()){
            leftUpgradeButton.setStyle("-fx-background-color:Lightgreen");

        }
        else{
            leftUpgradeButton.setStyle("-fx-background-color:red");
        }
        if(game.getMoney()>=tower.getRightUpgradeCost()){
            rightUpgradeButton.setStyle("-fx-background-color:Lightgreen");
        }
        else{
            rightUpgradeButton.setStyle("-fx-background-color:red");
        }
    }

    public void updateLeftUpgrade(){
        leftUpgradeButton.setDisable(true);
        leftUpgradeCostLabel.setText("Unavailable");
    }

    public void updateRightUpgrade(){
        rightUpgradeButton.setDisable(true);
        rightUpgradeCostLabel.setText("Unavailable");

    }



}
