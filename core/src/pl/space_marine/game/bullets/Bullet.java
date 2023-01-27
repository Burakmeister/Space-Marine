package pl.space_marine.game.bullets;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Bullet extends Rectangle {

    // położenie pocisku
    protected int x;
    protected int y;

    // textura pocisku
    protected Texture texture;

    // dzwiek pocisku
    protected Sound sound;

    // moc pocisku
    protected int power;

    // prędkość pocisku
    protected float velocityX;
    protected float velocityY;

    // rozmiar pocisku
    protected float width;
    protected float height;

   // czy pocisk jest jeszcze na mapie
    protected boolean isActive;
    public Bullet(int x, int y, float width, float height, float velocityY, float velocityX) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
        this.velocityY = velocityY;
        this.velocityX = velocityX;
        this.isActive = true;
    }

    public void update() {
        x += velocityX;
        y += velocityY;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void playSound() {
        sound.play();
    }

    public void dispose() {
        texture.dispose();
        sound.dispose();
    }
}