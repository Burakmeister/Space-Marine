package pl.space_marine.game.impediments.obstacles;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.Obstacle;

public class Cloud extends Obstacle {
    private boolean storm;
    private boolean flip;

    public Cloud(Image image, float collisionDamage, int direction, int speed, int x, int y, boolean flip, boolean storm) {
        super(image, null, collisionDamage, direction, speed, x, y);
        this.flip=flip;
        this.storm=storm;
    }

    public void collisionEffect(){
        // not implemented yet
    }

    public boolean isFlip() {
        return flip;
    }

    public boolean isStorm() {
        return storm;
    }
}
