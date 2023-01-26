package pl.space_marine.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayState extends State{
    private Stage stage;
    private Table table;
    private TextButton returnButton;
    private TextButton nextButton;

    private Texture rocket;

    private Image image;

    private  int rotation = 0;

    public PlayState(GameStateManager gsm) {
        super(gsm);
//        Viewport viewport = new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);

        rocket = new Texture("Space Shuttle.png");
        image = new Image(rocket);
        table = new Table();
        table.setFillParent(true);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        returnButton = new TextButton("Return", this.setButton());

        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                gsm.set(new MenuState(gsm));
            }
        });
        nextButton = new TextButton("Next", this.setButton());

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                gsm.set(new UpgradesState(gsm));
            }
        });
        table.setPosition(0,0);
        table.setDebug(true);

        table.align(Align.center);
        table.left().top();
        table.add(returnButton);
        table.add(nextButton);
        table.pack();

        stage.addActor(image);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2 );
        image.moveBy(viewport.getWorldWidth()/2-image.getWidth()/2,viewport.getWorldHeight()/2-image.getHeight()/2);
        stage.addActor(table);
    }

    @Override
    public void handleInput() {

        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
            // Get the current accelerometer values
            float x = Gdx.input.getAccelerometerX();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if ((int)x > 0 && rotation < 20) {
                rotation += 5;
                image.rotateBy(rotation);
            } else if ((int)x < 0 && rotation > -20) {
                rotation -= 5;
                image.rotateBy(rotation);
            }
//            if (rotation != 0) {
//                image.rotateBy(rotation);
////                rotation = 0;
//            }
//            System.out.println(x+"\n");
//            System.out.println(y+"\n");
//            System.out.println(z+"\n");
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
//        image.rotateBy(rotation);
    }

    @Override
    public void render(SpriteBatch sb) {
//        sb.begin();
//        sb.draw(rocket,viewport.getWorldWidth(),viewport.getWorldHeight());
//        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
