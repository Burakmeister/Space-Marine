package pl.space_marine.game.impediments;


import pl.space_marine.game.assets.Image;
import pl.space_marine.game.listener.Listener;

public abstract class Impediment {
    // abstrakcyjna klasa przeszkody/przeciwnika

    protected Image texture;          // textura
    protected Listener listener;     // listenery

    protected float collisionDamage;    // obrażenia zadane podczas zderzenia z graczem
    protected float HP = 1;             // zdrowie przeszkody
    protected float direction;          // kierunek radiany bez pi 0 - w gore
    protected int speed;
    protected int x;
    protected int y;



    public Impediment(Image image, Listener listener, float collisionDamage, float direction, int speed, int x, int y){
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
    // efekt wywołany na rakiecie przy kolizji
    public void collisionEffect(CollisionType collisionType){

            switch(collisionType) {
                case ROCKET:
                    listener.update(this, CollisionType.ROCKET);
                    break;
                case IMPEDIMENT:
                    listener.update(this, CollisionType.IMPEDIMENT);
                    break;
            }


    }

//    public void notifyListeners(){
//
//    }

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
    public float getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
