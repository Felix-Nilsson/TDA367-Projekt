package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BlueEnemyTest {
    ArrayList<BaseEnemy.Direction> path = new ArrayList<>();

    @Test
    void update() {
        path.add(BaseEnemy.Direction.SOUTH);
        path.add(BaseEnemy.Direction.EAST);
        BlueEnemy tmp = new BlueEnemy(10,1,10,10,25,25);
        tmp.setPath(path);
        tmp.update();
        assertEquals(tmp.getPositionY(),26);
        assertEquals(tmp.getPositionX(),25);
        int i = 0;
        while (i<100){
            tmp.update();
            System.out.println("x: " + tmp.getPositionX() + ", y= " + tmp.getPositionY());
            i++;
        }
    }

    @Test
    void followPath() {
        path.add(BaseEnemy.Direction.SOUTH);
        BlueEnemy tmp = new BlueEnemy(10,1,10,10,25,25);
        tmp.setPath(path);
        tmp.followPath();
        assertEquals(tmp.getPositionY(),25);
        assertEquals(tmp.getPositionX(),25);
        assertEquals(tmp.getDirection(), BaseEnemy.Direction.SOUTH);
    }

    @Test
    void tookDamage() {
        BlueEnemy tmp = new BlueEnemy(100,10,10,10,10,10);
        tmp.tookDamage(50);
        assertEquals(tmp.getHealth(),50);
    }
    @Test
    void turn(){
        path.add(BaseEnemy.Direction.SOUTH);
        BlueEnemy tmp = new BlueEnemy(100,1,10,10,10,10);
        tmp.setPath(path);

        tmp.turn(BaseEnemy.Direction.SOUTH);
        assertEquals(tmp.getDirection(), BaseEnemy.Direction.SOUTH);

        tmp.turn(BaseEnemy.Direction.EAST);
        assertEquals(tmp.getDirection(), BaseEnemy.Direction.EAST);

        tmp.turn(BaseEnemy.Direction.NORTH);
        assertEquals(tmp.getDirection(), BaseEnemy.Direction.NORTH);

        tmp.turn(BaseEnemy.Direction.WEST);
        assertEquals(tmp.getDirection(), BaseEnemy.Direction.WEST);
    }
}