package Model.Towers;

public class MageTowerLeftUpgrade extends TowerUpgrade{

    public MageTowerLeftUpgrade(Tower t) {
        super(t);
    }

    public Tower leftUpgrade(){
        tower.setMagicDmg(tower.getMagicDmg()+20); //MIght need to make upgrades call to the tower before or smthing
        return super.leftUpgrade(tower);
    }
}
