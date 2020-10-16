package Model.Towers;

import Model.Cell.Cell;
import Model.UpdateModel;

public class MageTowerFactory implements TowerFactory{

    @Override
    public MageTower createTower(Cell cell, UpdateModel updateModel) {
        return new MageTower(cell,0,10,150,50,0.5,updateModel, 100, 100);
    }

    @Override
    public int getPrice() {
        return 150;
    }
}
