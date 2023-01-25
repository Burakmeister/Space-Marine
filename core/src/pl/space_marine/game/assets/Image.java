package pl.space_marine.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum Image {
    // elementy konstrukcyjne rakiety
//    AVIONIC("avionic.png"),
//    BOOSTER("booster.png"),
//    ENGINE("engine.png"),
//    GUN("gun.png"),
//    TANK("tank.png"),
    ROCKET("rocket.png"),
    ENGINE_FIRE("engine_fire.png", 2, 2),

    // pociski

    //przeszkody
    BIRD("bird.png", 3, 4),
    DRAGON("dragon.png", 4, 3),
    CLOUD("clouds.png"),
    METHEOR("metheor.png"),
    PLANE("plane.png"),
    BALLOON("balloon.png"),
    SPEED_GATE("speed_gate.png"),
    SATELLITE("satellite.png"),
    DRONE("drone.png", 1 , 4),
    UFO("ufo.png", 5, 5);

    private final Texture texture;
    private int rows, cols;
    private Image(String source, int rows, int cols){
        this.texture = new Texture(Gdx.files.internal(source));
        this.rows=rows;
        this.cols=cols;
    }
    private Image(String source){
        this.texture = new Texture(Gdx.files.internal(source));
        this.rows=1;
        this.cols=1;
    }
    public Texture getTexture(){
        return this.texture;
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

}
