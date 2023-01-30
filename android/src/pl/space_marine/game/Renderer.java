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
import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.bullets.ShotBullet;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.listener.DefaulRocketShotListener;
import pl.space_marine.game.rocket.Rocket;
import pl.space_marine.game.states.GameStateManager;
import pl.space_marine.game.states.State;
import pl.space_marine.game.states.UpgradesState;
import pl.space_marine.game.WorldContactListener;


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
    private Array<BulletRenderer> bullets;
    private ImpedimentsIterator iterator;

    private Body ground;
    private WorldContactListener contact;

    private int g = 10;
    private boolean hasUpdates = false;
    private boolean isStarted = false;
    private boolean gameover = false;

    private BitmapFont height = new BitmapFont();
    private BitmapFont fuel = new BitmapFont();
    private BitmapFont HP = new BitmapFont();
    private BitmapFont start = new BitmapFont();
    private BitmapFont shop = new BitmapFont();
    private Animator fire;
    private boolean hasUpdates = false;


    private int time = 0;

    public Renderer(GameStateManager gsm, Stage stage) {
        super(gsm, stage);
        Gdx.gl.glClearColor(36 / 255f, 36 / 255f, 60 / 255f, 1);

        this.game = new Game();
        this.rocket = game.getRocket();
        this.impediments = new Array<>();
        this.bullets = new Array<BulletRenderer>();
        this.contact = new WorldContactListener();

        rocket.setX(Gdx.graphics.getWidth() / 2 - Image.ROCKET.getTexture().getWidth() / 2);
        rocket.setY(0);
        rocket.attach((DefaulRocketShotListener) this.game.getShotListener());

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
        body.setUserData(rocket);
        body.setTransform(rocket.getX(), rocket.getY(), 0);

        this.rocketBody = body;
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
        this.world.setContactListener(contact);
        this.fire = new Animator((SpriteBatch) stage.getBatch(), Image.ENGINE_FIRE, rocket.getX(), rocket.getY(), false, -90, SCALE);
//        rocket.updateImage(1);

        createGround();
    }


    @Override
    public void handleInput() {
        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
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
        }
        if (Gdx.input.isTouched() && Gdx.input.getY() < Gdx.graphics.getHeight() / 2) {

            if (!hasUpdates) {
                gsm.push(new UpgradesState(gsm, stage, rocket));
                hasUpdates = true;
            } else {
                State render = gsm.getStates().lastElement();
                gsm.pop();
                State upgrades = gsm.getStates().peek();
                ((UpgradesState) upgrades).makeUpgradesComponents();
                gsm.pop();
                gsm.getStates().push(render);
                gsm.getStates().push(upgrades);
            }

        }else if(Gdx.input.isTouched() && Gdx.input.getY()<Gdx.graphics.getHeight()/2){
            if(!gameover && isStarted)
                game.shot();
                                rocket.shot();
                Bullet bullet = game.shot();//new ShotBullet((int) (rocketBody.getPosition().x+rocket.getImage().getTexture().getWidth()/2*SCALE), (int) ((int) rocketBody.getPosition().y+rocket.getImage().getTexture().getHeight()*SCALE));
                rocket.getBullets().add(bullet);
                bullets.add(new BulletRenderer(bullet, world));
            else{
                for(AliveImpediment imp : impediments){
                    if(imp.getBody()!=null)
                    world.destroyBody(imp.getBody());
                }
                rocketBody.setTransform(0,0,0);
                Vector2 vec = rocketBody.getPosition();
                for (Sprite sprite : rocketSprite) {
                    sprite.setOrigin(0, 0);
                    sprite.setX((int) vec.x);
                    sprite.setY((int) vec.y);
                    sprite.setScale(SCALE);
                }
                gameover = false;
                isStarted = true;
                this.game = new Game();
                this.impediments.clear();
                rocket.setX(Gdx.graphics.getWidth() / 2 - Image.ROCKET.getTexture().getWidth() / 2);
                rocket.setY(0);
                rocket.setHP(1);
            }

        } else {
            gameover = false;
            isStarted = true;
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {


        if(!gameover){
            sb.setProjectionMatrix(camera.combined);
            camera.position.set(rocket.getX(), (float) (rocket.getY() + rocket.getImage().getTexture().getHeight() * 0.6), 0); //Ezunia its me bartolomeo
            sb.begin();

            height.draw(sb, "Height: " + rocket.getY(), rocket.getX(), (int) (rocket.getY() - rocket.getImage().getTexture().getHeight()*SCALE * 0.15));
            HP.draw(sb, "HP: " + Math.round(rocket.getHP()*100) + '%', rocket.getX(), (int) (rocket.getY() - rocket.getImage().getTexture().getHeight()*SCALE * 0.35));
            fuel.draw(sb, "Fuel: " + Math.round(rocket.getFuel()*100) + '%', rocket.getX(), (int) (rocket.getY() - rocket.getImage().getTexture().getHeight()*SCALE * 0.55));
            next();
            for (AliveImpediment alive : this.impediments) {
                alive.update();
                alive.draw(sb);
            }

            this.fire.render();

            Vector2 vec = rocketBody.getPosition();
            rocket.setX((int) vec.x);
            rocket.setY((int) vec.y);

            if (rocket.getOrientation() == 0 && rocketBody.getAngle() < 0.03 && rocketBody.getAngle() > -0.03) {
                rocketBody.setTransform(vec, 0);
            } else {
                rocketBody.setTransform(vec, (float) (rocketBody.getAngle() + Math.toRadians(rocket.getOrientation())));
                rocket.setOrientation(0);
                rocketBody.setFixedRotation(true);
            }
            this.fire.setRotation((int) (Math.toDegrees(rocketBody.getAngle())-90));
            this.fire.setX((int) (rocket.getX()));
            this.fire.setY(rocket.getY());

            float x = (float) Math.sin(rocketBody.getAngle() - Math.PI);
            float y = (float) Math.cos(rocketBody.getAngle());

            rocketBody.applyForce(new Vector2(
                            rocketBody.getMass() * (x * 300),//x force to apply
                            rocketBody.getMass() * (y * 300)),
                    rocketBody.getWorldCenter(), true);


            for (BulletRenderer bullet : bullets) {
                bullet.update();
                bullet.draw(sb);
                if (Math.sqrt(Math.pow(rocket.getX() - bullet.getBullet().getX(), 2) +
                        Math.pow(rocket.getX() - bullet.getBullet().getX(), 2)) > 300 || bullet.getBullet().isHitted()) {
                    this.rocket.getBullets().remove(bullet);
                    world.destroyBody(bullet.getBody());
                    bullets.removeValue(bullet, false);
                }else {
                    if(!bullet.getBullet().isUsed())
                        bullet.setVx(x);
                    bullet.setVy(y);
                    bullet.getBody().setFixedRotation(true);
                    bullet.getBody().applyForceToCenter(new Vector2(bullet.getBody().getMass() * (bullet.getVx() * 1000000),
                            bullet.getBody().getMass() * (bullet.getVy() * 1000000)), true);
                    bullet.getBullet().setUsed(true);
                }
            }


            rocketSprite[rocket.getStages()[4].getLevel()].setRotation((float) Math.toDegrees(rocketBody.getAngle()));
            rocketSprite[rocket.getStages()[4].getLevel()].setPosition(rocket.getX(), rocket.getY());
            rocketSprite[rocket.getStages()[4].getLevel()].draw(sb);

            time = time % 10;


            if (time == 0) {
                iterator = game.drawImpediments();
                getImpediments(sb);
                game.shot();
            }
            time++;

            iterator.refreshCursor();
            for (; iterator.hasNext(); ) {
                Impediment imp = (Impediment) iterator.next();
                if (imp.getHP() <= 0) {
                    System.out.println("usuwam: " + imp);
                    for (AliveImpediment ai : impediments) {
                        if (ai.getImpediment().equals(imp)) {
                            System.out.println("usuwam: " + ai.getImpediment().getClass());
                            ai.removeBody(world);
                            this.impediments.removeValue(ai, false);
                        }
                    }
                    iterator.remove();
                }
            }
            sb.end();

            if (rocket.getHP() <= 0) {
                gameover = true;
                isStarted=false;
            }

            camera.update();// Ezunia its me bartolomeo
            debugRenderer.render(world, camera.combined);
        }
        else{
        sb.begin();
        start.getData().setScale(5,5);
            shop.getData().setScale(5,5);
            start.draw(sb, "Sklep", Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()/4);
            shop.draw(sb, "Start", Gdx.graphics.getWidth()/2-50, 3*Gdx.graphics.getHeight()/4);
        sb.end();
        }

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
            for (int i = 0; i < impediments.size; i++) {
                if (impediments.get(i).getImpediment().equals(imp)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
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
