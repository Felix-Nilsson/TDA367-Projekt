package Model.Towers;

import Model.BaseCell;

public class MageTowerFactory {
    //temp
    BaseCell baseCell= new BaseCell();
    public void createTower() {
        //TODO Basecell
        MageTower mageTower = new MageTower(baseCell,0,10,150,50,0.5);
    }
}
