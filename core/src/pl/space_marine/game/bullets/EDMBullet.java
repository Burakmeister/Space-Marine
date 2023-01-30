package pl.space_marine.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.space_marine.game.assets.Image;

public class EDMBullet extends Bullet {
    public EDMBullet(int x, int y, Image image) {
        super(x, y, 4, image);
    }
}
