package Model.Towers;


import Model.Cell.Cell;

public class MageTowerUpgrade extends TowerUpgrade {

    public MageTowerUpgrade(Tower t) {
        super(t);
    }

    public Tower leftUpgrade(){
        tower.setMagicDmg(tower.getMagicDmg()+20); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(tower);
    }

    public Tower rightUpgrade(){
        tower.setAttackSpeed(tower.getAttackSpeed()+1);
        return super.rightUpgrade(tower);
    }



}
