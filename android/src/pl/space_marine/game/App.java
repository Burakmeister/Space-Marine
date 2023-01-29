package pl.space_marine.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.space_marine.game.assets.Animation;
import pl.space_marine.game.Renderer;
import pl.space_marine.game.assets.Image;

import pl.space_marine.game.states.GameStateManager;
import pl.space_marine.game.states.MenuState;

public class App extends ApplicationAdapter {
    private GameStateManager gsm;
    private Stage stage;
//    private Viewport viewport;

    @Override
    public void create () {
//        this.viewport = new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
        this.stage = new Stage(); // viewport
        gsm = new GameStateManager();
        gsm.push(new MenuState(gsm, stage));
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render((SpriteBatch) stage.getBatch());
    }

    @Override
    public void dispose () {
    }
}