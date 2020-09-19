package Model;

public class BaseEnemy implements Enemy{
    private int health;
    private int movementSpeed;
    private int magicResist;
    private int armor;
    private int positionX;
    private int positionY;

    public enum Direction {NORTH,EAST,SOUTH,WEST}

    protected Direction direction;

    public BaseEnemy(int health, int movementSpeed, int magicResist, int armor, int positionX, int positionY, Direction direction){
        this.health=health;
        this.movementSpeed=movementSpeed;
        this.magicResist=magicResist;
        this.armor=armor;
        this.positionX=positionX;
        this.positionY=positionY;
        this.direction=direction;
    }
    @Override
    public void update(){
        move();
    }
    @Override
    public void move(){
        switch (direction) {
            case NORTH -> positionY = positionY - movementSpeed;
            case EAST -> positionX = positionX + movementSpeed;
            case SOUTH -> positionY = positionY + movementSpeed;
            case WEST -> positionX = positionX - movementSpeed;
        }
    }


    @Override
    // ska antagligen ta in damage type (ad/ap)
    public void tookDamage(int damage){
        //TODO lägga in beräkningar beroende på damage type och armor/mr
        health=health-damage;

        if (health<=0){
            //TODO delete this object
        }
    }
    public int getPositionX(){
        return positionX;
    }
    public int getPositionY(){
        return positionY;
    }
    protected Direction getDirection(){
        return direction;
    }
    protected int getHealth(){
        return health;
    }
    protected int getMovementSpeed(){
        return movementSpeed;
    }
    protected int getMagicResist(){
        return magicResist;
    }
    protected int getArmor(){
        return armor;
    }

}
