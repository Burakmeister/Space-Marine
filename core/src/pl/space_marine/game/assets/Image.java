package pl.space_marine.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum Image {
    // elementy konstrukcyjne rakiety
//    AVIONIC_STAGE("avionic.png"),
//    BOOSTER_STAGE("booster.png"),
//    ENGINE_STAGE("engine.png"),
//    GUN_STAGE("gun.png"),
//    TANK_STAGE("tank.png", Direction.NONE),
    ROCKET("rocket_no_boosters.png", Direction.NONE),
    ENGINE_FIRE("engine_fire.png", Direction.LEFT, 2, 2),

    // pociski

    // tło

    BACKGROUND("bg.jpg", Direction.NONE),

    // ulepszenia
    ENGINE("jet_engine.png", Direction.NONE),
    FUEL_TANK("fuel_tank.png", Direction.NONE),
    GUN("heavy gun.png", Direction.NONE),
    AVIONICS("avionics.png", Direction.NONE),
    BOOSTER("booster.png", Direction.NONE),

    //przeszkody
    BIRD("bird_scale.png", Direction.LEFT, 3, 4),
    DRAGON("dragon_scale.png", Direction.NONE, 1, 3),
    CLOUD("cloud_scale.png", Direction.NONE),
    CLOUD2("cloud_scale2.png", Direction.NONE),
    CLOUD3("cloud_scale3.png", Direction.NONE),
    METHEOR("metheor.png", Direction.NONE),
    PLANE("plane_scale.png", Direction.RIGHT),
    BALLOON("balloon_scale.png", Direction.NONE),
    SPEED_GATE("speed_gate.png", Direction.NONE),
    SATELLITE("satellite_scale.png", Direction.NONE),
    DRONE("drone_scale.png", Direction.NONE, 1 , 4),
    UFO("ufo_scale.png", Direction.NONE);
    private final Texture texture;
    private final String name;
    private Direction direction;
    private int rows, cols;
    private Image(String source, Direction dir, int rows, int cols){
        this.texture = new Texture(Gdx.files.internal(source));
        this.rows=rows;
        this.cols=cols;
        this.direction = dir;
        this.name = source.substring(0,source.length()-4);
    }
    private Image(String source, Direction dir){
        this.texture = new Texture(Gdx.files.internal(source));
        this.rows=1;
        this.cols=1;
        this.direction = dir;
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

    public Direction getDirection() {
        return direction;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }
}
