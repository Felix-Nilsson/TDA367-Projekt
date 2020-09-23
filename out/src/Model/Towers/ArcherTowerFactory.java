package Model.Towers;

import Model.BaseCell;
import Model.GroundCell;
import Model.TerrainType;


public class ArcherTowerFactory implements TowerFactory{

    @Override
    public ArcherTower createTower(GroundCell cell) {
        //TODO Basecell
        ArcherTower archerTower = new ArcherTower(cell,10,0,100,50,0.5);
        return archerTower;
    }

}
