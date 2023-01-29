package pl.space_marine.game.rocket.stages;

import pl.space_marine.game.assets.Image;

public abstract class Stage {
    protected Image texture;    // textura
    protected short level;      // liczba sztuk danego stage'a

    public Stage(){
    }

    public short getLevel() {
        return level;
    }

    public void incrementLevel() {
        this.level += 1;
    }
}