package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Obstacle;
import pl.space_marine.game.rocket.Rocket;

public class SpeedGate extends Obstacle {
    private float acceleration;

    public SpeedGate(Image image, float collisionDamage, int direction, int speed, int x, int y) {
        super(image, null, collisionDamage, direction, speed, x, y);
    }

    public void collisionEffect(Rocket rocket){
        // not implemented yet
    }
}
