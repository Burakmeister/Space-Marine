package pl.space_marine.game.builder;

import pl.space_marine.game.impediments.obstacles.Bird;
import pl.space_marine.game.iterator.ImpedimentsList;

public class DefaultBuilder implements Builder{
    private int y = 0;
    private ImpedimentsList list;

    public DefaultBuilder(){
        this.list = new ImpedimentsList();
    }
    @Override
    public void addBird() {
        this.list.add(new Bird());
    }

    @Override
    public void addPlane() {

    }

    @Override
    public void addCloud() {

    }

    @Override
    public void addBalloon() {

    }

    @Override
    public void addSpeedGate() {

    }

    @Override
    public void addSatelite() {

    }

    @Override
    public void addMeteor() {

    }

    @Override
    public void addDrone() {

    }

    @Override
    public void addUfo() {

    }

    @Override
    public void addDragon() {

    }

    @Override
    public void moveHigher(int up) {

    }

    @Override
    public ImpedimentsList getResult() {
        return null;
    }
}
