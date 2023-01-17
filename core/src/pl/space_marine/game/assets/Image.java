package pl.space_marine.game.assets;

import com.badlogic.gdx.graphics.Texture;

public enum Image {
    // elementy konstrukcyjne rakiety
    AVIONIC("avionic.png", 0, 0),
    BOOSTER("booster.png",0,0),
    ENGINE("engine.png", 0, 0),
    GUN("gun.png",0,0),
    TANK("tank.png",0,0),

    // pociski
    ;

    private final Texture texture;
    private final int width;
    private final int height;

    private Image(String fileName, int width, int height){
        this.texture = new Texture("core//src/pl/space_marine/game/assets/images/"+fileName);
        this.width=width;
        this.height=height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Texture getTexture(){
        return this.texture;
    }
}
