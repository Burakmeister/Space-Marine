package pl.space_marine.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import jdk.internal.org.jline.utils.Log;


public enum Animation{
    BIRD("bird.png", 3, 4),
    ENGINE_FIRE("engine_fire.png", 2, 2);

    private int rows, cols;
    private Texture texture;

    private Animation(String source, int rows, int cols){
        this.texture = new Texture(Gdx.files.internal(source));
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Texture getTexture() {
        return texture;
    }
}
