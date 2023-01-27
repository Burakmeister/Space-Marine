package pl.space_marine.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import pl.space_marine.game.Renderer;
import pl.space_marine.game.assets.Image;
import pl.space_marine.game.rocket.Rocket;

public class UpgradesState extends State {
    private Table table;

    private TextButton returnButton;
    private TextButton nextButton;
    // przyciski ulepszeń
    private TextButton upEngineBtn;
    private TextButton upTankBtn;
    private TextButton upGunBtn;
    private TextButton upAvionicsBtn;
    private TextButton upBoosterBtn;

    private final Label engineText = new Label("Engine",this.setLabel());
    private final Label tankText = new Label("Tank",this.setLabel());
    private final Label boosterText = new Label("Booster",this.setLabel());
    private final Label avionicsText = new Label("Avionics",this.setLabel());
    private final Label gunText = new Label("Gun",this.setLabel());

    private final Rocket rocket;
    public UpgradesState(GameStateManager gsm, Stage stage, Rocket rocket) {
        super(gsm, stage);
        this.rocket = rocket;
        makeUpgradesComponents();
    }

    public void makeUpgradesComponents() {

        table = new Table();
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);

        table.setPosition(0,0);
        table.setDebug(true);

        populateTable(rocket.getAccountBalance());
        addButtonsListeners();
        table.pack();
        stage.addActor(table);
    }

    public void populateTable( int playerMoney) {
        Label viewlable = new Label("Upgrades", this.setLabel());
        viewlable.setAlignment(Align.center);
//        engine row
        Texture engineTex = Image.ENGINE.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image engineImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(engineTex);
        engineImage.setScaling(Scaling.fit);
        // etykiety ulepszeń
        Label engineLvlable = new Label("Level " + rocket.getStages().get(0).getLevel() + ".", this.setLabel());
        engineLvlable.setAlignment(Align.center);
        engineText.setAlignment(Align.center);
        upEngineBtn = new TextButton("Upgrade", this.setButton());

//        tank row
        // tekstury ulepszeń
        Texture tankTex = Image.FUEL_TANK.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image tankImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(tankTex);
        tankImage.setScaling(Scaling.fit);
        Label tankLvlable = new Label("Level " + rocket.getStages().get(3).getLevel() + ".", this.setLabel());
        tankLvlable.setAlignment(Align.center);
        tankText.setAlignment(Align.center);
        upTankBtn = new TextButton("Upgrade", this.setButton());

//        avionics row
        Texture avionicsTex = Image.AVIONICS.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image avionicsImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(avionicsTex);
        avionicsImage.setScaling(Scaling.fit);
        Label avionicsLvlable = new Label("Level " + rocket.getStages().get(1).getLevel() + ".", this.setLabel());
        avionicsLvlable.setAlignment(Align.center);
        avionicsText.setAlignment(Align.center);
        upAvionicsBtn = new TextButton("Upgrade", this.setButton());

//        gun row
        Texture gunTex = Image.GUN.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image gunImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(gunTex);
        gunImage.setScaling(Scaling.fit);
        Label gunLvlable = new Label("Level " + rocket.getStages().get(4).getLevel() + ".", this.setLabel());
        gunLvlable.setAlignment(Align.center);
        gunText.setAlignment(Align.center);
        upGunBtn = new TextButton("Upgrade", this.setButton());

//        booster row
        Texture boosterTex = Image.BOOSTER.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image boosterImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(boosterTex);
        boosterImage.setScaling(Scaling.fit);
        Label boosterLvlable = new Label("Level " + rocket.getStages().get(2).getLevel() + ".", this.setLabel());
        boosterLvlable.setAlignment(Align.center);
        boosterText.setAlignment(Align.center);
        upBoosterBtn = new TextButton("Upgrade", this.setButton());

        returnButton = new TextButton("Return", this.setButton());

        nextButton = new TextButton("Next Flight", this.setButton());


        // etykiety
        Label money = new Label("Money: " + playerMoney, this.setLabel());
        money.setAlignment(Align.center);

        table.add(viewlable).colspan(3).center().row();
        table.row();
        table.add(engineText).colspan(3).row();
        table.add(engineImage).width(50).height(50).pad(0,0,5,0);
        table.add(engineLvlable);
        table.add(upEngineBtn).row();
        table.add(tankText).colspan(3).row();
        table.add(tankImage).width(50).height(50).pad(0,0,5,0);
        table.add(tankLvlable);
        table.add(upTankBtn).row();
        table.add(avionicsText).colspan(3).row();
        table.add(avionicsImage).width(50).height(50).pad(0,0,5,0);
        table.add(avionicsLvlable);
        table.add(upAvionicsBtn).row();
        table.add(gunText).colspan(3).row();
        table.add(gunImage).width(50).height(50).pad(0,0,5,0);
        table.add(gunLvlable);
        table.add(upGunBtn).row();
        table.add(boosterText).colspan(3).row();
        table.add(boosterImage).width(50).height(50).pad(0,0,5,0);
        table.add(boosterLvlable);
        table.add(upBoosterBtn).row();
        table.add(returnButton);
        table.add(nextButton);
        table.add(money);
    }

    public void revalidateTable() {
        table.clear();
        populateTable(rocket.getAccountBalance());
        addButtonsListeners();
        table.invalidate();
    }

    public void addButtonsListeners() {
        upEngineBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rocket.getStages().get(0).incrementLevel();
                System.out.println("ulepszono" + rocket.getStages().get(0).toString());
                revalidateTable();
            }
        });

        upTankBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rocket.getStages().get(3).incrementLevel();
                System.out.println("ulepszono" + rocket.getStages().get(3).toString());
                revalidateTable();
            }
        });

        upAvionicsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rocket.getStages().get(1).incrementLevel();
                System.out.println("ulepszono" + rocket.getStages().get(1).toString());
                revalidateTable();
            }
        });

        upGunBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rocket.getStages().get(4).incrementLevel();
                System.out.println("ulepszono" + rocket.getStages().get(4).toString());
                revalidateTable();
            }
        });

        upBoosterBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rocket.getStages().get(2).incrementLevel();
                System.out.println("ulepszono" + rocket.getStages().get(2).toString());
                revalidateTable();
            }
        });

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                stage.clear();
                gsm.set(new MenuState(gsm, stage));
            }
        });

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed1");
                stage.clear();
                gsm.set(new Renderer(gsm, stage));
            }
        });
    }

    @Override
    public void handleInput() {
//      bo tak
    }

    @Override
    public void update(float dt) {
//      bo tak
    }

    @Override
    public void render(SpriteBatch sb) {
//        Gdx.gl.glClearColor(36/255f, 41/255f, 102/255f, 1);
        stage.draw();
    }

    @Override
    public void dispose() {
//      bo tak
    }
}
