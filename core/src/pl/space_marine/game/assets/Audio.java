package pl.space_marine.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum Audio{
    //ENGINE("engine.wav"),

    SHOTBULLET("shot_laser.mp3"),
    EXPLOSION("explosion.wav");

    private Sound sound;
    private Audio(String source){
        this.sound = Gdx.audio.newSound(Gdx.files.internal(source));
    }

    public Sound getSound() {
        return sound;
    }
}
