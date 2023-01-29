package pl.space_marine.game.bullets;

import static pl.space_marine.game.assets.Image.SHOTBULLET;

import pl.space_marine.game.assets.Audio;
import pl.space_marine.game.assets.Image;


public class ShotBullet extends Bullet{
    private Image image;
    private Audio audio;
    private int power;

    private float orientation = 0; //radiany, ale pomijamy pi, wiec -> (0-2) 0 - w gore     1 - w dol

    public ShotBullet(int x, int y, int power, float width, float height, float velocityY, float velocityX) {
        super(x, y, width, height, velocityY, velocityX);
        this.power = power;
        this.image = SHOTBULLET;
        this.audio = Audio.SHOTBULLET;
    }
    public Image getImage() {
        return this.image;
    }
    public Audio getAudio() {
        return this.audio;
    }

}