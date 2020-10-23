
package main.java.Model.Enemy;

import main.java.Model.DamageType;
import main.java.Model.Direction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseEnemyTest {
    /**
     * Test suite for BaseEnemy
     * @author Felix Nilsson
     */

    @Test
    void move() {
        List<Direction> path = new ArrayList();
        path.add(Direction.EAST);

        BaseEnemy b = new BaseEnemy(10,10,10,10,path,0);
        b.move();

        assertEquals(10,b.getPositionX());

        b = new BaseEnemy(10,10,10,10,path,0);
        path = new ArrayList();
        path.add(Direction.SOUTH);

        b.move();
        assertEquals(-40,b.getPositionY());

    }

    @Test
    void isKilled() {
        List<Direction> path = new ArrayList();
        path.add(Direction.EAST);
        BaseEnemy b = new BaseEnemy(10,10,0,10,path,0);

        b.tookDamage(10,DamageType.MAGICAL);

        assertEquals(true,b.isKilled());

    }

    @Test
    void isOut() {
        //TODO: also needs proper testing like followPath()

    }

    @Test
    void spawnTime() {
        List<Direction> path = new ArrayList();
        path.add(Direction.EAST);
        BaseEnemy b = new BaseEnemy(10,5,0,10,path,0);

        assertEquals(5,b.spawnTime());
    }

    @Test
    void followPath() {
        List<Direction> path = new ArrayList();
        path.add(Direction.EAST);
        path.add(Direction.SOUTH);


        BaseEnemy b = new BaseEnemy(10,10,0,10,path,0);

        System.out.println(b.direction);
        b.followPath();
        System.out.println(b.direction);
        b.followPath();
        System.out.println(b.direction);
        //TODO: proper testing of method

    }

    @Test
    void tookDamage() {
        List<Direction> path = new ArrayList();
        path.add(Direction.EAST);

        EnemyFactoryBlue ef = new EnemyFactoryBlue(path,0);

        BlueEnemy b = ef.createEnemyEasy();
        b.tookDamage(20, DamageType.PHYSICAL);
        assertEquals(80,b.getHealth());

        b = ef.createEnemyMedium();
        b.tookDamage(50,DamageType.MAGICAL);
        assertEquals(107,b.getHealth());
    }

    @Test
    void getImage() {
    }

    @Test
    void getPositionX() {
        List<Direction> path = new ArrayList();
        path.add(Direction.EAST);
        BaseEnemy b = new BaseEnemy(10,10,0,10,path,0);

        b.move();

        assertEquals(10,b.getPositionX());
    }

    @Test
    void getPositionY() {
        List<Direction> path = new ArrayList();
        path.add(Direction.SOUTH);
        BaseEnemy b = new BaseEnemy(10,10,0,10,path,0);

        b.move();

        assertEquals(-30,b.getPositionY());

    }

    @Test
    void getHealth() {
        List<Direction> path = new ArrayList();
        path.add(Direction.EAST);
        BaseEnemy b = new BaseEnemy(10,10,0,10,path,0);

        b.tookDamage(5,DamageType.MAGICAL);

        assertEquals(5,b.getHealth());

    }

    @Test
    void getMaxHealth() {
        List<Direction> path = new ArrayList();
        path.add(Direction.EAST);
        BaseEnemy b = new BaseEnemy(10,10,0,10,path,0);

        assertEquals(10,b.getMaxHealth());

    }
}


