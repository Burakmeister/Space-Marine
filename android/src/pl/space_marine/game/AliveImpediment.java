package pl.space_marine.game;

import static pl.space_marine.game.Renderer.SCALE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.codeandweb.physicseditor.PhysicsShapeCache;

import pl.space_marine.game.Animator;
import pl.space_marine.game.assets.Direction;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.impediments.obstacles.Cloud;

public class AliveImpediment {
    private Body body;
    private Impediment impediment;
    private Object drawer;
    private boolean isAnimation;

    public AliveImpediment(Impediment impediment, SpriteBatch batch, PhysicsShapeCache bodiesCache, World world){
        this.impediment = impediment;

        if(impediment.getImage().getRows()==1 && impediment.getImage().getCols()==1){
            Sprite sprite = new Sprite(impediment.getImage().getTexture());
            sprite.setScale(SCALE);
            sprite.setPosition(impediment.getX(), impediment.getY());
            this.drawer = sprite;
            isAnimation = false;
        }else{
            this.drawer = new Animator(batch, impediment.getImage(), impediment.getX(), impediment.getY(), false, 0, SCALE);
            isAnimation = true;
        }

        System.out.println(impediment.getImage().getName() + ", x=" + impediment.getX() + ", y=" + impediment.getY());
        if (impediment instanceof Cloud) {
            Cloud cloud = (Cloud) impediment;
            Sprite sprite = (Sprite) drawer;
            sprite.setFlip(cloud.isFlip(), false);
        }

        if(!(impediment instanceof Cloud)){
            body = bodiesCache.createBody(impediment.getImage().getName(), world, SCALE,  SCALE);
            body.setTransform(impediment.getX(), impediment.getY(), 0);
//            body.setGravityScale(1);
        }
    }

    public void update(){
        if (!(impediment instanceof Cloud)) {
            Transform transform = body.getTransform();
            impediment.setX((int) transform.getPosition().x);
            impediment.setY((int) transform.getPosition().y);

            if(impediment.getSpeed()!=0){
                float x = (float) Math.sin(body.getAngle() - Math.PI);
                float y = (float) Math.cos(body.getAngle());

                body.applyForceToCenter(new Vector2(
                                body.getMass() * (x * impediment.getSpeed()),//x force to apply
                                body.getMass() * (y * impediment.getSpeed())),
                        true);
            }
        }

        if(isAnimation){
            Animator anime = (Animator) this.drawer;
            anime.setX(impediment.getX());
            anime.setY(impediment.getY());
        }else{
            Sprite sprite = (Sprite) this.drawer;
            sprite.setPosition(impediment.getX(), impediment.getY());
        }
    }

    public void draw(SpriteBatch sb){
        if(drawer instanceof Sprite){
            Sprite drawer = (Sprite) this.drawer;
            drawer.draw(sb);
        }else{
            Animator drawer = (Animator) this.drawer;
            drawer.render();
        }
    }

    public void dispose(){
        if(this.drawer instanceof Animator){
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

    public Object getDrawer() {
        return drawer;
    }

    public boolean isAnimation() {
        return isAnimation;
    }
}
