package main.test.main.java.Model;

import main.java.Model.Cell.GroundCell;
import main.java.Model.Difficulty;
import main.java.Model.Game;
import main.java.Model.Towers.MageTower;
import main.java.Model.Towers.MageTowerFactory;
import main.java.Model.Towers.Upgrade.MageTowerRightUpgrade;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;


public class GameTest {

    @Test
    void getNameOfMap() {
        Game game = new Game(Difficulty.EASY, 1);
        assertEquals("Curvy Snake" ,game.getNameOfMap());
    }

    @Test
    void getDifficulty(){
        Game game = new Game(Difficulty.EASY, 1);
        assertEquals(Difficulty.EASY, game.getDifficulty());
    }

    @Test
    void rightUpgradeTower(){
        Game game = new Game(Difficulty.EASY, 1);
        MageTowerFactory mtf = new MageTowerFactory();
        GroundCell cell = new GroundCell(1,1,false);
        MageTower t = mtf.createTower(cell);
        MageTowerRightUpgrade mtru = new MageTowerRightUpgrade(t);
        assertEquals(mtru, game.rightUpgradeTower(t));
    }

    @Test
    void removeTower(){
        Game game = new Game(Difficulty.EASY, 1);
    }
}