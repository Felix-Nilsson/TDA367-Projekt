package Model.Towers;

public class ArcherTowerRightUpgrade extends TowerUpgrade{

    public ArcherTowerRightUpgrade(Tower t) {
        super(t);
    }

    public Tower rightUpgrade(){
        tower.setAttackSpeed(tower.getAttackSpeed()+1);
        return super.rightUpgrade(tower);
    }
}
