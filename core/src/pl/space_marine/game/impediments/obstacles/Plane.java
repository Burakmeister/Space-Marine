package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Obstacle;

public class Plane extends Obstacle {
    public Plane(Image image, float collisionDamage, float direction, int speed, int x, int y) {
        super(image, null, collisionDamage, direction, speed, x, y);
    }
}
