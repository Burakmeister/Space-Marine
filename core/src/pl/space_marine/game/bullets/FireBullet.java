package pl.space_marine.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.space_marine.game.assets.Image;

public class FireBullet extends Bullet {
    private Texture texture;
    private Sound sound;
    private int power;

    public FireBullet(int x, int y, Image image) {
        super(x, y, 3, image);
    }

}