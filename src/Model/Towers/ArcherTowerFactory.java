package Model.Towers;

import Model.Cell.BaseCell;
import Model.Cell.Cell;
import Model.UpdateModel;


public class ArcherTowerFactory implements TowerFactory{

    @Override
    public ArcherTower createTower(Cell cell, UpdateModel updateModel) {
        //TODO Basecell
        ArcherTower archerTower = new ArcherTower(updateModel,cell,10,0,100,50,0.5);
        return archerTower;
    }

    @Override
    public int getPrice() {
        return 100;
    }

}
