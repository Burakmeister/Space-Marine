package pl.space_marine.game.listener;

import com.badlogic.gdx.Gdx;

import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.impediments.obstacles.Bird;
import pl.space_marine.game.iterator.ImpedimentsIterator;

public class DefaulRocketShotListener implements Listener {
    private ImpedimentsIterator<Impediment> impediments;

    public DefaulRocketShotListener(ImpedimentsIterator<Impediment> impediments) {
        this.impediments = impediments;
    }

    @Override
    public void update(int x, int y) {
        impediments.refreshCursor();
        while (impediments.hasNext()) {
            Impediment impediment = this.impediments.next();
            if (impediment instanceof Bird) {

                if (Math.pow((impediment.getX() - x), 2) + Math.pow((impediment.getY() - y), 2) < Gdx.graphics.getWidth() / 2) {
                    impediment.setSpeed(impediment.getSpeed() + 1);
                    System.out.println("speed set:" + impediment.getSpeed());
                }
            }
        }
    }
}
