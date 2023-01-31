package pl.space_marine.game;

import com.badlogic.gdx.Gdx;

import java.util.Random;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.builder.Builder;
import pl.space_marine.game.builder.DefaultBuilder;
import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.bullets.factories.BulletCreator;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.listener.DefaulRocketShotListener;
import pl.space_marine.game.listener.Listener;
import pl.space_marine.game.rocket.Rocket;

public class Game {
    public static final float SCALE = 0.2f;
    public static final int DISTANCE_TO_ROCKET = (int) (7 * Gdx.graphics.getHeight() / 6 * SCALE);  //max
    public static final int DISTANCE_TO_ROCKET_WIDTH = (int) (Gdx.graphics.getWidth() * SCALE);  //max
    public static final int MAX_IMPEDIMENTS_ON_SCREEN = 50;

    private final Builder builder;
    private final Rocket rocket = Rocket.getInstance();
    private final Listener shotListener;
    private final BulletCreator bulletCreator;

    public Game() {
        this.builder = new DefaultBuilder(rocket, MAX_IMPEDIMENTS_ON_SCREEN);
        this.shotListener = new DefaulRocketShotListener(builder.getResult());
        this.bulletCreator = new BulletCreator(rocket);
    }

    public ImpedimentsIterator<Impediment> drawImpediments() {
        try {
            Random rand = new Random();
            int y = rand.nextInt(2 * DISTANCE_TO_ROCKET) - DISTANCE_TO_ROCKET / 4 + rocket.getY();
            int x;
            if (y > rocket.getY() + DISTANCE_TO_ROCKET || y < rocket.getY() - DISTANCE_TO_ROCKET / 4) {
                if (rand.nextBoolean())
                    x = rand.nextInt(DISTANCE_TO_ROCKET_WIDTH) - DISTANCE_TO_ROCKET_WIDTH / 2 + rocket.getX();
                else {
                    if (rand.nextBoolean())
                        x = rand.nextInt((int) (3 * DISTANCE_TO_ROCKET_WIDTH / 2.0)) + (int) (3 * DISTANCE_TO_ROCKET_WIDTH / 4.0) + rocket.getX();
                    else
                        x = rand.nextInt((int) (3 * DISTANCE_TO_ROCKET_WIDTH / 2.0)) - 2 * DISTANCE_TO_ROCKET_WIDTH + rocket.getX() - (int) (Image.CLOUD.getTexture().getWidth() * SCALE);
                }
            } else {
                if (rand.nextBoolean())
                    x = rand.nextInt((int) (3 * DISTANCE_TO_ROCKET_WIDTH / 2.0)) + (int) (3 * DISTANCE_TO_ROCKET_WIDTH / 4.0) + rocket.getX();
                else
                    x = rand.nextInt((int) (3 * DISTANCE_TO_ROCKET_WIDTH / 2.0)) - 2 * DISTANCE_TO_ROCKET_WIDTH + rocket.getX() - (int) (Image.CLOUD.getTexture().getWidth() * SCALE);
            }
            builder.setX(x);
            builder.setY(y);
            int randInt = rand.nextInt(100);
            if (rocket.getY() < 1000) {
                if (randInt < 10) {
                    builder.setCollisionDamage(0.5f);
                    builder.setDirection(0);
                    builder.setSpeed(0);
                    builder.addDrone();
                } else if (randInt < 30) {
                    builder.setCollisionDamage(0.1f);
                    builder.setSpeed(rand.nextInt(3));
                    builder.setDirection(0);
                    builder.addBalloon();
                } else if (randInt < 50) {
                    builder.setCollisionDamage(0.15f);
                    builder.setSpeed(rand.nextInt(10));
                    if (randInt < 40) {
                        builder.setDirection(90);
                        builder.addBirdR();
                    } else {
                        builder.setDirection(-90);
                        builder.addBirdL();
                    }
                } else {
                    builder.setCollisionDamage(0);
                    if (randInt < 75) {
                        builder.setDirection(90);
                        builder.setSpeed(0);
                    } else {
                        builder.setDirection(-90);
                        builder.setSpeed(0);
                    }
                    builder.addCloud();
                }
            } else if (rocket.getY() < 2000) {
                if (randInt < 5) {
                    builder.setCollisionDamage(0.65f);
                    builder.setDirection(180);
                    builder.setSpeed(0);
                    builder.addDragon();
                } else if (randInt < 20) {
                    builder.setCollisionDamage(0.15f);
                    builder.setSpeed(rand.nextInt(10));
                    if (randInt < 12) {
                        builder.setDirection(90);
                        builder.addBirdR();
                    } else {
                        builder.setDirection(-90);
                        builder.addBirdL();
                    }
                } else if (randInt < 35) {
                    builder.setCollisionDamage(0.5f);
                    builder.setSpeed(rand.nextInt(20));
                    if (randInt < 27) {
                        builder.setDirection(90);
                        builder.addPlaneR();
                    } else {
                        builder.setDirection(-90);
                        builder.addPlaneL();
                    }
                } else if (randInt < 50) {
                    builder.setCollisionDamage(0.1f);
                    builder.setSpeed(rand.nextInt(3));
                    builder.setDirection(0);
                    builder.addBalloon();
                } else {
                    builder.setCollisionDamage(0);
                    if (randInt < 75) {
                        builder.setDirection(90);
                        builder.setSpeed(0);
                    } else {
                        builder.setDirection(-90);
                        builder.setSpeed(0);
                    }
                    builder.addCloud();
                }
            } else {
                if (randInt < 10) {
                    builder.setCollisionDamage(0.8f);
                    builder.setDirection(180);
                    builder.setSpeed(0);
                    builder.addUfo();
                } else if (randInt < 20) {
                    builder.setCollisionDamage(0.65f);
                    builder.setDirection(180);
                    builder.setSpeed(0);
                    builder.addDragon();
                } else if (randInt < 40) {
                    builder.setCollisionDamage(0.6f);
                    builder.setDirection(rand.nextInt(180) + 90);
                    builder.setSpeed(rand.nextInt(10));
                    builder.addSatelite();
                } else if (randInt < 60) {
                    builder.setCollisionDamage(0.7f);
                    builder.setDirection(rand.nextInt(120) + 120);
                    builder.setSpeed(rand.nextInt(30));
                    builder.addMeteor();
                }
            }
            return builder.getResult();
        } catch (Exception ex) {
            System.exit(0);
        }
        return null;
    }

    public Bullet shot() {
        return bulletCreator.createBullet();
    }


    public Rocket getRocket() {
        return rocket;
    }

    public Listener getShotListener() {
        return shotListener;
    }
}
