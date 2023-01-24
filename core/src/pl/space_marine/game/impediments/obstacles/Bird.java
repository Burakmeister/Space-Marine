package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Listener;

public class Bird extends Obstacle{
    public Bird(float collisionDamage, float direction, int speed, int x, int y) {
        super(Image.BIRD, listeners, collisionDamage, direction, speed, x, y);
    }
}
