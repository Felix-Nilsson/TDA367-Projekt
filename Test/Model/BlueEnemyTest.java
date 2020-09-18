package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlueEnemyTest {

    @Test
    void update() {
        BlueEnemy tmp = new BlueEnemy(10,1,10,10,10,10,BaseEnemy.Direction.SOUTH);
        tmp.update();
        assertEquals(tmp.getPositionY(),11);
        assertEquals(tmp.getPositionX(),10);
    }

    @Test
    void move() {
        BlueEnemy tmp = new BlueEnemy(10,1,10,10,10,10, BaseEnemy.Direction.SOUTH);
        tmp.move();
        assertEquals(tmp.getPositionY(),11);
        assertEquals(tmp.getPositionX(),10);
        assertEquals(tmp.getDirection(), BaseEnemy.Direction.SOUTH);
    }

    @Test
    void tookDamage() {
        BlueEnemy tmp = new BlueEnemy(100,10,10,10,10,10,BaseEnemy.Direction.SOUTH);
        tmp.tookDamage(50);
        assertEquals(tmp.getHealth(),50);
    }
}