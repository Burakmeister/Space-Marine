package pl.space_marine.game.listener;

import pl.space_marine.game.impediments.CollisionType;
import pl.space_marine.game.impediments.Impediment;

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