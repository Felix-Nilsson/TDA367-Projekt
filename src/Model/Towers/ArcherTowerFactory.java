package Model.Towers;

import Model.Cell.Cell;


public class ArcherTowerFactory implements TowerFactory{

    @Override
    public ArcherTower createTower(Cell cell) {

        return new ArcherTower(cell,20,0,100,200,3, 100,100);
    }

    @Override
    public int getPrice() {
        return 100;
    }

}
