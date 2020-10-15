package Model.Towers;

public class ArcherTowerUpgrade extends TowerUpgrade{
    private Tower t;

    public ArcherTowerUpgrade(Tower t) {
        super(t);
        this.t = t;
    }

    public Tower leftUpgrade(){
        t.setPhysicalDmg(t.getPhysicalDmg()+10);
        return super.leftUpgrade(t);
    }

    public Tower rightUpgrade(){
        t.setAttackSpeed(t.getAttackSpeed()+0.5);
        return super.rightUpgrade(t);
    }

}
