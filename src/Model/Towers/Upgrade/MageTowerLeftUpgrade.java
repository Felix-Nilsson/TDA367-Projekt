package Model.Towers.Upgrade;

import Model.Towers.Tower;

public class MageTowerLeftUpgrade extends TowerUpgrade{

    public MageTowerLeftUpgrade(Tower t) {
        super(t);
    }

    @Override
    public Tower leftUpgrade(Tower t){
        tower.setMagicDmg(tower.getMagicDmg()+20); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(tower);
    }

    @Override
    public Tower rightUpgrade(Tower t){
        Tower temp = tower.rightUpgrade(t);
        return temp;
    }

}
