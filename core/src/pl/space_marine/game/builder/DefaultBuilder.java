package pl.space_marine.game.builder;
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
import pl.space_marine.game.iterator.ImpedimentList;
import pl.space_marine.game.iterator.ImpedimentsIterator;
import pl.space_marine.game.rocket.Rocket;

public class DefaultBuilder implements Builder{
    private int y;
    private int x;
    private float collisionDamage;
    private int direction;
    private int speed;
    private ImpedimentsIterator<Impediment> list;

    public DefaultBuilder(Rocket rocket, int maxImpediments){
        this.list = (ImpedimentsIterator<Impediment>) new ImpedimentList<Impediment>(rocket, maxImpediments).iterator();
        this.y=0;
    }
    @Override
    public void addBirdL() throws Exception {
        this.list.add(new Bird(Image.BIRD_L, collisionDamage, direction, speed, x, y));
    }
    @Override
    public void addBirdR() throws Exception {
        this.list.add(new Bird(Image.BIRD_R, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addPlaneL() throws Exception {
        this.list.add(new Plane(Image.PLANE_L, collisionDamage, direction, speed, x, y));
    }
    @Override
    public void addPlaneR() throws Exception {
        this.list.add(new Plane(Image.PLANE_R, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addCloud() throws Exception {
        Random rand = new Random();
        switch(rand.nextInt(3)){
            case 0:
                this.list.add(new Cloud(Image.CLOUD, collisionDamage, direction, speed, x, y, rand.nextBoolean(), rand.nextInt(5)==0));
                break;
            case 1:
                this.list.add(new Cloud(Image.CLOUD2, collisionDamage, direction, speed, x, y, rand.nextBoolean(), rand.nextInt(5)==0));
                break;
            case 2:
                this.list.add(new Cloud(Image.CLOUD3, collisionDamage, direction, speed, x, y, rand.nextBoolean(), rand.nextInt(5)==0));
                break;
        }
    }

    @Override
    public void addBalloon() throws Exception {
        this.list.add(new Balloon(Image.BALLOON, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addSpeedGate() throws Exception {
        this.list.add(new SpeedGate(Image.SPEED_GATE, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addSatelite() throws Exception {
        this.list.add(new Satelite(Image.SATELLITE, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addMeteor() throws Exception {
        this.list.add(new Meteor(Image.METHEOR, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addDrone() throws Exception {
        this.list.add(new Drone(Image.DRONE, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addUfo() throws Exception {
        this.list.add(new Ufo(Image.UFO, collisionDamage, direction, speed, x, y));
    }

    @Override
    public void addDragon() throws Exception {
        this.list.add(new Dragon(Image.DRAGON, collisionDamage, direction, speed, x, y));
    }
    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setX(int move) {
        this.x = move;
    }

    @Override
    public void setDirection(int direction) {
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
