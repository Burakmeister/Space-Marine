package pl.space_marine.game.rocket.stages;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.space_marine.game.assets.Image;
import pl.space_marine.game.rocket.Rocket;

public class RocketBuilder {
    private Rocket rocket;
//    private List<Sprite> sprites;
    private HashMap<Stage, List<Sprite>> sprites;

    public RocketBuilder(Rocket rocket) {
        this.rocket = rocket;
        System.out.println(rocket);
        this.sprites = new HashMap<>();
        Sprite rocketSprite = new Sprite(Image.ROCKET.getTexture());
        rocketSprite.setOrigin(rocket.getImage().getTexture().getWidth()/2,rocket.getImage().getTexture().getHeight()/2);
        rocketSprite.setX(rocket.getX());
        rocketSprite.setY(rocket.getY());
        sprites.put(rocket.getStages()[5],new ArrayList<Sprite>());

        sprites.put(rocket.getStages()[2], new ArrayList<Sprite>());
    }

    public void addStages() {
        addBooster(rocket.getStages()[2]);
    }

    public void addBooster(Stage stage) {
        if (stage.getLevel() == 1 &&  sprites.get(rocket.getStages()[2]).size() == 0) {
            Sprite booster = new Sprite(Image.BOOSTER_STAGE.getTexture());
            booster.setOrigin(rocket.getImage().getTexture().getWidth()/2,rocket.getImage().getTexture().getHeight()/2);
            booster.setX(rocket.getX());
            booster.setY(rocket.getY());
            sprites.get(rocket.getStages()[2]).add(booster);
        } else if (stage.getLevel() == 2 && sprites.get(rocket.getStages()[2]).size() == 1) {
            Sprite booster = new Sprite(Image.BOOSTER_STAGE.getTexture());
            booster.setOrigin(rocket.getImage().getTexture().getWidth()/2,rocket.getImage().getTexture().getHeight()/2);
            booster.setX(rocket.getX()-rocket.getImage().getTexture().getWidth()/2);
            booster.setY(rocket.getY());
            sprites.get(rocket.getStages()[2]).add(booster);
        }  else if (stage.getLevel() == 3 && sprites.get(rocket.getStages()[2]).size() == 2) {
            Sprite booster = new Sprite(Image.BOOSTER_STAGE.getTexture());
            booster.setOrigin(rocket.getImage().getTexture().getWidth()/2,rocket.getImage().getTexture().getHeight()/2);
            booster.setX(rocket.getX());
            booster.setY(rocket.getY());
            sprites.get(rocket.getStages()[2]).add(booster);
        }
    }

    public List<Sprite> getSprites() {
        List<Sprite> ret = new ArrayList<>();
        for(Stage stg: rocket.getStages()){
            List<Sprite> tmp = sprites.get(stg);
            if (tmp != null) {
                ret.addAll(tmp);
            }
        }
        return ret;
    }

    public void update() {
//        for (Sprite sprite: sprites) {
//            sprite.setX(rocket.getX()+rocket.getImage().getTexture().getWidth()/2);
//            sprite.setY(rocket.getY());
//            if(sprite instanceof Booster){
//                Booster booster = (Booster) sprite;
//                if()
//            }
        for(Stage stage: rocket.getStages()){
            List<Sprite> tmp = sprites.get(stage);
            if (tmp != null) {
                int length = tmp.size();
                if(stage instanceof Booster){
                    updateBoosters(tmp);
                }else if(stage instanceof Engine){
                    updateEngines(tmp);
                }

            }

        }
    }

    public void updateBoosters( List<Sprite> sprites) {
        if (sprites.size() == 1) {
            sprites.get(0).setX(rocket.getX());
            sprites.get(0).setY(rocket.getY());
        } else if (sprites.size() == 2) {
            sprites.get(0).setX(rocket.getX()+rocket.getImage().getTexture().getWidth()/2);
            sprites.get(0).setY(rocket.getY());
            sprites.get(1).setX(rocket.getX()-rocket.getImage().getTexture().getWidth()/2);
            sprites.get(1).setY(rocket.getY());
        } else if (sprites.size() == 3) {
            sprites.get(sprites.size()-2).setX(rocket.getX()+rocket.getImage().getTexture().getWidth()/2);
            sprites.get(sprites.size()-2).setY(rocket.getY());
            sprites.get(sprites.size()-3).setX(rocket.getX()-rocket.getImage().getTexture().getWidth()/2);
            sprites.get(sprites.size()-3).setY(rocket.getY());
            sprites.get(sprites.size()-1).setX(rocket.getX());
            sprites.get(sprites.size()-1).setY(rocket.getY());
        }
    }

    public void updateEngines( List<Sprite> sprites) {

    }
}
