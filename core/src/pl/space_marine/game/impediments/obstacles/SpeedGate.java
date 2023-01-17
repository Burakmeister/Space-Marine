package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Listener;
import pl.space_marine.game.rocket.Rocket;

public class SpeedGate extends Obstacle{
    private float acceleration;

    public SpeedGate(Image image, Listener[] listeners, float collisionDamage, float direction, int speed, int x, int y) {
        super(image, listeners, collisionDamage, direction, speed, x, y);
    }

    public void collisionEffect(Rocket rocket){
        // not implemented yet
    }
}
