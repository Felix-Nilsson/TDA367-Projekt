package main.java.Model.Towers.Upgrade;

import main.java.Model.Towers.Tower;

public class ArcherTowerRightUpgrade extends TowerUpgrade{

    public ArcherTowerRightUpgrade(Tower t) {
        super(t);
    }

    public Tower rightUpgrade(){
        tower.setAttackSpeed(tower.getAttackSpeed()+1);
        return super.rightUpgrade(tower);
    }

    @Override
    public Tower leftUpgrade(Tower t){
        Tower temp = tower.leftUpgrade(t);
        return temp;
    }


}
