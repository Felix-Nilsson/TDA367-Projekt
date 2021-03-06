package main.java.Model.Towers.Upgrade;

import main.java.Model.Towers.Tower;

public class MageTowerLeftUpgrade extends TowerUpgrade{

    public MageTowerLeftUpgrade(Tower t) {
        super(t);
    }

    @Override
    public Tower leftUpgrade(Tower t){
        tower.upgradeMagicDmg(); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(tower);
    }

    @Override
    public Tower rightUpgrade(Tower t){
        Tower temp = tower.rightUpgrade(t);
        return temp;
    }

}
