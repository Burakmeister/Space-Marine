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
    public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static final int HEIGHT_BETWEEN_IMPEDIMENTS = 100;

    private Builder builder;
    private ImpedimentsIterator iterator;
    private Rocket rocket = Rocket.getInstance();

    private boolean isPaused = false;
    private boolean isGameover = false;

    public Game() {
        this.builder = new DefaultBuilder();
        this.rocket.setX(SCREEN_WIDTH / 2 - rocket.getImage().getTexture().getWidth() / 2);
        this.rocket.setY(1);
        for (int y = 0; y < SCREEN_HEIGHT; y += HEIGHT_BETWEEN_IMPEDIMENTS) {
            drawImpediments();
        }
        this.rocket.setY(0);
    }

    public void maneuver(boolean isRight) {
        //not implemented yet
    }

    public ImpedimentsIterator<Impediment> drawImpediments() {
        if (this.rocket.getY() % 50 == 1) {
            Random rand = new Random();
            int x = rand.nextInt(SCREEN_WIDTH);
            builder.setX(x);
            builder.moveHigher(HEIGHT_BETWEEN_IMPEDIMENTS);
            int randInt = rand.nextInt(10);
            switch (randInt) {
                case 0:
                    builder.setDirection(rand.nextFloat() + 0.5f);
                    builder.setSpeed(rand.nextInt(5));
                    builder.addBalloon();
                    break;
                case 1:
                    switch (rand.nextInt(2)) {
                        case 0:
                            builder.setDirection(0.5f);
                            break;
                        case 1:
                            builder.setDirection(1.5f);
                            break;
                    }
                    builder.setSpeed(rand.nextInt(5));
                    builder.addBird();
                    break;
                case 2:
                    builder.setSpeed(0);
                    builder.addCloud();
                    break;
                case 3:
                    builder.setDirection(rand.nextFloat() + 0.5f);
                    builder.setSpeed(rand.nextInt(5));
                    builder.addDragon();
                    break;
                case 4:
                    builder.setDirection(rand.nextFloat() + 0.5f);
                    builder.setSpeed(rand.nextInt(5));
                    builder.addSatelite();
                    break;
                case 5:
                    builder.setDirection(rand.nextFloat() + 0.5f);
                    builder.setSpeed(rand.nextInt(5));
                    builder.addMeteor();
                    break;
                case 6:
                    builder.setDirection(rand.nextFloat() + 0.5f);
                    builder.setSpeed(rand.nextInt(5));
                    builder.addUfo();
                    break;
                case 7:
                    builder.setDirection(rand.nextFloat() + 0.5f);
                    builder.setSpeed(rand.nextInt(5));
                    builder.addDrone();
                    break;
                case 8:
                    builder.setSpeed(0);
                    builder.addSpeedGate();
                    break;
                case 9:
                    switch (rand.nextInt(2)) {
                        case 0:
                            builder.setDirection(0.5f);
                            break;
                        case 1:
                            builder.setDirection(1.5f);
                            break;
                    }
                    builder.setSpeed(rand.nextInt(5));
                    builder.addPlane();
                    break;
            }
            iterator = this.builder.getResult();
        }
        iterator.refreshCursor();
        moveImpediments(iterator);
        return iterator;
    }

    private void moveImpediments(ImpedimentsIterator iterator){
        for(; iterator.hasNext();){
            break;
        }
    }

    public Rocket getRocket() {
        return rocket;
    }
}
