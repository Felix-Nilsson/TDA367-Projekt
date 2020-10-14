package Model.Towers;

public abstract class TowerUpgrade implements Tower{
    private Tower upgradeableTower;

    public TowerUpgrade(Tower upgradeableTower){
        this.upgradeableTower = upgradeableTower;
    }

    public void leftUpgrade(){
        upgradeableTower.leftUpgrade();
    }

}
