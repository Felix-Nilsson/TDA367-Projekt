package Model.Towers;

import Model.Cell.Cell;

public class MageTowerFactory implements TowerFactory{

    @Override

    public MageTower createTower(Cell cell) {
        return new MageTower(cell,0,100,150,50,5,100, 100);
    }

    @Override
    public int getPrice() {
        return 150;
    }
}
