package main.java.Model;

import main.java.Model.Cell.Cell;
import main.java.Model.Level.CurvySnakeFactory;
import main.java.Model.Level.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    /**
     * Testing suite for Board
     * @author Felix Nilsson
     */

    @Test
    void getStartPos() {
        Board b = new Board(1);
        assertEquals(3,b.getStartPos());

    }

    @Test
    void getEndPos() {
        Board b = new Board(1);
        assertEquals(17,b.getEndPos());

    }


    @Test
    void isCellOccupied() {
        Board b = new Board(1);

        assertEquals(false,b.isCellOccupied(0));

        b.setCellOccupied(0);

        assertEquals(true,b.isCellOccupied(0));

    }

    @Test
    void setCellOccupied() {
        Board b = new Board(1);
        b.setCellOccupied(0);

        assertEquals(true,b.isCellOccupied(0));

        b = new Board(1);

        assertEquals(false,b.isCellOccupied(0));

    }

    @Test
    void setCellUnoccupied() {
        Board b = new Board(1);
        b.setCellOccupied(0);
        b.setCellUnoccupied(0);

        assertEquals(false,b.isCellOccupied(0));
    }
}