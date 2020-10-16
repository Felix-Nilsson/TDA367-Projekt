package Model.Towers;

import Model.Cell.Cell;

public class MageTowerFactory implements TowerFactory{

    @Override
    public MageTower createTower(Cell cell) {
        return new MageTower(cell,0,10,150,100,0.5);
    }

    @Override
    public int getPrice() {
        return 150;
    }
}
