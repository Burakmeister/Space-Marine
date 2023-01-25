package pl.space_marine.game.impediments;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Impediment;
import pl.space_marine.game.impediments.Listener;

public abstract class Obstacle extends Impediment {
    protected boolean isTransparent;

    public Obstacle(Image image, Listener[] listeners, float collisionDamage, float direction, int speed, int x, int y) {
        super(image, listeners, collisionDamage, direction, speed, x, y);
    }
}
