package pl.space_marine.game.bullets.factories;

import static pl.space_marine.game.Game.SCALE;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.bullets.LaserBullet;
import pl.space_marine.game.bullets.ShotBullet;
import pl.space_marine.game.rocket.Rocket;

public class ShotBulletCreator extends BulletCreator{

    public ShotBulletCreator(Rocket rocket) {
        super(rocket);
    }

    @Override
    public Bullet createBullet() {
        return new LaserBullet((int) (rocket.getX()+ SCALE*Image.ROCKET3.getTexture().getWidth()/2), (int) (rocket.getY()+Image.ROCKET3.getTexture().getHeight()*SCALE));
    }
}
