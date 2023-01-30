package pl.space_marine.game;

import static pl.space_marine.game.Renderer.SCALE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.codeandweb.physicseditor.PhysicsShapeCache;

import java.util.ArrayList;
import java.util.List;

import pl.space_marine.game.assets.Audio;
import pl.space_marine.game.assets.Image;
import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.bullets.ShotBullet;
import pl.space_marine.game.impediments.Impediment;

public class BulletRenderer {
    private Bullet bullet;
    private Sprite sprite;
    private Body body;

    private float vx, vy;

    public BulletRenderer(Bullet bullet, World world) {
        this.bullet = bullet;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(bullet.getX(), bullet.getY());
        bodyDef.fixedRotation = false;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Image.SHOTBULLET.getTexture().getWidth()*SCALE, Image.SHOTBULLET.getTexture().getHeight()*SCALE);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.setUserData(bullet);
        body.createFixture(fixtureDef);
        shape.dispose();
        this.body=body;
        sprite = new Sprite(bullet.getImage().getTexture());
        sprite.setScale(SCALE);
        sprite.setOrigin(0,0);
        sprite.setPosition(bullet.getX(), bullet.getY());
        this.sprite=sprite;
    }

    public void update() {
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        bullet.setX(body.getPosition().x);
        bullet.setY(body.getPosition().y);
    }

    public void draw(SpriteBatch sb) {
        sprite.draw(sb);
    }

    public Body getBody() {
        return body;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getVy() {
        return vy;
    }

    public float getVx() {
        return vx;
    }
}

