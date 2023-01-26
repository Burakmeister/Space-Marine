package pl.space_marine.game.rocket;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import pl.space_marine.game.Game;
import pl.space_marine.game.assets.Image;
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
    private float orientation = 0; //radiany, ale pomijamy pi, wiec -> (0-2) 0 - w gore     1 - w dol

    private Image image;

    private Rocket(){
        this.bullets = new ArrayList<>();
        this.stages = new ArrayList<>();
        this.image = Image.ROCKET;
    }

    public static Rocket getInstance(){
        return Rocket.rocket;
    }

    // strzelanie
    public void shot(){
        //not implemented yet
    }
    public Image getImage() {
        return image;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


}
