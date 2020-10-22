
package main.java.Model;

import main.java.Model.Cell.GroundCell;
import main.java.Model.Difficulty;
import main.java.Model.Game;
import main.java.Model.Towers.MageTower;
import main.java.Model.Towers.MageTowerFactory;
import main.java.Model.Towers.Tower;
import main.java.Model.Towers.Upgrade.MageTowerRightUpgrade;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameTest {

    @Test
    public void getNameOfMap() {
        Game game = new Game(Difficulty.EASY, 1);
        assertEquals("Curvy Snake" ,game.getNameOfMap());
    }

    @Test
    public void getDifficulty(){
        Game game = new Game(Difficulty.EASY, 1);
        assertEquals(Difficulty.EASY, game.getDifficulty());
    }

    @Test
    public void rightUpgradeTower(){
        Game game = new Game(Difficulty.EASY, 1);
        MageTowerFactory mtf = new MageTowerFactory();
        GroundCell cell = new GroundCell(1,1,false);
        MageTower t = mtf.createTower(cell);

        double tAttackSpeedOld = t.getAttackSpeed(); //AS before upgrade

        MageTowerRightUpgrade mtru = new MageTowerRightUpgrade(t); // AS after upgrade
        Tower tower = mtru.rightUpgrade();

        assertEquals(tAttackSpeedOld+1.0,tower.getAttackSpeed());
    }

    @Test
    public void removeTower(){
        Game game = new Game(Difficulty.EASY, 1);
    }
}

