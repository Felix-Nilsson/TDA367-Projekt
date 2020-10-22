package main.java.Model.Towers.Upgrade;

import main.java.Model.Towers.Tower;

public class MageTowerRightUpgrade extends TowerUpgrade{
    public MageTowerRightUpgrade(Tower t) {
        super(t);
    }

    public Tower rightUpgrade(){
        tower.setAttackSpeed(tower.getAttackSpeed()+1.0);
        return super.rightUpgrade(tower);
    }

    @Override
    public Tower leftUpgrade(Tower t){
        Tower temp = tower.leftUpgrade(t);
        return temp;
    }



}
