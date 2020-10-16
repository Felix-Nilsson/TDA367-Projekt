package Model.Towers;

public class ArcherTowerUpgrade extends TowerUpgrade{
    private Tower t;

    public ArcherTowerUpgrade(Tower t) {
        super(t);
        this.t = t;
    }

    public Tower leftUpgrade(){
        t.setPhysicalDmg(t.getPhysicalDmg()+20);
        return super.leftUpgrade(t);
    }

    public Tower rightUpgrade(){
        t.setAttackSpeed(t.getAttackSpeed()+1);
        return super.rightUpgrade(t);
    }

}
