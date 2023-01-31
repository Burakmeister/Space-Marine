package pl.space_marine.game;

import static pl.space_marine.game.Renderer.SCALE;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.rocket.Rocket;

public class BulletRenderer {
    private Bullet bullet;
    private Sprite sprite;
    private Body body;

    private float vx, vy;

    public BulletRenderer(Bullet bullet, World world, Sprite rocketSprite, Body rocketBody) {
        this.bullet = bullet;
        float hw = rocketSprite.getWidth() * SCALE;
//        System.out.println(hw);
        float hh = rocketSprite.getHeight() * SCALE;
//        System.out.println(hh);

        float r = (float) Math.sqrt(Math.pow(hh, 2) + Math.pow(hw, 2));

        float rot = (float) Math.toRadians(rocketSprite.getRotation());

        bullet.setX((int) (rocketBody.getPosition().x + r * (float) Math.sin(rot + Math.PI - Math.atan(hw / 2 / hh))));
        bullet.setY((int) (rocketBody.getPosition().y + r * (float) Math.cos(rot - Math.atan(hw / 2 / hh))));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(bullet.getX(), bullet.getY());
//        bodyDef.fixedRotation = true;
        bodyDef.bullet = true;
        bodyDef.angle = rocketBody.getAngle();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Image.SHOTBULLET.getTexture().getWidth() * SCALE, Image.SHOTBULLET.getTexture().getHeight() * SCALE);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        shape.dispose();

        Body body = world.createBody(bodyDef);
        body.setUserData(bullet);
        body.createFixture(fixtureDef);

        this.body = body;
        sprite = new Sprite(bullet.getImage().getTexture());
        sprite.setScale(SCALE);
        sprite.setRotation((float) Math.toDegrees(rocketBody.getAngle()));
        sprite.setX(rocketSprite.getX());
        sprite.setY(rocketSprite.getY());
        sprite.setOrigin(0, 0);
    }

    public void update() {
        bullet.setX((int) body.getPosition().x);
        bullet.setY((int) body.getPosition().y);
//        getBody().applyLinearImpulse(new Vector2(getVx() * 1000000,
//                getVy() * 1000000), getBody().getWorldCenter(), true);

        sprite.setPosition(bullet.getX(), bullet.getY());
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

