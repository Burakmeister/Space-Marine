package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Listener;

public class Cloud extends Obstacle{
    private boolean storm;

    public Cloud(Image image, Listener[] listeners, float collisionDamage, float direction, int speed, int x, int y) {
        super(image, listeners, collisionDamage, direction, speed, x, y);
    }

    public void collisionEffect(){
        // not implemented yet
    }
}
