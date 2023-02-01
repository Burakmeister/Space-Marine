package pl.space_marine.game.states;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
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
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import pl.space_marine.game.AstronomyPictureDay;
import pl.space_marine.game.NasaApi;
import pl.space_marine.game.Renderer;
import pl.space_marine.game.assets.Image;

public class MenuState extends State {
    private Texture background;
    private Table table;
    private TextButton playButton;
    private TextButton highScoreButton;

    public MenuState(GameStateManager gsm, Stage stage) {
        super(gsm, stage);
        background = Image.BACKGROUND.getTexture();
        com.badlogic.gdx.scenes.scene2d.ui.Image backgroundImage = new com.badlogic.gdx.scenes.scene2d.ui.Image(background);
        table = new Table();
        table.setFillParent(true);
        Viewport viewport = new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
        this.stage = new Stage(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4));
//        this.stage.setViewport();
        Gdx.input.setInputProcessor(this.stage);

        playButton = new TextButton("Play", this.setButton());
        highScoreButton = new TextButton("High Score", this.setButton());


//        api

        NasaApi nasaApi = new NasaApi("4gHIVhrWiUu1WvFYDdpbWn6chDf4fngjrYMp9wTC");
        Texture tex;
        Random rand = new Random();
        int year = rand.nextInt(27)+1995, month = rand.nextInt(12)+1, day = rand.nextInt(28);
        try {
            Date randomDate = new Date(year,month,day);
            AstronomyPictureDay apd = nasaApi.getAstronomyPictureOfTheDay(String.valueOf(year) + '-' + String.valueOf(month) + '-' + String.valueOf(day), true, false);
            Bitmap bmp = downloadImageFromPath(apd.getUrl());
            tex = new Texture(bmp.getWidth(), bmp.getHeight(), Pixmap.Format.RGBA8888);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTextureObjectHandle());
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
            bmp.recycle();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        com.badlogic.gdx.scenes.scene2d.ui.Image image = new com.badlogic.gdx.scenes.scene2d.ui.Image(tex);

        Label label = new Label("Space Marine", this.setLabel());
        Label dateText = new Label( String.valueOf(year) + '-' + String.valueOf(month) + '-' + String.valueOf(day), this.setLabel());


//        table.setDebug(true);
        table.setPosition(0, 0);
        table.add(label).row();
        table.align(Align.center);

        table.defaults().width(100);
        table.add(playButton).pad(10);
        table.row();
        table.add(dateText);

//        table.add(highScoreButton).row();

//        table.add(image);
//        table.pack();
//        image.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        this.stage.addActor(image);
//        BitmapFont dateText = new BitmapFont();

//        dateText.setColor(Color.WHITE);
//        stage.getBatch().begin();
//        dateText.draw(stage.getBatch(), String.valueOf(year) + '-' + String.valueOf(month) + '-' + String.valueOf(day), 0, 0);
//        dateText.draw(stage.getBatch(),1);
//        stage.getBatch().end();
        this.stage.addActor(table);


        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.push(new Renderer(gsm, stage));
            }
        });
    }

    public void setInputProcessor(Stage stage) {
        Gdx.input.setInputProcessor(stage);
    }


    public void setListenerPlay() {
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                State render = gsm.getStates().lastElement();
                gsm.pop();
                State menu = gsm.getStates().lastElement();
                gsm.pop();
                gsm.getStates().push(menu);
                gsm.getStates().push(render);
            }
        });
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()) {
            State render = gsm.getStates().lastElement();
            gsm.pop();
            State menu = gsm.getStates().lastElement();
            gsm.pop();
            gsm.getStates().push(menu);
            gsm.getStates().push(render);
        }
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void update(float dt) {
//        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
    public Bitmap downloadImageFromPath(String path){
        InputStream in =null;
        Bitmap bmp=null;
        int responseCode = -1;
        try{

            URL url = new URL(path);//"http://192.xx.xx.xx/mypath/img1.jpg
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.connect();
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                //download
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();
            }
        }
        catch(Exception ex){
            Log.e("Exception",ex.toString());
        }
        return bmp;
    }
}
