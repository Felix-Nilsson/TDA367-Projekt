package main.java.Model.Towers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest {
    /**
     * Test suite for Projectile
     * @author Felix Nilsson
     * @author Simon Larsson
     */

    @Test
    void getTargetPosX() {
        Projectile p = new Projectile(0,0,0,10);
        assertEquals(0,p.getTargetPosX());
    }

    @Test
    void getTargetPosY() {
        Projectile p = new Projectile(0,0,0,10);
        assertEquals(10,p.getTargetPosY());
    }

    @Test
    void move() {
        Projectile proj = new Projectile(0,0,0,10);
        proj.move();

        assertEquals(0,(int)proj.getPosX());
        assertEquals(30,(int)proj.getPosY());
        proj.move();
        assertEquals(0,(int)proj.getPosX());
        assertEquals(60,(int)proj.getPosY());
    }

    @Test
    void isExisting() {
        Projectile p = new Projectile(0,0,0,10);
        assertEquals(p.isExisting(),true);
    }


    @Test
    void getVx() {
        Projectile p = new Projectile(0,0,10,0);

        assertEquals(30,p.getVx());
    }

    @Test
    void getVy() {
        Projectile p = new Projectile(0,0,0,10);
        assertEquals(30,p.getVy());
    }

    @Test
    void getPosX() {
        Projectile p = new Projectile(0,0,0,10);
        assertEquals(0,p.getPosX());
    }

    @Test
    void getPosY() {
        Projectile p = new Projectile(0,0,0,10);
        assertEquals(0,p.getPosY());
    }
}