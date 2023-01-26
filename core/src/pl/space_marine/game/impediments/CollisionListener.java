package pl.space_marine.game.impediments;

import pl.space_marine.game.rocket.Rocket;

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