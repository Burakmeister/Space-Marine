package pl.space_marine.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import pl.space_marine.game.assets.Animation;
import pl.space_marine.game.Renderer;
import pl.space_marine.game.assets.Image;

public class Renderer extends ApplicationAdapter {
	private Game game;

	private SpriteBatch batch;


	Animator test;
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		test = new Animator(batch, Animation.BIRD, 50, 50);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		this.batch.begin();
		batch.draw(Image.CLOUD.getTexture(), 100, 100);
		test.render();
		this.batch.end();
	}

	@Override
	public void dispose () {
		test.dispose();
		batch.dispose();
	}
}
