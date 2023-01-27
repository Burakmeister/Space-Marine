package pl.space_marine.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Iterator;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.rocket.Rocket;
import pl.space_marine.game.states.GameStateManager;
import pl.space_marine.game.states.MenuState;
import pl.space_marine.game.states.State;
import pl.space_marine.game.states.UpgradesState;

public class Renderer extends State {
    private Game game;
    private Rocket rocket;
    private TextButton returnButton;
    private TextButton nextButton;
    private Array<Animator> animations;

    public Renderer(GameStateManager gsm, Stage stage){
        super(gsm, stage);
        this.game = new Game();
        animations = new Array<>();
        this.rocket = game.getRocket();
        rocket.setX(Gdx.graphics.getWidth()/2-Image.ROCKET.getTexture().getWidth()/2);
        rocket.setY(0);
//        stage.setViewport(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(this.stage);
        returnButton = new TextButton("Return", this.setButton());

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                stage.clear();
                gsm.set(new MenuState(gsm, stage));
            }
        });

        nextButton = new TextButton("Next", this.setButton());
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                stage.clear();
                gsm.set(new UpgradesState(gsm, stage, rocket));
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.left().top();
        table.add(returnButton);
        table.add(nextButton);
        table.pack();

        stage.addActor(table);
//        stage.addActor(nextButton);
    }


    @Override
    public void handleInput() {
        float rotation = rocket.getOrientation();
        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
            // Get the current accelerometer values
            float x = Gdx.input.getAccelerometerX();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if ((int) x > 0 && rotation < 45) {
                rotation += 5;
                rocket.setOrientation(rotation);
            } else if ((int) x < 0 && rotation > -45) {
                rotation -= 5;
                rocket.setOrientation(rotation);
            }
        }

    }

    @Override
    public void update(float dt) {
        //nie chcem
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        draw(sb);
        sb.draw(rocket.getImage().getTexture(),
                rocket.getX(), rocket.getY(),
                rocket.getImage().getTexture().getWidth()/2,
                rocket.getImage().getTexture().getHeight()/2,
                rocket.getImage().getTexture().getWidth(),
                rocket.getImage().getTexture().getHeight(),
                1,1,rocket.getOrientation(),
                0,
                0,
                rocket.getImage().getTexture().getWidth(),
                rocket.getImage().getTexture().getHeight(),
                false, false
        );
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        for (Animator a : animations) {
            a.dispose();
        }
    }

    public void draw(SpriteBatch batch) {
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
                //	public void draw (Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX,
                //		float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY);

                    batch.draw(impediment.getImage().getTexture(),
                            impediment.getX(), impediment.getY(),
                            impediment.getImage().getTexture().getWidth()/2,
                            impediment.getImage().getTexture().getHeight()/2,
                            impediment.getImage().getTexture().getWidth(),
                            impediment.getImage().getTexture().getHeight(),
                            1,
                            1,
                            impediment.getDirection()*180,
                            0,
                            0,
                            impediment.getImage().getTexture().getWidth(),
                            impediment.getImage().getTexture().getHeight(),
                            flip, false
                            );
//                Log.d("ganerator", impediment.getImage().name() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
            } else {                                                                                    // w innym wypadku tworze Animator albo uzywal istniejacego jesli jest utworzony
                boolean drawed = false;
                for (Animator anime : animations) {
                    if (((Impediment)anime.getObject()).equals(impediment)) {
                        anime.render();
                        drawed = true;
                        break;
                    }
                }
                if (!drawed) {
                    Animator tmp = new Animator(batch, impediment.getImage(), impediment.getX(), impediment.getY(), flip, impediment.getDirection()*180, impediment);
                    animations.add(tmp);
                    tmp.render();
                }
//                Log.d("ganerator(anime)", impediment.getImage().name() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
            }

        }
    }
}
