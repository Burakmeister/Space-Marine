package pl.space_marine.game;

import static pl.space_marine.game.Renderer.SCALE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.codeandweb.physicseditor.PhysicsShapeCache;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.impediments.obstacles.Cloud;

public class AliveImpediment {
    private Body body;
    private Impediment impediment;
    private Object drawer;
    private boolean isAnimation;
    private short endFrames = 0;
    private boolean toRemove = false;

    public AliveImpediment(Impediment impediment, SpriteBatch batch, PhysicsShapeCache bodiesCache, World world) {
        this.impediment = impediment;

        if (impediment.getImage().getRows() == 1 && impediment.getImage().getCols() == 1) {
            Sprite sprite = new Sprite(impediment.getImage().getTexture());
            sprite.setPosition(impediment.getX(), impediment.getY());
            sprite.setScale(SCALE);
            sprite.setOrigin(0,0);
            this.drawer = sprite;
            isAnimation = false;
        } else {
            this.drawer = new Animator(batch, impediment.getImage(), impediment.getX(), impediment.getY(), false, 0, SCALE);
            isAnimation = true;
        }


        if (!(impediment instanceof Cloud)) {
            body = bodiesCache.createBody(impediment.getImage().getName(), world, SCALE, SCALE);
            body.setTransform(impediment.getX(), impediment.getY(), 0);
            body.setUserData(impediment);
//            body.setGravityScale(1);
        }else{
            Cloud cloud = (Cloud) impediment;
            Sprite sprite = (Sprite) drawer;
            sprite.setFlip(cloud.isFlip(), false);
        }
    }

    public void update() {
        if(this.endFrames==0){
            if (!(impediment instanceof Cloud)) {
//                if (impediment.getSpeed() != 0) {
//                    float x = (float) Math.sin(body.getAngle() - Math.PI);
//                    float y = (float) Math.cos(body.getAngle());
//
//                    body.applyForceToCenter(new Vector2(
//                                    body.getMass() * (x * impediment.getSpeed()),//x force to apply
//                                    body.getMass() * (y * impediment.getSpeed())),
//                            true);
//                }

                impediment.setX((int) body.getPosition().x);
                impediment.setY((int) body.getPosition().y);

                if (isAnimation) {
                    Animator anime = (Animator) this.drawer;
                    anime.setX(impediment.getX());
                    anime.setY(impediment.getY());
                    anime.setRotation((int) Math.toDegrees(body.getAngle()));
                } else {
                    Sprite sprite = (Sprite) this.drawer;
                    sprite.setPosition(impediment.getX(), impediment.getY());
                    sprite.setRotation((float) Math.toDegrees(body.getAngle()));
                }

            }else{
                ((Sprite) drawer).setPosition(impediment.getX(), impediment.getY());
            }
        }
    }

    public void draw(SpriteBatch sb) {
        if (drawer instanceof Sprite) {
            Sprite drawer = (Sprite) this.drawer;
//            System.out.println("Sprite: " + drawer.getX);
            drawer.draw(sb);
        } else {
            Animator drawer = (Animator) this.drawer;
            drawer.render();
            if(endFrames>0){
                endFrames++;
                if(endFrames>=15){
                    this.toRemove = true;
                }
            }
        }
    }

    public void dispose() {
        if (this.drawer instanceof Animator) {
            Animator anime = (Animator) this.drawer;
            anime.dispose();
        }
    }

    public Body getBody() {
        return body;
    }

    public Impediment getImpediment() {
        return impediment;
    }

    public void removeBody(World world, SpriteBatch sb){
        world.destroyBody(body);
        drawer = new Animator(sb, Image.EXPLOSION, this.impediment.getX(), impediment.getY());
        ((Animator) drawer).render();
        isAnimation = true;
        endFrames = 1;
    }

    public boolean isToRemove() {
        return toRemove;
    }
}
