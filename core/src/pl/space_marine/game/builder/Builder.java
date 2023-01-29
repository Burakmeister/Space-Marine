package pl.space_marine.game.builder;

import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.iterator.ImpedimentsIterator;

public interface Builder {
    public void addBirdL() throws Exception;
    public void addBirdR() throws Exception;
    public void addPlaneL() throws Exception;
    public void addPlaneR() throws Exception;
    public void addCloud() throws Exception;
    public void addBalloon() throws Exception;
    public void addSpeedGate() throws Exception;
    public void addSatelite() throws Exception;
    public void addMeteor() throws Exception;
    public void addDrone() throws Exception;
    public void addUfo() throws Exception;
    public void addDragon() throws Exception;
    public void setY(int y);
    public void setX(int move);
    public void setDirection(int direction);
    public void setSpeed(int speed);
    public void setCollisionDamage(float collisionDamage);
    public ImpedimentsIterator<Impediment> getResult();
    public int getHeight();
}
