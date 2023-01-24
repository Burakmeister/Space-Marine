package pl.space_marine.game.rocket;

import java.util.ArrayList;
import java.util.List;

import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.rocket.stages.Stage;

public class Rocket {
    // instancja rakiety
    private static final Rocket rocket = new Rocket();

    // listy: pociski i człony
    private List<Bullet> bullets;
    private List<Stage> stages;

    // dane użytkownika: stan konta i rekord
    private int accountBalance;
    private int record;

    // dane rakiet: pozycja i ?armor?
    private int armor;
    private int x;
    private int y;

    private Rocket(){
        this.bullets = new ArrayList<>();
        this.stages = new ArrayList<>();
    }

    public static Rocket getInstance(){
        return Rocket.rocket;
    }

    // strzelanie
    public void shot(){
        //not implemented yet
    }
}
