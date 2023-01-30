package pl.space_marine.game;

import static java.lang.System.exit;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.bullets.EDMBullet;
import pl.space_marine.game.bullets.FireBullet;
import pl.space_marine.game.bullets.LaserBullet;
import pl.space_marine.game.bullets.ShotBullet;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.impediments.enemies.Dragon;
import pl.space_marine.game.impediments.enemies.Drone;
import pl.space_marine.game.impediments.enemies.Ufo;
import pl.space_marine.game.impediments.obstacles.Balloon;
import pl.space_marine.game.impediments.obstacles.Bird;
import pl.space_marine.game.impediments.obstacles.Cloud;
import pl.space_marine.game.impediments.obstacles.Meteor;
import pl.space_marine.game.impediments.obstacles.Plane;
import pl.space_marine.game.impediments.obstacles.Satelite;
import pl.space_marine.game.impediments.obstacles.SpeedGate;
import pl.space_marine.game.rocket.Rocket;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA == null || fixtureB == null) return;

        Body body1 = fixtureA.getBody(), body2 = fixtureB.getBody();
        if (body1 == null || body2 == null) return;

//        System.out.println(body1.getUserData() + " : " + body2.getUserData());

        if (body1.getUserData() instanceof Impediment && body2.getUserData() instanceof Rocket
                || body1.getUserData() instanceof Rocket && body2.getUserData() instanceof Impediment) {

            Impediment impediment = null;
            Rocket rocket = null;

            if (body1.getUserData() instanceof Impediment) {
                impediment = (Impediment) body1.getUserData();
                rocket = (Rocket) body2.getUserData();
            } else if (body1.getUserData() instanceof Rocket) {
                impediment = (Impediment) body2.getUserData();
                rocket = (Rocket) body1.getUserData();
            }


            if (impediment instanceof Dragon) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof Drone) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof Ufo) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof Balloon) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof Bird) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof Cloud) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof Meteor) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof Plane) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof Satelite) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            } else if (impediment instanceof SpeedGate) {
                impediment.looseHP(1);
                rocket.looseHP(impediment.getCollisionDamage());
            }
        }

        if(body1.getUserData() instanceof Bullet && body2.getUserData() instanceof Impediment
                || body1.getUserData() instanceof Impediment && body2.getUserData() instanceof Bullet){
            Impediment impediment = null;
            Bullet bullet = null;

            if (body1.getUserData() instanceof Impediment) {
                impediment = (Impediment) body1.getUserData();
                bullet = (Bullet) body2.getUserData();
            } else if (body1.getUserData() instanceof Bullet) {
                impediment = (Impediment) body2.getUserData();
                bullet = (Bullet) body1.getUserData();
            }

            if (impediment instanceof Dragon) {

            } else if (impediment instanceof Drone) {

            } else if (impediment instanceof Ufo) {

            } else if (impediment instanceof Balloon) {

            } else if (impediment instanceof Bird) {

            } else if (impediment instanceof Cloud) {

            } else if (impediment instanceof Meteor) {

            } else if (impediment instanceof Plane) {

            } else if (impediment instanceof Satelite) {

            } else if (impediment instanceof SpeedGate) {

            }

        }

        if(body1.getUserData() instanceof Bullet && body2.getUserData() instanceof Rocket
                || body1.getUserData() instanceof Rocket && body2.getUserData() instanceof Bullet){
            Rocket rocket = null;
            Bullet bullet = null;

            if (body1.getUserData() instanceof Impediment) {
                rocket = (Rocket) body1.getUserData();
                bullet = (Bullet) body2.getUserData();
            } else if (body1.getUserData() instanceof Rocket) {
                rocket = (Rocket) body2.getUserData();
                bullet = (Bullet) body1.getUserData();
            }

            if(bullet instanceof ShotBullet){

            }else if(bullet instanceof EDMBullet){

            }else if(bullet instanceof FireBullet){

            }else if(bullet instanceof LaserBullet){

            }

        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}