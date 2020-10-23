package main.java.Model.Towers.Upgrade;

import main.java.Model.Towers.Tower;

public class MageTowerRightUpgrade extends TowerUpgrade{
    public MageTowerRightUpgrade(Tower t) {
        super(t);
    }

    public Tower rightUpgrade(){
        tower.upgradeAttackSpeed();
        return super.rightUpgrade(tower);
    }

    @Override
    public Tower leftUpgrade(Tower t){
        Tower temp = tower.leftUpgrade(t);
        return temp;
    }



}
