package Model.Towers;

public class MageTowerRightUpgrade extends TowerUpgrade{
    public MageTowerRightUpgrade(Tower t) {
        super(t);
    }

    public Tower rightUpgrade(){
        tower.setAttackSpeed(tower.getAttackSpeed()+1);
        return super.rightUpgrade(tower);
    }
}
