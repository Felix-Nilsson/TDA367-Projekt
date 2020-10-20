package Model.Towers.Upgrade;

import Model.Enemy.Enemy;
import Model.Towers.Projectile;
import Model.Towers.Tower;

import java.util.List;

public class ArcherTowerLeftUpgrade extends TowerUpgrade{

    public ArcherTowerLeftUpgrade(Tower t) {
        super(t);
    }

    public Tower leftUpgrade(){
        tower.setPhysicalDmg(tower.getPhysicalDmg()+20); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(tower);
    }

    @Override
    public Tower rightUpgrade(Tower t){
        Tower temp = tower.rightUpgrade(t);
        return temp;
    }

}
