package Model;

import Model.Towers.Projectile;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.PI;
import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest {
/*
    @Test
    void move(){
    Projectile proj = new Projectile(0,0,10);
    proj.move();

    assertEquals(-8,(int)proj.getPosX());
    assertEquals(5,(int)proj.getPosY());
    proj.move();
    assertEquals(-16,(int)proj.getPosX());
    assertEquals(10,(int)proj.getPosY());

}

 */

/*
    @Test
    void calculateVelocity(){
        //135 degrees
        Projectile proj = new Projectile(10,10,0);
        assertEquals(10,(int)proj.getVx());
        assertEquals(0,(int)proj.getVy());
        //0 degrees and on top of enemy (should never happen)
        Projectile proj1 = new Projectile(10,10,10);
        assertEquals(-8,(int)proj1.getVx());
        assertEquals(5,(int)proj1.getVy());
        //90 degrees
        Projectile proj2 = new Projectile(10,10,10);
        assertEquals(-8,(int)proj2.getVx());
        assertEquals(5,(int)proj2.getVy());
        //180 degrees = -180 degrees
        Projectile proj3 = new Projectile(10,10,0);
        assertEquals(10,(int)proj3.getVx());
        assertEquals(0,(int)proj3.getVy());
        //-135 degrees

    }

 */

}