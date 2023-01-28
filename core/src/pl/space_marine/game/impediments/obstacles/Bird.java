package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Obstacle;

public class Bird extends Obstacle {
    public Bird(Image image, float collisionDamage, int direction, int speed, int x, int y) {
        super(image, null, collisionDamage, direction, speed, x, y);
    }
}
