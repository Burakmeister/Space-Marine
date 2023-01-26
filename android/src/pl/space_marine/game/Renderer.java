package pl.space_marine.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import pl.space_marine.game.assets.Animation;
import pl.space_marine.game.Renderer;
import pl.space_marine.game.assets.Image;
import pl.space_marine.game.states.GameStateManager;
import pl.space_marine.game.states.MenuState;

public class Renderer extends ApplicationAdapter {
	private Game game;

	private SpriteBatch batch;
	private GameStateManager gsm;


	Animator test;
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		test = new Animator(batch, Animation.BIRD, 50, 50);
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1,0,0,1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		test.dispose();
		batch.dispose();
	}
}
