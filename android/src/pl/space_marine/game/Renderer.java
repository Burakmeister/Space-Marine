package pl.space_marine.game;

import android.util.DisplayMetrics;
import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;

import pl.space_marine.game.assets.Animation;
import pl.space_marine.game.Renderer;
import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.rocket.Rocket;

public class Renderer extends ApplicationAdapter {
    private Game game;
    private SpriteBatch batch;


    private Array<Animator> animations;

    @Override
    public void create() {
        this.game = new Game();

        this.batch = new SpriteBatch();
        animations = new Array<>();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.2f, 0.2f, 0.4f, 1);
        this.batch.begin();
        draw();
        this.batch.end();
    }

    @Override
    public void dispose() {
        for (Animator a : animations) {
            a.dispose();
        }
        batch.dispose();
    }

    public void draw() {
        ImpedimentsIterator list = game.drawImpediments();
        for (Iterator it = list; it.hasNext(); ) {
            Impediment impediment = (Impediment) it.next();
            if (impediment.getImage().getCols() == 1 && impediment.getImage().getRows() == 1) {
                batch.draw(impediment.getImage().getTexture(), impediment.getX(), impediment.getY());
//                Log.d("ganerator", impediment.getImage().name() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
            } else {
                boolean drawed = false;
                for (Animator anime : animations) {
                    if (anime.getX() == impediment.getX() && anime.getY() == impediment.getY()) {
                        anime.render();
                        drawed = true;
                        break;
                    }
                }
                if (!drawed) {
                    Animator tmp = new Animator(batch, impediment.getImage(), impediment.getX(), impediment.getY());
                    animations.add(tmp);
                    tmp.render();
                }
//                Log.d("ganerator(anime)", impediment.getImage().name() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
            }
        }
        Rocket rocket = game.getRocket();
        batch.draw(rocket.getImage().getTexture(), rocket.getX(), rocket.getY());
//        rocket.setY(rocket.getY() + 1);
    }
}
