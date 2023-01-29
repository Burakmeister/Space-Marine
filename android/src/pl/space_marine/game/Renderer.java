package pl.space_marine.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.codeandweb.physicseditor.PhysicsShapeCache;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentList;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.rocket.Rocket;
import pl.space_marine.game.states.GameStateManager;
import pl.space_marine.game.states.State;
import pl.space_marine.game.states.UpgradesState;


public class Renderer extends State {
    public static final float SCALE = 0.2f;
    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;
    private float accumulator = 0;
    private Game game;
    private World world;
    private PhysicsShapeCache bodiesCache;
    private Body rocketBody;
    private Sprite rocketSprite[];
    private Box2DDebugRenderer debugRenderer;
    private Rocket rocket;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private Array<AliveImpediment> impediments;
    private ImpedimentsIterator iterator;

    private Body ground;

    private int g = 10;
    private boolean hasUpdates = false;

    private BitmapFont height = new BitmapFont();

    public Renderer(GameStateManager gsm, Stage stage) {
        super(gsm, stage);
        Gdx.gl.glClearColor(36 / 255f, 36 / 255f, 60 / 255f, 1);

        this.game = new Game();
        this.rocket = game.getRocket();
        this.impediments = new Array<>();

        rocket.setX(Gdx.graphics.getWidth() / 2 - Image.ROCKET.getTexture().getWidth() / 2);
        rocket.setY(0);

        camera = new OrthographicCamera(300, 600);
        viewport = new ExtendViewport(5000, 5000, camera);
        stage.setViewport(viewport);

        camera.position.set(rocket.getX(), rocket.getY() + rocket.getImage().getTexture().getHeight(), 0);
        camera.update();

        Gdx.input.setInputProcessor(this.stage);
        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, -g), true);
        this.bodiesCache = new PhysicsShapeCache(Gdx.files.internal("physics.xml"));

        Body body = bodiesCache.createBody(rocket.getImage().getName(), world, SCALE, SCALE);
        body.setType(BodyDef.BodyType.DynamicBody);
        body.setTransform(rocket.getX(), rocket.getY(), 0);

        this.rocketBody = body;
//        this.rocketBody.applyForce();
        iterator = (ImpedimentsIterator) game.drawImpediments();
        getImpediments((SpriteBatch) stage.getBatch());

        this.rocketSprite = new Sprite[4];
        this.rocketSprite[0] = new Sprite(Image.ROCKET.getTexture());
        this.rocketSprite[1] = new Sprite(Image.ROCKET1.getTexture());
        this.rocketSprite[2] = new Sprite(Image.ROCKET2.getTexture());
        this.rocketSprite[3] = new Sprite(Image.ROCKET3.getTexture());

        Vector2 vec = rocketBody.getPosition();
        for (Sprite sprite : rocketSprite) {
            sprite.setOrigin(0, 0);
            sprite.setX((int) vec.x);
            sprite.setY((int) vec.y);
            sprite.setScale(SCALE);
        }

        createGround();
    }


    @Override
    public void handleInput() {
//        float rotation = rocket.getOrientation();
        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
            // Get the current accelerometer values
            float x = Gdx.input.getAccelerometerX();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            rocket.setOrientation(2 * x);
            if (x < 1 && x > -1) {
                rocket.setOrientation(0);
            }
        } if (Gdx.input.isTouched() && Gdx.input.getY() < Gdx.graphics.getHeight()/2) {
            if (!hasUpdates) {
                gsm.push(new UpgradesState(gsm, stage, rocket));
                hasUpdates = true;
            } else {
                State render = gsm.getStates().lastElement();
                gsm.pop();
                System.out.println(render);
                State upgrades = gsm.getStates().peek();
                ((UpgradesState) upgrades).makeUpgradesComponents();
                System.out.println(upgrades);
                gsm.pop();
                gsm.getStates().push(render);
                gsm.getStates().push(upgrades);
            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        camera.position.set(rocket.getX(), (float) (rocket.getY() + rocket.getImage().getTexture().getHeight() * 0.6), 0); //Ezunia its me bartolomeo
        sb.begin();

        height.draw(sb, "Height: " + rocket.getY(), rocket.getX(), (int) (rocket.getY() - rocket.getImage().getTexture().getHeight() * 0.1));
        next();
        for (AliveImpediment alive : this.impediments) {
            alive.update();
            alive.draw(sb);
        }

        Vector2 vec = rocketBody.getPosition();
        rocket.setX((int) vec.x);
        rocket.setY((int) vec.y);

        if(rocket.getOrientation()==0 && rocketBody.getAngle()<0.01 && rocketBody.getAngle()>-0.01){
            rocketBody.setTransform(vec, 0);
        }else{
            rocketBody.setTransform(vec, (float) (rocketBody.getAngle()+Math.toRadians(rocket.getOrientation())));
            rocket.setOrientation(0);
        }

        float x = (float) Math.sin(rocketBody.getAngle() - Math.PI);
        float y = (float) Math.cos(rocketBody.getAngle());
//        System.out.println(rocketBody.getAngle());

        rocketBody.applyForce(new Vector2(
                rocketBody.getMass() * (x * 300),//x force to apply
                rocketBody.getMass() * (y * 300)),
                rocketBody.getWorldPoint(new Vector2(SCALE*rocketSprite[rocket.getStages()[4].getLevel()].getWidth()/2,0)), true);   //  , new Vector2(rocketBody.getWorldCenter().x, rocketBody.getWorldCenter().y), true);


        //if booster to kolejny force
        //trzeba ogarnac jak dziala masa etc.

        rocketSprite[rocket.getStages()[4].getLevel()].setRotation((float) Math.toDegrees(rocketBody.getAngle()));
        rocketSprite[rocket.getStages()[4].getLevel()].setPosition(rocket.getX(), rocket.getY());
        rocketSprite[rocket.getStages()[4].getLevel()].draw(sb);

//        rocketSprite.setRotation((float) Math.toDegrees(rocketBody.getAngle()));
//        rocketSprite.setPosition(rocket.getX(), rocket.getY());
//        rocketSprite.draw(sb);

        if(rocket.getY()%10==0){
            iterator = game.drawImpediments();
            getImpediments(sb);
        }

        sb.end();

        camera.update();// Ezunia its me bartolomeo
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        for (AliveImpediment imp : this.impediments) {
            imp.dispose();
        }
        world.dispose();
        debugRenderer.dispose();
    }

    public void getImpediments(SpriteBatch sb) {
        boolean found;
        iterator.refreshCursor();
        while (iterator.hasNext()) {
            found = false;
            Impediment imp = (Impediment) iterator.next();
            for(AliveImpediment tmp: impediments){
                if(tmp.getImpediment().equals(imp)){
                    found=true;
                    break;
                }
            }
            if(!found){
                this.impediments.add(new AliveImpediment(imp, sb, bodiesCache, world));
            }
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

    private void createGround() {
        if (ground != null)
            world.destroyBody(ground);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5000, 1);
        fixtureDef.shape = shape;
        ground = world.createBody(bodyDef);
        ground.createFixture(fixtureDef);
        ground.setTransform(0, 0, 0);
        shape.dispose();
    }
}
