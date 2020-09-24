package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void fillPath() {
        //båda dessa ska inte behöva finnas med egentligen
        Board b= new Board(1);

        ArrayList<BaseEnemy.Direction> path = b.getPath();
        assertEquals(BaseEnemy.Direction.EAST, path.get(0));
        assertEquals(BaseEnemy.Direction.EAST, path.get(7));
        assertEquals(BaseEnemy.Direction.SOUTH, path.get(24));
        assertEquals(BaseEnemy.Direction.WEST, path.get(35));
        assertEquals(BaseEnemy.Direction.SOUTH, path.get(93));
        assertEquals(BaseEnemy.Direction.EAST, path.get(110));
        System.out.println("start: " + path.get(0) +" path(1): "+ path.get(1) +" path(2): "+ path.get(2) +" path(24): "+ path.get(24)+" path(30): "+ path.get(30));
    }
}