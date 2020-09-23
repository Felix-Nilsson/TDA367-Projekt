package Model.Towers;

import Model.BaseCell;
import Model.GroundCell;
import Model.TerrainType;
import Model.UpdateModel;


public class ArcherTowerFactory implements TowerFactory{

    @Override
    public ArcherTower createTower(GroundCell cell, UpdateModel updateModel) {
        //TODO Basecell
        ArcherTower archerTower = new ArcherTower(cell,10,0,100,50,0.5,updateModel);
        return archerTower;
    }

}
