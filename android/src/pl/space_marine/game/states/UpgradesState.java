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

    // tekstury ulepszeń
    private Texture tankTex;
    private Texture engineTex;
    private Texture boosterTex;
    private Texture avionicsTex;
    private Texture gunTex;
    // etykiety
    private Label money;
    private Label viewlable;
    // etykiety ulepszeń
    private Label engineLvlable;
    private Label engineText = new Label("Engine",this.setLabel());
    private Label tankLvlable;
    private Label tankText = new Label("Tank",this.setLabel());
    private Label boosterLvlable;
    private Label boosterText = new Label("Booster",this.setLabel());
    private Label avionicsLvlable;
    private Label avionicsText = new Label("Avionics",this.setLabel());
    private Label gunLvlable;
    private Label gunText = new Label("Gun",this.setLabel());


    // zmienne poziomy
    private int tankLv;
    private int engineLv;
    private int avionicsLv;
    private int gunLv;
    private int boosterLv;
    // pieniądze
    private int playerMoney;
    protected UpgradesState(GameStateManager gsm, Stage stage) {
        super(gsm, stage);

        makeUpgradesComponents();



    }

    public void makeUpgradesComponents() {
        table = new Table();
        table.setFillParent(true);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        table.setPosition(0,0);
//        table.setDebug(true);
        table.defaults().width((float) (viewport.getWorldWidth()/3.5));
        table.align(Align.center);

        populateTable(1,1,1,1,1,1000);
        table.pack();
        stage.addActor(table);
    }

    public void populateTable(int tankLevel, int engineLevel, int avionicsLevel, int boosterLevel, int gunLevel, int playerMoney) {
        this.boosterLv = boosterLevel;
        this.engineLv = engineLevel;
        this.gunLv = gunLevel;
        this.avionicsLv = avionicsLevel;
        this.tankLv = tankLevel;
        this.playerMoney = playerMoney;
        viewlable = new Label("Upgrades",this.setLabel());
        viewlable.setAlignment(Align.center);
//        engine row
        engineTex = Image.ENGINE.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image engineImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(engineTex);
        engineImage.setScaling(Scaling.fit);
        engineLvlable = new Label("Level "+ engineLv +".",this.setLabel());
        engineLvlable.setAlignment(Align.center);
        engineText.setAlignment(Align.center);
        upEngineBtn = new TextButton("Upgrade", this.setButton());
        upEngineBtn.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("not coded yet -- upEngineBtn");
            }
        });
//        tank row
        tankTex = Image.FUEL_TANK.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image tankImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(tankTex);
        tankImage.setScaling(Scaling.fit);
        tankLvlable = new Label("Level "+ tankLv +".",this.setLabel());
        tankLvlable.setAlignment(Align.center);
        tankText.setAlignment(Align.center);
        upTankBtn = new TextButton("Upgrade", this.setButton());
        upTankBtn.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("not coded yet -- upTankBtn");
            }
        });
//        avionics row
        avionicsTex = Image.AVIONICS.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image avionicsImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(avionicsTex);
        avionicsImage.setScaling(Scaling.fit);
        avionicsLvlable = new Label("Level "+ avionicsLv +".",this.setLabel());
        avionicsLvlable.setAlignment(Align.center);
        avionicsText.setAlignment(Align.center);
        upAvionicsBtn = new TextButton("Upgrade", this.setButton());
        upAvionicsBtn.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                System.out.println("not coded yet -- upAvionicsBtn");
            }
        });
//        gun row
        gunTex = Image.GUN.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image gunImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(gunTex);
        gunImage.setScaling(Scaling.fit);
        gunLvlable = new Label("Level "+ gunLv +".",this.setLabel());
        gunLvlable.setAlignment(Align.center);
        gunText.setAlignment(Align.center);
        upGunBtn = new TextButton("Upgrade", this.setButton());
        upGunBtn.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("not coded yet -- upGunBtn");
            }
        });
//        booster row
        boosterTex = Image.BOOSTER.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image boosterImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(boosterTex);
        boosterImage.setScaling(Scaling.fit);
        boosterLvlable = new Label("Level "+ boosterLv +".",this.setLabel());
        boosterLvlable.setAlignment(Align.center);
        boosterText.setAlignment(Align.center);
        upBoosterBtn = new TextButton("Upgrade", this.setButton());
        upBoosterBtn.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("not coded yet -- upBoosterBtn");
            }
        });
        returnButton = new TextButton("Return", this.setButton());
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                gsm.set(new MenuState(gsm, stage));
            }
        });
        nextButton = new TextButton("Next Flight", this.setButton());
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                gsm.set(new Renderer(gsm, stage));
            }
        });

        money = new Label("Money: " + playerMoney,this.setLabel());
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

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(36/255f, 41/255f, 102/255f, 1);
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
