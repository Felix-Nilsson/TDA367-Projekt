package Model.Towers;

import Model.BaseCell;
import Model.GroundCell;
import Model.TerrainType;

public class MageTowerFactory implements TowerFactory{

    @Override
    public MageTower createTower(GroundCell cell) {
        return new MageTower(cell,0,10,150,50,0.5);
    }
}
