package pl.space_marine.game.impediments;

public interface Listener {

        // interfejs funkcyjny listenera (można lambdować)
    public void update(Impediment impediment, CollisionType collisionType);

}
