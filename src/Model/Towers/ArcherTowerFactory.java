package Model.Towers;

import Model.BaseCell;
import Model.TerrainType;

public class ArcherTowerFactory implements TowerFactory{

    //temp
    BaseCell baseCell= new BaseCell(1,1 , TerrainType.GROUND, false, 50, 50, "2aa84c");
    public void createTower() {
        //TODO Basecell
        ArcherTower archerTower = new ArcherTower(baseCell,10,0,100,50,0.5);
    }
}
