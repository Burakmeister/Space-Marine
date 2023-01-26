package pl.space_marine.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public abstract class State {
    protected GameStateManager gsm;

    protected Viewport viewport;
    protected TextButton.TextButtonStyle textButtonStyle;
    protected BitmapFont font;
    protected Skin skin;
    protected TextureAtlas buttonAtlas;

    protected Label.LabelStyle labelStyle;
//    protected OrthographicCamera cam;
//    protected Vector3 coords;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        this.viewport = new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.pack"));
        textButtonStyle = new TextButton.TextButtonStyle();
    }

    public TextButton.TextButtonStyle setButton() {
        skin.addRegions(buttonAtlas);
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("buttonnormal");
        textButtonStyle.down = skin.getDrawable("buttonpressed");
        return textButtonStyle;
    }

    public Label.LabelStyle setLabel() {
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;
//        skin.add("default", labelStyle);
        return  labelStyle;
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
}