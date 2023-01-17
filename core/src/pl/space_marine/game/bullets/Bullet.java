package pl.space_marine.game.bullets;

import com.badlogic.gdx.graphics.Texture;

public abstract class Bullet {

    // położenie pocisku
    protected int x;
    protected int y;

    // textura pocisku
    protected Texture texture;

    // moc pocisku
    protected int power;

    public Bullet(int x, int y, Texture texture, int power){
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.power = power;
    }
}
