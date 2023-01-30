package pl.space_marine.game.impediments;


import pl.space_marine.game.assets.Image;
import pl.space_marine.game.listener.Listener;

public abstract class Impediment {
    // abstrakcyjna klasa przeszkody/przeciwnika

    protected Image texture;          // textura
    protected Listener listener;     // listenery

    protected float collisionDamage;    // obrażenia zadane podczas zderzenia z graczem
    protected float HP = 1;             // zdrowie przeszkody
    protected int direction;          // stopnie -180 - 180
    protected int speed;
    protected int x;
    protected int y;



    public Impediment(Image image, Listener listener, float collisionDamage, int direction, int speed, int x, int y){
        this.collisionDamage = collisionDamage;
        this.texture = image;
        this.listener = listener;
        this.direction = direction;
        this.speed = speed;
        this.x=x;
        this.y=y;
    }
    public boolean isCollisionWithRocket() {
        return true;
        //return false;
    }

    public void draw(){

    }

    public Image getImage() {
        return texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public void looseHP(float HP) {
        this.HP-=HP;
    }
    public float getHP() {
        return HP;
    }

    public float getCollisionDamage() {
        return collisionDamage;
    }
}
