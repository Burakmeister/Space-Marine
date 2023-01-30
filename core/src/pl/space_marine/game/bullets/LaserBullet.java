package pl.space_marine.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.space_marine.game.assets.Image;

public class LaserBullet extends Bullet {

    private Sound sound;
    public LaserBullet(int x, int y) {
        super(x, y, 2, Image.SHOTBULLET);
        this.sound = Gdx.audio.newSound(Gdx.files.internal("shot_laser.mp3"));

    }

    public Sound getSound() {
        return sound;
    }
}
