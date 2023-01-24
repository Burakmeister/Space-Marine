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

    // pociski

    //przeszkody
    BIRD("bird.png"),
    DRAGON("dragon.png"),
    CLOUD("clouds.png"),
    METHEOR("metheor.png")
    ;

    private final Texture texture;

    private Image(String fileName){
        this.texture = new Texture(Gdx.files.internal(fileName));
    }
    public Texture getTexture(){
        return this.texture;
    }
}
