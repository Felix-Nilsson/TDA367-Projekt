package Model.Towers;

import Model.Cell.Cell;


public class ArcherTowerFactory implements TowerFactory{

    @Override
    public ArcherTower createTower(Cell cell) {
        //TODO Basecell
        ArcherTower archerTower = new ArcherTower(cell,10,0,100,250,0.5);
        return archerTower;
    }

    @Override
    public int getPrice() {
        return 100;
    }

}
