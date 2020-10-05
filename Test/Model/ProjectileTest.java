package Model;

import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.PI;
import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest {

    @Test
    void move(){
    Projectile proj = new Projectile(0,0,10,10);
    proj.move();

    assertEquals((int)proj.getPosX(),7);
    assertEquals((int)proj.getPosY(),7);
    proj.move();
    assertEquals((int)proj.getPosX(),14);
    assertEquals((int)proj.getPosY(),14);

}


    @Test
    void calculateVelocity(){
        //135 degrees
        Projectile proj = new Projectile(10,10,0,0);
        assertEquals((int)proj.getVx(),-7);
        assertEquals((int)proj.getVy(),-7);
        //0 degrees and on top of enemy (should never happen)
        Projectile proj1 = new Projectile(10,10,10,10);
        assertEquals((int)proj1.getVx(),10);
        assertEquals((int)proj1.getVy(),0);
        //90 degrees
        Projectile proj2 = new Projectile(10,10,10,0);
        assertEquals((int)proj2.getVx(),0);
        assertEquals((int)proj2.getVy(),-10);
        //180 degrees = -180 degrees
        Projectile proj3 = new Projectile(10,10,0,10);
        assertEquals((int)proj3.getVx(),-10);
        assertEquals((int)proj3.getVy(),0);
        //-135 degrees
        Projectile proj4 = new Projectile(10,10,0,20);
        assertEquals((int)proj4.getVx(),-7);
        assertEquals((int)proj4.getVy(),7);
        //-90 degrees
        Projectile proj5 = new Projectile(10,10,10,20);
        assertEquals((int)proj5.getVx(),0);
        assertEquals((int)proj5.getVy(),10);
        //-45 degrees
        Projectile proj6 = new Projectile(10,10,20,20);
        assertEquals((int)proj6.getVx(),7);
        assertEquals((int)proj6.getVy(),7);
        //0 degrees
        Projectile proj7 = new Projectile(10,10,20,10);
        assertEquals((int)proj7.getVx(),10);
        assertEquals((int)proj7.getVy(),0);
    }

}