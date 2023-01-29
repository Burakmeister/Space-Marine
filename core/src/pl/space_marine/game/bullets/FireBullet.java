package pl.space_marine.game.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FireBullet extends Bullet {
    private Texture texture;
    private Sound sound;
    private int power;

    public FireBullet(int x, int y, Texture texture, Sound sound, int power, float width, float height, float velocityY, float velocityX) {
        super(x, y, width, height, velocityY, velocityX);
        this.power = power;
        this.texture = new Texture("");
        this.sound = Gdx.audio.newSound(Gdx.files.internal(""));
    }

}