package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlueEnemyTest {

    @Test
    void update() {
        BlueEnemy tmp = new BlueEnemy(10,10, BaseEnemy.Direction.SOUTH,BaseEnemy.Difficulty.EASY);
        tmp.update();
        assertEquals(tmp.getPositionY(),11);
        assertEquals(tmp.getPositionX(),10);
    }

    @Test
    void move() {
        BlueEnemy tmp = new BlueEnemy(10,10, BaseEnemy.Direction.SOUTH,BaseEnemy.Difficulty.EASY);
        tmp.move();
        assertEquals(tmp.getPositionY(),11);
        assertEquals(tmp.getPositionX(),10);
        assertEquals(tmp.getDirection(), BaseEnemy.Direction.SOUTH);
        assertEquals(tmp.getDifficulty(), BaseEnemy.Difficulty.EASY);
    }

    @Test
    void tookDamage() {
        BlueEnemy tmp = new BlueEnemy(0,0,BaseEnemy.Direction.SOUTH,BaseEnemy.Difficulty.EASY);
        tmp.tookDamage(50);
        assertEquals(tmp.getHealth(),50);
    }
}