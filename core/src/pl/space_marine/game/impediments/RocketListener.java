package pl.space_marine.game.impediments;

import pl.space_marine.game.rocket.Rocket;

public class RocketListener implements Listener {
    @Override
    public void update(Impediment impediment, CollisionType collisionType) {
        if (collisionType == CollisionType.ROCKET) {
            //kolizja rakiety z innym obiektem
        } else if (collisionType == CollisionType.BONUS) {
            //kolizja rakiety z Bonus
        }else if (collisionType == CollisionType.ENEMY_SHOT) {
            //kolizja rakiety z pociskiem
        }
        }
    }