package pl.space_marine.game;

import pl.space_marine.game.builder.Builder;
import pl.space_marine.game.iterator.ImpedimentsList;
import pl.space_marine.game.rocket.Rocket;

public class Game {
    private Builder builder;
    private Rocket rocket = Rocket.getInstance();
    private ImpedimentsList list;

    private boolean isPaused = false;
}
