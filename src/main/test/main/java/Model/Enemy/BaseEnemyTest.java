package main.java.Model.Enemy;

import main.java.Model.DamageType;
import main.java.Model.Direction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseEnemyTest {

    @Test
    void update() {
    }

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
    }

    @Test
    void isOut() {
    }

    @Test
    void spawnTime() {
    }

    @Test
    void followPath() {
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
    }

    @Test
    void getPositionY() {
    }

    @Test
    void getHealth() {
    }

    @Test
    void getMaxHealth() {
    }
}