package pl.space_marine.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator{
    private int x, y;
    private int rotation = 0;
    private float scale = 1f;
    private boolean flip = false;
    private Animation<TextureRegion> animation;
    private pl.space_marine.game.assets.Image anime;
    private SpriteBatch batch;

    float stateTime;

    public Animator(SpriteBatch batch, pl.space_marine.game.assets.Image animation, int x, int y){
        this.anime = animation;
        this.x = x;
        this.y = y;
        this.batch = batch;
        this.create();
    }

    public Animator(SpriteBatch batch, pl.space_marine.game.assets.Image animation, int x, int y, boolean flip, int rotation, float scale){
        this.anime = animation;
        this.x = x;
        this.y = y;
        this.batch = batch;
        this.flip = flip;
        this.rotation = rotation;
        this.scale = scale;
        this.create();
    }

    public void render() {
        stateTime+=Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        int width, height;
        width = this.anime.getTexture().getWidth()/this.anime.getCols();
        height = this.anime.getTexture().getHeight()/this.anime.getRows();
        batch.draw(currentFrame, x, y,
                0, 0,
                (flip ? -1 : 1)*width, height,
                scale, scale, rotation);
    }

    public void dispose() {
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    private void create(){
        TextureRegion[][] tmp = TextureRegion.split(this.anime.getTexture(),
                this.anime.getTexture().getWidth()/this.anime.getCols(),
                this.anime.getTexture().getHeight()/this.anime.getRows());

        TextureRegion[] frames = new TextureRegion[this.anime.getCols()*this.anime.getRows()];
        int idx = 0;
        for(int i=0; i<this.anime.getRows(); i++){
            for(int j=0; j<this.anime.getCols(); j++){
                frames[idx++] = tmp[i][j];
            }
        }
        this.animation = new Animation<>(0.1f, frames);
        stateTime = 0;
    }
}
