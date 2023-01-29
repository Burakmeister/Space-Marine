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
    ROCKET("rocket_scale.png"),
    ENGINE_FIRE("engine_fire.png", 2, 2),

    ARMOR("armor.png"),
    // pociski
    SHOTBULLET("Shotbullet.png"),
    // tło

    BACKGROUND("bg.jpg"),

    // ulepszenia
    ENGINE("jet_engine.png"),
    FUEL_TANK("fuel_tank.png"),
    GUN("heavy gun.png"),
    AVIONICS("avionics.png"),
    BOOSTER("booster.png"),

    //przeszkody
    BIRD_L("bird_scale_L.png", 3, 4),
    BIRD_R("bird_scale_R.png", 3, 4),
    DRAGON("dragon_scale.png", 1, 3),
    CLOUD("cloud_scale.png"),
    CLOUD2("cloud_scale2.png"),
    CLOUD3("cloud_scale3.png"),
    METHEOR("metheor.png"),
    PLANE_R("plane_scale_R.png"),
    PLANE_L("plane_scale_L.png"),
    BALLOON("balloon_scale.png"),
    SPEED_GATE("speed_gate.png"),
    SATELLITE("satellite_scale.png"),
    DRONE("drone_scale.png", 1 , 4),
    UFO("ufo_scale.png");

    private final Texture texture;
    private final String name;
    private int rows, cols;
    private Image(String source, int rows, int cols){
        this.texture = new Texture(Gdx.files.internal(source));
        this.rows=rows;
        this.cols=cols;
        this.name = source.substring(0,source.length()-4);
    }
    private Image(String source){
        this.texture = new Texture(Gdx.files.internal(source));
        this.rows=1;
        this.cols=1;
        this.name = source.substring(0,source.length()-4);
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

    public String getName() {
        System.out.println(name);
        return name;
    }
}
