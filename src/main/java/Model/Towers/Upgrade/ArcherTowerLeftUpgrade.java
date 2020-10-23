package main.java.Model.Towers.Upgrade;

import main.java.Model.Towers.Tower;

public class ArcherTowerLeftUpgrade extends TowerUpgrade{

    public ArcherTowerLeftUpgrade(Tower t) {
        super(t);
    }

    public Tower leftUpgrade(){
        tower.upgradePhysicalDmg(); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(tower);
    }

    @Override
    public Tower rightUpgrade(Tower t){
        Tower temp = tower.rightUpgrade(t);
        return temp;
    }

}
