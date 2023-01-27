package pl.space_marine.game.listener;

import pl.space_marine.game.impediments.CollisionType;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.listener.Listener;

public class CollisionListener implements Listener {
    @Override
    public void update(Impediment impediment, CollisionType collisionType) {
        if (collisionType == CollisionType.ROCKET) {
            //kolizja rakiety z Impediment
        } else if (collisionType == CollisionType.ROCKET_SHOT) {
            //kolizja Impediment z strzałem rakiety
        }
    }
}