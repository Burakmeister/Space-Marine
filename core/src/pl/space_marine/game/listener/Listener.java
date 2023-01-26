package pl.space_marine.game.listener;

import pl.space_marine.game.impediments.CollisionType;
import pl.space_marine.game.impediments.Impediment;

public interface Listener {

        // interfejs funkcyjny listenera (można lambdować)
    public void update(Impediment impediment, CollisionType collisionType);

}
