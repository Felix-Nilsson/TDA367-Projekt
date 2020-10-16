package Model.Towers;

import Model.Towers.MageTower;
import Model.Towers.Tower;
import Model.Towers.TowerUpgrade;

public class MageTowerUpgrade extends TowerUpgrade {
    private Tower t;

    public MageTowerUpgrade(Tower t) {
        super(t);
        this.t = t;
    }

    public Tower leftUpgrade(){
        t.setMagicDmg(t.getMagicDmg()+20); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(t);
    }

    public Tower rightUpgrade(){
        t.setAttackSpeed(t.getAttackSpeed()+1);
        return super.rightUpgrade(t);
    }



}
