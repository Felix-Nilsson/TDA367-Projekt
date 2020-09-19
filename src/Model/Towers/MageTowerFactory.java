package Model.Towers;

import Model.BaseCell;
import Model.TerrainType;

public class MageTowerFactory {
    //temp
    BaseCell baseCell= new BaseCell(2,1 , TerrainType.GROUND, false, 50, 50, "2aa84c");
    public void createTower() {
        //TODO Basecell
        MageTower mageTower = new MageTower(baseCell,0,10,150,50,0.5);
    }
}
