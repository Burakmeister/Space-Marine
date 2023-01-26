package pl.space_marine.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import pl.space_marine.game.states.MenuState;

public class AndroidLauncher extends AndroidApplication {
//	public final static int SCREEN_WIDTH;
//	public final static int SCREEN_HEIGHT;
//
//	public AndroidLauncher(){
//		DisplayMetrics displayMetrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//		SCREEN_HEIGHT = displayMetrics.heightPixels;
//		SCREEN_WIDTH = displayMetrics.widthPixels;
//	}
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		config.useCompass = false;
		config.useGyroscope = false;
		initialize(new App(), config);
	}
}
