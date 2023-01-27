package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Obstacle;

public class Cloud extends Obstacle {
    private boolean storm;

    public Cloud(Image image, float collisionDamage, float direction, int speed, int x, int y) {
        super(image, null, collisionDamage, direction, speed, x, y);
    }

    public void collisionEffect(){
        // not implemented yet
    }
}
