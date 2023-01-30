package pl.space_marine.game.bullets;

import static pl.space_marine.game.assets.Image.SHOTBULLET;

import pl.space_marine.game.assets.Audio;
import pl.space_marine.game.assets.Image;


public class ShotBullet extends Bullet{

    public ShotBullet(int x, int y) {
        super(x, y, 1, Image.SHOTBULLET);
    }
}