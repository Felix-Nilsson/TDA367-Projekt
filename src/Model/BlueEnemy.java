package Model;

public class BlueEnemy implements Enemy{

    private final BaseEnemy parent;
    private static final int baseHealth = 100;
    private static final int baseMovementSpeed = 1;
    private static final int baseMagicResist = 10;
    private static final int baseArmor = 5;

    //TODO byt ut flera parametrar mot Difficulty, och sen ändra
    public BlueEnemy(int positionX, int positionY, BaseEnemy.Direction direction, BaseEnemy.Difficulty difficulty){
        int health = baseHealth;
        int movementSpeed = baseMovementSpeed;
        int magicResist = baseMagicResist;
        int armor = baseArmor;
        switch (difficulty){
            case EASY:
                break;
            case MEDIUM:
                health = baseHealth+20;
                movementSpeed = baseMovementSpeed+1;
                magicResist = baseMagicResist+5;
                armor = baseArmor+5;
                break;
            case HARD:
                health = baseHealth+40;
                movementSpeed = baseMovementSpeed+2;
                magicResist = baseMagicResist+10;
                armor = baseArmor+10;
                break;
        }
        parent=new BaseEnemy(health, movementSpeed, magicResist, armor, positionX, positionY, direction, difficulty);
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
    protected int getPositionX(){
        return parent.getPositionX();
    }
    protected int getPositionY(){
        return parent.getPositionY();
    }
    protected BaseEnemy.Direction getDirection(){
        return parent.direction;
    }
    protected BaseEnemy.Difficulty getDifficulty(){
        return parent.difficulty;
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
