package main.java.Model.Towers;

import main.java.Model.Cell.GroundCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseTowerTest {

    @Test
    void stopTimer() {
        //todo: proper testing
    }

    @Test
    void startTimer() {
        GroundCell g = new GroundCell(0,0,true);
        BaseTower b = new BaseTower(g,5,5,10,
                5,1,10,10);

        b.startTimer();
        //TODO: needs proper testing
    }

    @Test
    void getIsReadyToFire() {
        GroundCell g = new GroundCell(0,0,true);
        BaseTower b = new BaseTower(g,5,5,10,
                5,1,10,10);

        //Todo: make a game that is actually testable
    }

    @Test
    void getPosX() {
        GroundCell g = new GroundCell(0,0,true);
        BaseTower b = new BaseTower(g,5,5,10,
                5,1,10,10);

        assertEquals(25,b.getPosX());
    }

    @Test
    void getPosY() {
        GroundCell g = new GroundCell(0,0,true);
        BaseTower b = new BaseTower(g,5,5,10,
                5,1,10,10);

        assertEquals(15,b.getPosY());
    }

    @Test
    void setAngle() {
    }

    @Test
    void setTowerImage() {
    }

    @Test
    void update() {
    }

    @Test
    void attackIfEnemyInRange() {
    }

    @Test
    void attack() {
    }

    @Test
    void getProjectile() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void getLeftUpgradeCost() {
    }

    @Test
    void getRightUpgradeCost() {
    }

    @Test
    void getLeftUpgradeLabel() {
    }

    @Test
    void getRightUpgradeLabel() {
    }

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }

    @Test
    void getMagicDmg() {
    }

    @Test
    void getPhysicalDmg() {
    }

    @Test
    void getAttackSpeed() {
    }

    @Test
    void setMagicDmg() {
    }

    @Test
    void setPhysicalDmg() {
    }

    @Test
    void setAttackSpeed() {
    }

    @Test
    void getImage() {
    }

    @Test
    void getLeftUpgradeImage() {
    }

    @Test
    void getRightUpgradeImage() {
    }

    @Test
    void getRange() {
    }

    @Test
    void getTarget() {
    }

    @Test
    void setTarget() {
    }

    @Test
    void getPosition() {
    }

    @Test
    void leftUpgrade() {
    }

    @Test
    void rightUpgrade() {
    }

    @Test
    void setColor() {
    }
}