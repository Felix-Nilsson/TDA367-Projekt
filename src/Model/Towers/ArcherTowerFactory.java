package Model.Towers;

import Model.BaseCell;

public class ArcherTowerFactory implements TowerFactory{

    //temp
    BaseCell baseCell= new BaseCell();
    public void createTower() {
        //TODO Basecell
        ArcherTower archerTower = new ArcherTower(baseCell,10,0,100,50,0.5);
    }
}
