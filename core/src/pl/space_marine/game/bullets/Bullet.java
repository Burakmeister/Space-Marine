package pl.space_marine.game.bullets;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import pl.space_marine.game.assets.Image;

public abstract class Bullet extends Rectangle {

    // położenie pocisku
    public int x;
    public int y;

    // textura pocisku
    protected Texture texture;

    // dzwiek pocisku
    protected Sound sound;

    // moc pocisku
    protected int power;

    // prędkość pocisku
    public float velocityX;
    public float velocityY;

    // rozmiar pocisku
    protected float width;
    protected float height;
    private Image image;

    private float orientation = 0;
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
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }
    public float getOrientation() {
        return orientation;
    }
    public float getVelocityX() {
        return velocityX;
    }
    public float getVelocityY() {
        return velocityY;
    }
}