package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Listener;

public class Balloon extends Obstacle{
    public Balloon(Image image, Listener[] listeners, float collisionDamage, float direction, int speed, int x, int y) {
        super(image, listeners, collisionDamage, direction, speed, x, y);
    }
}
