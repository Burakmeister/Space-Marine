package pl.space_marine.game.states;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private SpriteBatch batch;
    private Sprite sprite;
    private ShotBullet shotBullet;
    private PhysicsShapeCache bodiesCache;
    private World world;
    public BulletRenderer(Bullet bullet, SpriteBatch batch, PhysicsShapeCache bodiesCache, World world) {
        this.bullet = bullet;
        this.batch = batch;
        this.bodiesCache = bodiesCache;
        this.world = world;
        this.sprite = new Sprite(bullet.getImage().getTexture());
    }
    public void update() {
        bullet.setX(bullet.getX() + bullet.getVelocityX());
        bullet.setY(bullet.getY() + bullet.getVelocityY());
        sprite.setPosition(bullet.getX(), bullet.getY());
    }
    public void draw(SpriteBatch sb) {
        sb.begin();
        sb.draw(bullet.getImage().getTexture(), bullet.getX(), bullet.getY());
        sb.end();
    }
}

