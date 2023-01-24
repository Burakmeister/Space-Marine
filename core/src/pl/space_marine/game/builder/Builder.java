package pl.space_marine.game.builder;

import pl.space_marine.game.iterator.ImpedimentsList;

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
    public ImpedimentsList getResult();
}
