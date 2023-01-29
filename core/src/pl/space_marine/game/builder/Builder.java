package pl.space_marine.game.builder;

import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentsIterator;

public interface Builder {
    public void addBird();
    public void addPlane();
    public void addCloud();
    public void addBalloon();
    public void addSpeedGate();
    public void addSatelite();
    public void addMeteor();
    public void addDrone();
    public void addUfo();
    public void addDragon();
    public void moveHigher(int up);
    public void setX(int move);
    public void setDirection(int direction);
    public void setSpeed(int speed);
    public void setCollisionDamage(float collisionDamage);
    public ImpedimentsIterator<Impediment> getResult();
    public void clear();
    public int getHeight();
}
