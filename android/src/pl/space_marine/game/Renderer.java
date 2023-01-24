package pl.space_marine.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import pl.space_marine.game.assets.Image;

public class Renderer extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Game game;

	@Override
	public void create () {
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
//		batch.draw(Image.BIRD);
		batch.end();
	}
	
	@Override
	public void dispose () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}
}
