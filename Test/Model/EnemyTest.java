package Model;

import Model.Enemy.BaseEnemy;
import Model.Enemy.BlueEnemy;
import Model.Enemy.EnemyFactoryBlue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
/*
import static org.junit.Assert.assertEquals;

public class EnemyTest {
    @Test
    void takeDamage(){
        List<BaseEnemy.Direction> path = new ArrayList();
        path.add(BaseEnemy.Direction.EAST);

        EnemyFactoryBlue ef = new EnemyFactoryBlue(path,0);

        BlueEnemy b = ef.createEnemyEasy();
        b.tookDamage(20,DamageType.PHYSICAL);
        assertEquals(80,b.getHealth());

        b = ef.createEnemyMedium();
        b.tookDamage(50,DamageType.MAGICAL);
        assertEquals(107,b.getHealth());

    }

}

 */
