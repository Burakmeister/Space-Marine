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

    // dzwiek pocisku
    protected Sound sound;

    // moc pocisku
    protected int power;

    // prędkość pocisku

    // rozmiar pocisku
    private Image image;

    private boolean used = false;
    private boolean hitted = false;

    private float orientation = 0;
    public Bullet(int x, int y, int power, Image image) {
        this.x = x;
        this.y = y;
        this.power = power;
        this.image = image;
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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isHitted() {
        return hitted;
    }

    public void setHitted(boolean hitted) {
        this.hitted = hitted;
    }
}