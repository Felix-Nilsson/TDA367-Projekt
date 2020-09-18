package Model;

public class BlueEnemy implements Enemy{

    private final BaseEnemy parent;

    public BlueEnemy(int health, int movementSpeed, int magicResist, int armor, int positionX, int positionY, BaseEnemy.Direction direction){
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor, positionX, positionY, direction);
    }
    @Override
    public void update(){
        //TODO ska eventuellt vara individuell för varje enemy
        parent.update();
    }
    @Override
    public void move(){
        parent.move();
    }
    @Override
    public void tookDamage(int damage){
        parent.tookDamage(damage);
    }

    public int getPositionX(){
        return parent.getPositionX();
    }
    public int getPositionY(){
        return parent.getPositionY();
    }
    protected BaseEnemy.Direction getDirection(){
        return parent.direction;
    }
    protected int getHealth(){
        return parent.getHealth();
    }
    protected int getMovementSpeed(){
        return parent.getMovementSpeed();
    }
    protected int getMagicResist(){
        return parent.getMagicResist();
    }
    protected int getArmor(){
        return parent.getArmor();
    }

}
