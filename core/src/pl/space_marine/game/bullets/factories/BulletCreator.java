package pl.space_marine.game.bullets.factories;

import static pl.space_marine.game.Game.SCALE;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.rocket.Rocket;

public class BulletCreator {
    protected Rocket rocket;
    public BulletCreator(Rocket rocket){
        this.rocket = rocket;
    }
    public Bullet createBullet(){
        return new Bullet((int) (rocket.getX()+ SCALE* Image.ROCKET3.getTexture().getWidth()/2), (int) (rocket.getY()+Image.ROCKET3.getTexture().getHeight()*SCALE), rocket.getStages()[3].getLevel());
    }
}
