package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Listener;
import pl.space_marine.game.impediments.Obstacle;

public class Satelite extends Obstacle {
    public Satelite(Image image, float collisionDamage, float direction, int speed, int x, int y) {
        super(image, null, collisionDamage, direction, speed, x, y);
    }
}
