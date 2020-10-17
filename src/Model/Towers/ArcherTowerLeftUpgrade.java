package Model.Towers;

public class ArcherTowerLeftUpgrade extends TowerUpgrade{

    public ArcherTowerLeftUpgrade(Tower t) {
        super(t);
    }

    public Tower leftUpgrade(){
        tower.setPhysicalDmg(tower.getPhysicalDmg()+20); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(tower);
    }
}
