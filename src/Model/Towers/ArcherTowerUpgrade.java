package Model.Towers;

import Model.Cell.Cell;

public class ArcherTowerUpgrade extends TowerUpgrade{

    public ArcherTowerUpgrade(Tower t) {
        super(t);
    }

    public Tower leftUpgrade(){
        tower.setPhysicalDmg(tower.getPhysicalDmg()+20); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(tower);
    }

    public Tower rightUpgrade(){
        tower.setAttackSpeed(tower.getAttackSpeed()+1);
        return super.rightUpgrade(tower);
    }



}
