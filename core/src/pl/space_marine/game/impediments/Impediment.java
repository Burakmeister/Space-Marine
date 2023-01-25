package pl.space_marine.game.impediments;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.rocket.Rocket;

public abstract class Impediment {
    // abstrakcyjna klasa przeszkody/przeciwnika

    protected Image image;

    protected Listener[] listeners;     // listenery

    protected float collisionDamage;    // obrażenia zadane podczas zderzenia z graczem
    protected float HP = 1;             // zdrowie przeszkody
    protected float direction;          // kierunek  radiany bez pi  0 - w gore
    protected int speed;
    protected int x;
    protected int y;

    public Impediment(Image image, Listener[] listeners, float collisionDamage, float direction, int speed, int x, int y){
        this.collisionDamage = collisionDamage;
        this.listeners = listeners;
        this.direction = direction;
        this.speed = speed;
        this.x=x;
        this.y=y;
        this.image = image;
    }

    // efekt wywołany na rakiecie przy kolizji
    public void collisionEffect(Rocket rocket){
        //not impelemented yet
    }

    public void notifyListeners(){

    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
