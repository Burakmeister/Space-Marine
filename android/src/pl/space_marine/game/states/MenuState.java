package pl.space_marine.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.space_marine.game.assets.Image;

public class MenuState extends State {
    private Texture background;
//    private TextureRegion mainBackground;
    private Stage stage;
    private Table table;
    private TextButton playButton;
    private TextButton highScoreButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
//        Viewport viewport = new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
        background = Image.BACKGROUND.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image backgroundImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(background);
        table = new Table();
        table.setFillParent(true);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        playButton = new TextButton("Play", this.setButton());
        highScoreButton = new TextButton("High Score", this.setButton());

        table.setDebug(true);
        table.setPosition(0,0);
        table.align(Align.center);

        table.defaults().width(100);
        table.add(playButton).pad(10);
        table.row();
        table.add(highScoreButton);
        table.pack();
        backgroundImage.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);


        playButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                gsm.set(new PlayState(gsm));
            }
        });
    }



    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()) {
            gsm.set(new PlayState(gsm));
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(float dt) {
//        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
//        sb.begin();
//        sb.draw(mainBackground, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
