package pl.space_marine.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.codeandweb.physicseditor.PhysicsShapeCache;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.rocket.Rocket;
import pl.space_marine.game.states.GameStateManager;
import pl.space_marine.game.states.State;


public class Renderer extends State {
    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;
    float accumulator = 0;
    private Game game;
    private World world;
    private PhysicsShapeCache bodiesCache;
    private Array<Body> bodies;
    private Body rocketBody;
    private Box2DDebugRenderer debugRenderer;
    private Rocket rocket;
    //    private TextButton returnButton;
    private Array<Object> sprites;
    private OrthographicCamera camera;
    private ExtendViewport viewport;

    private ImpedimentsIterator iterator;

    private boolean beforeStart = false;

    public Renderer(GameStateManager gsm, Stage stage) {
        super(gsm, stage);
        Gdx.gl.glClearColor(36 / 255f, 36 / 255f, 60 / 255f, 1);

        this.game = new Game();
        this.rocket = game.getRocket();
        this.sprites = new Array<>();
        this.bodies = new Array<>();
        iterator = new ImpedimentsIterator();

        rocket.setX(Gdx.graphics.getWidth() / 2 - Image.ROCKET.getTexture().getWidth() / 2);
        rocket.setY(0);
        camera = new OrthographicCamera(1600, 3200);
        camera.position.set(this.rocket.getX(), this.rocket.getY(), 0);
        camera.update();

        viewport = new ExtendViewport(800, 600, camera);
        stage.setViewport(viewport);


        Gdx.input.setInputProcessor(this.stage);
//        returnButton = new TextButton("Return", this.setButton());
//
//        returnButton.addListener(new ChangeListener() {
//            @Override
//            public void changed (ChangeEvent event, Actor actor) {
//                System.out.println("Button Pressed");
//                gsm.set(new MenuState(gsm, stage));
//            }
//        });
        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, -10), true);
        this.bodiesCache = new PhysicsShapeCache(Gdx.files.internal("physics.xml"));


//        sprite = new Sprite(meteor.getImage().getTexture());
//        sprite.setPosition(meteor.getX(), meteor.getY());
//        stage.addActor(returnButton);
        Body body = bodiesCache.createBody(rocket.getImage().getName(), world, 1, 1);
        body.setTransform(rocket.getX(), rocket.getY(), 0);
        this.rocketBody = body;
