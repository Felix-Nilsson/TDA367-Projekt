package Model.Towers;

import Model.BaseCell;
import Model.GroundCell;
import Model.TerrainType;
import Model.UpdateModel;

public class MageTowerFactory implements TowerFactory{

    @Override
    public MageTower createTower(GroundCell cell, UpdateModel updateModel) {
        return new MageTower(cell,0,10,150,50,0.5,updateModel);
    }
}
