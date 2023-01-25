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
        ImpedimentsIterator list = game.drawImpediments();  //pobieram iterator impedimentów z game
        for (Iterator it = list; it.hasNext(); ) {          // pętla z iteratorem
            Impediment impediment = (Impediment) it.next();
            boolean flip = false;
            switch (impediment.getImage().getDirection()){  // sprawdzam orientacje i czy trzeba odbic
                case LEFT:{
                    if(impediment.getDirection()>0 && impediment.getDirection()<1){
                        flip = true;
                    }
                }
                case RIGHT:{
                    if(impediment.getDirection()>1 && impediment.getDirection()<2){
                        flip = true;
                    }
                }
            }

            if (impediment.getImage().getCols() == 1 && impediment.getImage().getRows() == 1) {         // jesli obrazek nie jest animowany to po prostu rysuje
                    batch.draw(impediment.getImage().getTexture(),
                            impediment.getX(), impediment.getY(),
                            (flip ? -1 : 1)*impediment.getImage().getTexture().getWidth(), impediment.getImage().getTexture().getHeight());
//                Log.d("ganerator", impediment.getImage().name() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
            } else {                                                                                    // w innym wypadku tworze Animator albo uzywal istniejacego jesli jest utworzony
                boolean drawed = false;
                for (Animator anime : animations) {
                    if (anime.getX() == impediment.getX() && anime.getY() == impediment.getY()) {
                        anime.render();
                        drawed = true;
                        break;
                    }
                }
                if (!drawed) {
                    Animator tmp = new Animator(batch, impediment.getImage(), impediment.getX(), impediment.getY(), flip);
                    animations.add(tmp);
                    tmp.render();
                }
//                Log.d("ganerator(anime)", impediment.getImage().name() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
            }
        }
        Rocket rocket = game.getRocket();
        batch.draw(rocket.getImage().getTexture(), rocket.getX(), rocket.getY());       // rysowanie rakiety
//        rocket.setY(rocket.getY() + 1);
    }
}
