package pl.space_marine.game;

import com.badlogic.gdx.Gdx;

import java.util.Iterator;
import java.util.Random;

import pl.space_marine.game.builder.Builder;
import pl.space_marine.game.builder.DefaultBuilder;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.rocket.Rocket;

public class Game {
    public static final int HEIGHT_BETWEEN_IMPEDIMENTS = 10;

    private Builder builder;
    private ImpedimentsIterator iterator;
    private Rocket rocket = Rocket.getInstance();

    private boolean isPaused = false;
    private boolean isGameover = false;

    public Game() {
        this.builder = new DefaultBuilder();
    }

    public ImpedimentsIterator<Impediment> drawImpediments() {
        builder.clear();
        Random rand = new Random();
        int x = rand.nextInt(5000)-2500;
        builder.setX(x);
        builder.moveHigher(HEIGHT_BETWEEN_IMPEDIMENTS);
        int randInt = rand.nextInt(15);
        switch (randInt) {
            case 0:
                builder.setDirection(rand.nextInt(360)-180);
                builder.setSpeed(rand.nextInt(5));
                builder.addBalloon();
                break;
            case 1:
                switch (rand.nextInt(2)) {
                    case 0:
                        builder.setDirection(90);
                        break;
                    case 1:
                        builder.setDirection(-90);
                        break;
                }
                builder.setSpeed(rand.nextInt(5));
                builder.addBird();
                break;
            case 3:
                builder.setDirection(rand.nextInt(360)-180);
                builder.setSpeed(rand.nextInt(5));
                builder.addDragon();
                break;
            case 4:
                builder.setDirection(rand.nextInt(360)-180);
                builder.setSpeed(rand.nextInt(5));
                builder.addSatelite();
                break;
            case 5:
                builder.setDirection(rand.nextInt(360)-180);
                builder.setSpeed(rand.nextInt(5));
                builder.addMeteor();
                break;
            case 6:
                builder.setDirection(rand.nextInt(360)-180);
                builder.setSpeed(rand.nextInt(5));
                builder.addUfo();
                break;
            case 7:
                builder.setDirection(rand.nextInt(360)-180);
                builder.setSpeed(rand.nextInt(5));
                builder.addDrone();
                break;
            case 8:
                builder.setSpeed(0);
//                builder.addSpeedGate();
                break;
            case 9:
                switch (rand.nextInt(2)) {
                    case 0:
                        builder.setDirection(90);
                        break;
                    case 1:
                        builder.setDirection(-90);
                        break;
                }
                builder.setSpeed(rand.nextInt(5));
                builder.addPlane();
                break;
            default: {
                builder.setSpeed(0);
                builder.setDirection(0);
                builder.addCloud();
                break;
            }
        }
        return builder.getResult();
    }


    public Rocket getRocket() {
        return rocket;
    }
}
