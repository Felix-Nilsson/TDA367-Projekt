package View;

import Model.Towers.Tower;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToolbarHandler {

    private final Label towerLabel;
    private final Label attackLabel;
    private final Label magicLabel;
    private final Label attackSpeedLabel;
    private final Label rangeLabel;
    private final Label leftUpgradeCostLabel;
    private final Label rightUpgradeCostLabel;

    private final Tower tower;

    private final Button sellButton;

    private final ImageView tImageView;
    public ToolbarHandler(Tower tower, Label towerLabel,Label attackLabel,Label magicLabel,Label attackSpeedLabel,Label rangeLabel,Label leftUpgradeCostLabel,Label rightUpgradeCostLabel,Button sellButton,ImageView tImageView){
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


}
