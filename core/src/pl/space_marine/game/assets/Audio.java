package pl.space_marine.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum Audio{
    ENGINE("engine.wav");

    private Sound sound;
    private Audio(String source){
        this.sound = Gdx.audio.newSound(Gdx.files.internal(source));
    }
}
