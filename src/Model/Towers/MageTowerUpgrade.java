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
        t.setMagicDmg(t.getMagicDmg()+10);
        return super.leftUpgrade(t);
    }

    public Tower rightUpgrade(){
        t.setAttackSpeed(t.getAttackSpeed()+0.5);
        return super.rightUpgrade(t);
    }
}
