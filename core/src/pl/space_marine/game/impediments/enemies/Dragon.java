package pl.space_marine.game.impediments.enemies;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.impediments.CollisionBulletListener;
import pl.space_marine.game.impediments.CollisionImpedimentListener;
import pl.space_marine.game.impediments.Listener;

public class Dragon extends Enemy{
    public Dragon(float collisionDamage, float direction, int speed, int x, int y) {
        super(Image.DRAGON, new Listener[]{new CollisionBulletListener(), new CollisionImpedimentListener()}, collisionDamage, direction, speed, x, y);
    }
}
