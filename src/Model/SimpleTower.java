package Model;

public class SimpleTower implements Tower{
    BaseTower baseTower;

    public SimpleTower(){
        baseTower = new BaseTower();
    }

    @Override
    public void update() {
        baseTower.update();
    }
}
