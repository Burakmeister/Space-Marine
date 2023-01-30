package pl.space_marine.game.rocket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.bullets.Bullet;
import pl.space_marine.game.listener.DefaulRocketShotListener;
import pl.space_marine.game.listener.Listener;
import pl.space_marine.game.rocket.stages.Avionic;
import pl.space_marine.game.rocket.stages.Booster;
import pl.space_marine.game.rocket.stages.Engine;
import pl.space_marine.game.rocket.stages.Gun;
import pl.space_marine.game.rocket.stages.Stage;
import pl.space_marine.game.rocket.stages.Tank;

public class Rocket {
    // instancja rakiety
    private static final Rocket rocket = new Rocket();

    // listy: pociski i człony
    private List<Bullet> bullets;
    private Stage[] stages;

    // dane użytkownika: stan konta i rekord
    private int accountBalance = 0;
    private int record;

    // dane rakiet: pozycja i ?armor?
    private int armor = 0;
    private int x;
    private int y;
    private float orientation = 0; //radiany, ale pomijamy pi, wiec -> (0-2) 0 - w gore     1 - w dol

    private Image image;
    private Set<Listener> listeners;

    private Rocket(){
        this.bullets = new ArrayList<>();
        this.stages = new Stage[]{
                new Engine(),
                new Avionic(),
                new Tank(),
                new Gun(),
                new Booster()
        };
        this.image = Image.ROCKET;
        for (int i = 0; i < 4; i++) {
            this.stages[i].incrementLevel();
        }
        this.setAccountBalance(1000000);

        this.listeners = new HashSet<>();
    }

    public static Rocket getInstance(){
        return Rocket.rocket;
    }

    // strzelanie
    public void shot(){
        System.out.println("peeew peeew");
        notifyListeners();
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


    public float getOrientation() {
        return orientation;
    }

    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void buyUpgrade(int upgradeCost) {
        this.accountBalance -= upgradeCost;
    }

    public void incrementArmor() {
        this.armor++;
    }

    public Stage[] getStages() {
        return stages;
    }

    public int getArmor() {
        return armor;
    }

    public void updateImage(int level) {
        switch (level) {
            case 1:
                this.image = Image.ROCKET1;
                break;
            case 2:
                this.image = Image.ROCKET2;
                break;
            case  3:
                this.image = Image.ROCKET3;
        }
    }

    public void attach(DefaulRocketShotListener listener)
    {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void detach(DefaulRocketShotListener listener)
    {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    public void notifyListeners(){
        for(Listener o: listeners) {
            o.update(rocket.getX(), rocket.getY());
        }
    }
}