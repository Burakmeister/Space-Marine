package pl.space_marine.game.bullets.factories;

import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.rocket.Rocket;

public abstract class BulletCreator {
    protected Rocket rocket;
    public BulletCreator(Rocket rocket){
        this.rocket = rocket;
    }
    public abstract Bullet createBullet();
}