//        for(int i=0; i<20; i++)
        draw((SpriteBatch) stage.getBatch());
    }


    @Override
    public void handleInput() {
        float rotation = rocket.getOrientation();
        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
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
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        next();
        int i=0;
        for (Object sprite : sprites) {
            iterator.refreshCursor();
            Impediment temp = (Impediment) iterator.next();
            if (sprite instanceof Sprite) {
                Sprite tmp = (Sprite) sprite;
//                tmp.setPosition(bodies.get(i).getPosition().x,(bodies.get(i).getPosition().y));
                tmp.draw(sb);
            } else if (sprite instanceof Animator) {
                Animator anime = (Animator) sprite;
                anime.setPosition((int) bodies.get(i).getPosition().x, (int) bodies.get(i).getPosition().y);
                anime.render();
            }
            i++;
        }
        Vector2 vec = rocketBody.getPosition();
        rocket.setX((int) vec.x);
        rocket.setY((int) vec.y);
        rocketBody.setTransform(vec, (float) (rocket.getOrientation()*Math.PI/180));

        sb.draw(rocket.getImage().getTexture(),
                rocket.getX(), rocket.getY(),
                rocket.getImage().getTexture().getWidth() / 2,
                rocket.getImage().getTexture().getHeight() / 2,
                rocket.getImage().getTexture().getWidth(),
                rocket.getImage().getTexture().getHeight(),
                1, 1, rocket.getOrientation(),
                0,
                0,
                rocket.getImage().getTexture().getWidth(),
                rocket.getImage().getTexture().getHeight(),
                false, false
        );
        sb.end();
        debugRenderer.render(world, camera.combined);
        stage.draw();
    }

    @Override
    public void dispose() {
        for (Object a : sprites) {
            if (a instanceof Animator) {
                Animator spr = (Animator) a;
                spr.dispose();
            }
        }
        world.dispose();
        debugRenderer.dispose();
    }

    public void draw(SpriteBatch batch) {
        iterator.join(game.drawImpediments(iterator));  //pobieram iterator impedimentów z game i lacze z tym co mam

        iterator.refreshCursor();
        while (iterator.hasNext()) {
            Impediment impediment = (Impediment) iterator.next();
            boolean flip = false;
            switch (impediment.getImage().getDirection()) {  // sprawdzam orientacje i czy trzeba odbic
                case LEFT: {
                    if (impediment.getDirection() > 0 && impediment.getDirection() < 1) {
                        flip = true;
                    }
                }
                case RIGHT: {
                    if (impediment.getDirection() > 1 && impediment.getDirection() < 2) {
                        flip = true;
                    }
                }
            }
            if (impediment.getImage().getCols() == 1 && impediment.getImage().getRows() == 1) {
                Sprite spr = new Sprite(impediment.getImage().getTexture(), impediment.getX(), impediment.getY());
                spr.setFlip(flip, false);
                sprites.add(spr);
                bodies.add(bodiesCache.createBody(impediment.getImage().getName(), world, 1, 1));
            } else {
                Animator tmp = new Animator(batch, impediment.getImage(), impediment.getX(), impediment.getY(), flip, impediment.getDirection() * 180, impediment);
                sprites.add(tmp);
                bodies.add(bodiesCache.createBody(impediment.getImage().getName(), world, 1, 1));
            }
        }
//        for (Iterator it = iterator; it.hasNext(); ) {          // pętla z iteratorem
//            Impediment impediment = (Impediment) it.next();
//            boolean flip = false;
//            switch (impediment.getImage().getDirection()){  // sprawdzam orientacje i czy trzeba odbic
//                case LEFT:{
//                    if(impediment.getDirection()>0 && impediment.getDirection()<1){
//                        flip = true;
//                    }
//                }
//                case RIGHT:{
//                    if(impediment.getDirection()>1 && impediment.getDirection()<2){
//                        flip = true;
//                    }
//                }
//            }
//
//            for (Object sprite : sprites) {
//                if(sprite instanceof Animator){
//
//                }else if(sprite instanceof Sprite){
//
//                }
//            }
//
//            if (impediment.getImage().getCols() == 1 && impediment.getImage().getRows() == 1) {         // jesli obrazek nie jest animowany to po prostu rysuje
//                //	public void draw (Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX,
//                //		float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY);
//                    batch.draw(impediment.getImage().getTexture(),
//                            impediment.getX(), impediment.getY(),
//                            impediment.getImage().getTexture().getWidth()/2,
//                            impediment.getImage().getTexture().getHeight()/2,
//                            impediment.getImage().getTexture().getWidth(),
//                            impediment.getImage().getTexture().getHeight(),
//                            1,
//                            1,
//                            impediment.getDirection()*180,
//                            0,
//                            0,
//                            impediment.getImage().getTexture().getWidth(),
//                            impediment.getImage().getTexture().getHeight(),
//                            flip, false
//                            );
////                Log.d("ganerator", impediment.getImage().name() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
//            } else {                                                                                    // w innym wypadku tworze Animator albo uzywal istniejacego jesli jest utworzony
//
//                if (!drawed) {
//                    Animator tmp = new Animator(batch, impediment.getImage(), impediment.getX(), impediment.getY(), flip, impediment.getDirection()*180, impediment);
//                    sprites.add(tmp);
//                }
////                Log.d("ganerator(anime)", impediment.getImage().name() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
//            }
//
//        }
    }

    public void removeActor(Impediment obj) {
        iterator.refreshCursor();
        int i = 0;
        while (!iterator.isEmpty()) {
            if (iterator.next().equals(obj)) {
                sprites.removeIndex(i);
                bodies.removeIndex(i);
                iterator.remove();
                break;
            }
            i++;
        }
    }

    private void next() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }
}
