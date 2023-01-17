package pl.space_marine.game.impediments.enemies;

import java.util.List;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.impediments.Listener;
import pl.space_marine.game.rocket.Rocket;

public abstract class Enemy extends Impediment {
    protected List<Bullet> bullets;
    protected float bulletDamage;

    public Enemy(Image image, Listener[] listeners, float collisionDamage, float direction, int speed, int x, int y) {
        super(image, listeners, collisionDamage, direction, speed, x, y);
    }

    public void attack(Rocket rocket){
        // not implemented yet
    }
}
