package pl.space_marine.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.codeandweb.physicseditor.PhysicsShapeCache;

import pl.space_marine.game.Animator;
import pl.space_marine.game.assets.Direction;
import pl.space_marine.game.impediments.Impediment;

public class AliveImpediment {
    private Body body;
    private Impediment impediment;
    private Object drawer;
    private boolean isAnimation;

    public AliveImpediment(Impediment impediment, SpriteBatch batch, PhysicsShapeCache bodiesCache, World world){
        this.impediment = impediment;
        boolean flip = false;
        if(impediment.getImage().getDirection() == Direction.LEFT){
            impediment.setDirection(impediment.getDirection() + 90);
            if(impediment.getDirection()>0){
                flip=true;
            }
        } else if (impediment.getImage().getDirection() == Direction.RIGHT) {
            impediment.setDirection(impediment.getDirection() - 90);
            if(impediment.getDirection()<0){
                flip=true;
            }
        }

        if(impediment.getImage().getRows()==1 && impediment.getImage().getCols()==1){
            Sprite sprite = new Sprite(impediment.getImage().getTexture());
            sprite.setScale(Renderer.SCALE);
            sprite.setPosition(impediment.getX(), impediment.getY());
            sprite.setFlip(flip, false);
            this.drawer = sprite;
            isAnimation = false;
        }else{
            this.drawer = new Animator(batch, impediment.getImage(), impediment.getX(), impediment.getY(), flip, impediment.getDirection(), Renderer.SCALE);
            isAnimation = true;
        }

        System.out.println(impediment.getImage().getName() + ", x=" + impediment.getX() + ", y=" + impediment.getY());

        body = bodiesCache.createBody(impediment.getImage().getName(), world, Renderer.SCALE,  Renderer.SCALE);
        body.setTransform(impediment.getX(), impediment.getY(), (float) Math.toDegrees(impediment.getDirection()));
    }

    public void update(){
        Transform transform = body.getTransform();
        impediment.setDirection((int) (Math.toDegrees(transform.getRotation())));
        impediment.setX((int) transform.getPosition().x);
        impediment.setY((int) transform.getPosition().y);

        if(isAnimation){
            Animator anime = (Animator) this.drawer;
            anime.setX(impediment.getX());
            anime.setY(impediment.getY());
//            if(needFlip(true)){
//                anime.setFlip(true);
//            }
        }else{
            Sprite sprite = (Sprite) this.drawer;
            sprite.setPosition(impediment.getX(), impediment.getY());
            sprite.setRotation(impediment.getDirection());
            sprite.setOrigin(0,0);
//            if(needFlip(false)){
//                sprite.flip(true, false);
//            }
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

    private boolean needFlip(boolean isAnimation){
        switch(impediment.getImage().getDirection()){
            case RIGHT:
                if(impediment.getDirection()<0){
                    impediment.getImage().setDirection(Direction.LEFT);
                    return true;
                }
                break;
            case LEFT:
                if(impediment.getDirection()>0){
                    impediment.getImage().setDirection(Direction.RIGHT);
                    return true;
                }
                break;
        }
        return false;
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
