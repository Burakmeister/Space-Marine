package pl.space_marine.game.builder;

import static pl.space_marine.game.Game.SCREEN_HEIGHT;

import java.util.Random;

import pl.space_marine.game.assets.Image;
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
import pl.space_marine.game.iterator.ImpedimentsIterator;

public class DefaultBuilder implements Builder{
    private int y;
    private int x;
    float collisionDamage;
    float direction;
    int speed;
    private ImpedimentsIterator list;

    public DefaultBuilder(){
        this.list = new ImpedimentsIterator();
        this.y=0;
    }
    @Override
    public void addBird() {
        this.list.add(new Bird(Image.BIRD, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addPlane() {
        this.list.add(new Plane(Image.PLANE, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addCloud() {
        Random rand = new Random();
        switch(rand.nextInt(3)){
            case 0:
                this.list.add(new Cloud(Image.CLOUD, collisionDamage, direction, speed, x, y));
                break;
            case 1:
                this.list.add(new Cloud(Image.CLOUD2, collisionDamage, direction, speed, x, y));
                break;
            case 2:
                this.list.add(new Cloud(Image.CLOUD3, collisionDamage, direction, speed, x, y));
                break;
        }
    }

    @Override
    public void addBalloon() {
        this.list.add(new Balloon(Image.BALLOON, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addSpeedGate() {
        this.list.add(new SpeedGate(Image.SPEED_GATE, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addSatelite() {
        this.list.add(new Satelite(Image.SATELLITE, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addMeteor() {
        this.list.add(new Meteor(Image.METHEOR, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addDrone() {
        this.list.add(new Drone(Image.DRONE, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addUfo() {
        this.list.add(new Ufo(Image.UFO, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addDragon() {
        this.list.add(new Dragon(Image.DRAGON, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void moveHigher(int up) {
        this.y += up;
    }

    @Override
    public void setX(int move) {
        this.x = move;
    }

    @Override
    public void setDirection(float direction) {
        this.direction = direction;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void setCollisionDamage(float collisionDamage) {
        this.collisionDamage = collisionDamage;
    }

    @Override
    public ImpedimentsIterator<Impediment> getResult() {
        return this.list;
    }

    @Override
    public int getHeight() {
        return y;
    }
}
