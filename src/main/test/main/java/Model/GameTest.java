/*
package main.test.main.java.Model;

import main.java.Model.Cell.GroundCell;
import main.java.Model.Difficulty;
import main.java.Model.Game;
import main.java.Model.Towers.*;
import main.java.Model.Towers.Upgrade.MageTowerRightUpgrade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    /**
     * Test suite for Game
     * @author Felix Nilsson
     * @author Hannes Svahn
     */
/*
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
        game.createTower(0,new ArcherTowerFactory());

        Tower t = game.getTower(0,0);

        //Supposed to work
        boolean success = true;
        try {
            game.removeTower(t);
        }
        catch (NullPointerException e){
            success = false;
            assertFalse(true);
        }
        if(success){
            assertTrue(true);
        }

        TowerFactory tf = new ArcherTowerFactory();
        t = tf.createTower(new GroundCell(0,0,false));

        //Supposed to not work
        success = false;
        try {
            game.removeTower(t);
        }
        catch (NullPointerException e){
            success = true;
            assertTrue(true);
        }
        if(success){
            assertFalse(true);
        }

    }
}

 */

