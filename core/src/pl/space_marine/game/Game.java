package pl.space_marine.game;

import java.util.Random;

import pl.space_marine.game.builder.Builder;
import pl.space_marine.game.builder.DefaultBuilder;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentList;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.rocket.Rocket;

public class Game {
    public static final int DISTANCE_TO_ROCKET = 350;
    public static final int MAX_IMPEDIMENTS_ON_SCREEN = 30;

    private Builder builder;
    private Rocket rocket = Rocket.getInstance();

    public Game() {
        this.builder = new DefaultBuilder(rocket, MAX_IMPEDIMENTS_ON_SCREEN);
    }

    public ImpedimentsIterator<Impediment> drawImpediments() {
        try{
            Random rand = new Random();
            int x = rand.nextInt(DISTANCE_TO_ROCKET)-DISTANCE_TO_ROCKET/2 + rocket.getX();
            builder.setX(x);
            builder.setY((int) (rand.nextInt(DISTANCE_TO_ROCKET)+3.0/2*DISTANCE_TO_ROCKET+rocket.getY()));
            int randInt = rand.nextInt(100);
            if(rocket.getY()<1500){
                if(randInt<10){
                    builder.setDirection(0);
                    builder.setSpeed(0);
                    builder.addDrone();
                }else if(randInt<30){
                    builder.setSpeed(rand.nextInt(3));
                    builder.setDirection(0);
                    builder.addBalloon();
                }else if(randInt<50){
                    builder.setSpeed(rand.nextInt(10));
                    if(randInt<40){
                        builder.setDirection(90);
                        builder.addBirdR();
                    }else{
                        builder.setDirection(-90);
                        builder.addBirdL();
                    }
                }else{
                    if(randInt<75){
                        builder.setDirection(90);
                        builder.setSpeed(0);
                    }else{
                        builder.setDirection(-90);
                        builder.setSpeed(0);
                    }
                    builder.addCloud();
                }
            }else if(rocket.getY()<3000){
                if(randInt<5){
                    builder.setDirection(180);
                    builder.setSpeed(0);
                    builder.addDragon();
                }else if(randInt<20){
                    builder.setSpeed(rand.nextInt(10));
                    if(randInt<27){
                        builder.setDirection(90);
                        builder.addBirdR();
                    }else{
                        builder.setDirection(-90);
                        builder.addBirdL();
                    }
                }else if(randInt<35){
                    builder.setSpeed(rand.nextInt(20));
                    if(randInt<47){
                        builder.setDirection(90);
                        builder.addPlaneR();
                    }else{
                        builder.setDirection(-90);
                        builder.addPlaneL();
                    }
                }else if(randInt<50){
                    builder.setSpeed(rand.nextInt(3));
                    builder.setDirection(0);
                    builder.addBalloon();
                }else{
                    if(randInt<75){
                        builder.setDirection(90);
                        builder.setSpeed(0);
                    }else{
                        builder.setDirection(-90);
                        builder.setSpeed(0);
                    }
                    builder.addCloud();
                }
            }else{
                if(randInt<10){
                    builder.setDirection(180);
                    builder.setSpeed(0);
                    builder.addUfo();
                }else if(randInt<20){
                    builder.setDirection(180);
                    builder.setSpeed(0);
                    builder.addDragon();
                }else if(randInt<40){
                    builder.setDirection(rand.nextInt(180)+90);
                    builder.setSpeed(rand.nextInt(10));
                    builder.addSatelite();
                }else if(randInt<60){
                    builder.setDirection(rand.nextInt(120)+120);
                    builder.setSpeed(rand.nextInt(30));
                    builder.addMeteor();
                }
            }
            return builder.getResult();
        }catch (Exception ex){
            System.exit(0);
        }
        return null;
    }


    public Rocket getRocket() {
        return rocket;
    }
}
