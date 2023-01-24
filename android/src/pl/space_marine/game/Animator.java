package pl.space_marine.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator{
    private int x, y;
    private float scale = 1f;
    private Animation<TextureRegion> animation;
    private pl.space_marine.game.assets.Animation anime;
    private SpriteBatch batch;

    float stateTime;

    public Animator(SpriteBatch batch, pl.space_marine.game.assets.Animation animation, int x, int y){
        this.anime = animation;
        this.x = x;
        this.y = y;
        this.batch = batch;
        this.create();
    }

    public Animator(SpriteBatch batch, pl.space_marine.game.assets.Animation animation, int x, int y, float scale){
        this.anime = animation;
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.batch = batch;
        this.create();
    }

    public void render() {
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stateTime+=Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        int width, height;
        width = this.anime.getTexture().getWidth()/this.anime.getCols();
        height = this.anime.getTexture().getHeight()/this.anime.getRows();
        batch.draw(currentFrame, x, y, width*scale, height*scale);
    }

    public void dispose() {
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
        this.animation = new Animation<>(0.025f, frames);
        stateTime = 0;
    }
}
