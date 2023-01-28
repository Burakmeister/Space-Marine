package pl.space_marine.game.impediments;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.listener.Listener;

public abstract class Obstacle extends Impediment {
    protected boolean isTransparent;

    public Obstacle(Image image, Listener listener, float collisionDamage, int direction, int speed, int x, int y) {
        super(image, listener, collisionDamage, direction, speed, x, y);
    }
}
