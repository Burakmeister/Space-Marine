package pl.space_marine.game.states;

import static java.lang.System.exit;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import pl.space_marine.game.AliveImpediment;
import pl.space_marine.game.impediments.Enemy;
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


        if(fixtureA == null || fixtureB == null) return;
        if(fixtureA.getUserData() == null || fixtureB.getUserData() == null) return;



        if (fixtureA.getUserData() instanceof Body && fixtureB.getUserData() instanceof Body) {
            Body bodyA = (Body) fixtureA.getUserData();
            Body bodyB = (Body) fixtureB.getUserData();


            if (bodyA.getUserData() instanceof AliveImpediment && bodyB.getUserData() instanceof Rocket) {
                AliveImpediment aliveImpediment = (AliveImpediment) bodyA.getUserData();
                Rocket rocket = (Rocket) bodyB.getUserData();


                if (aliveImpediment.getImpediment() instanceof Dragon) {
                    exit(2);
                } else if (aliveImpediment.getImpediment() instanceof Drone) {
                    exit(2);
                } else if (aliveImpediment.getImpediment() instanceof Ufo) {
                    exit(2);
                } else if (aliveImpediment.getImpediment() instanceof Balloon) {
                    exit(2);
                } else if (aliveImpediment.getImpediment() instanceof Bird) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof Cloud) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof Meteor) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof Plane) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof Satelite) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof SpeedGate) {
                    exit(2);
                }

                // your collision handling code here
                System.out.println("no i jeblo");
            }


            if (bodyB.getUserData() instanceof AliveImpediment && bodyA.getUserData() instanceof Rocket) {
                AliveImpediment aliveImpediment = (AliveImpediment) bodyB.getUserData();
                Rocket rocket = (Rocket) bodyA.getUserData();

                if (aliveImpediment.getImpediment() instanceof Dragon) {
                    exit(2);
                } else if (aliveImpediment.getImpediment() instanceof Drone) {
                    exit(2);
                } else if (aliveImpediment.getImpediment() instanceof Ufo) {
                    exit(2);
                } else if (aliveImpediment.getImpediment() instanceof Balloon) {
                    exit(2);
                } else if (aliveImpediment.getImpediment() instanceof Bird) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof Cloud) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof Meteor) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof Plane) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof Satelite) {
                    exit(2);
                }else if (aliveImpediment.getImpediment() instanceof SpeedGate) {
                    exit(2);
                }

                System.out.println("no i jeblo");
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