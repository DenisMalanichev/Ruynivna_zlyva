package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.*;


public class DesktopLauncher {
	public static void main (String[] arg){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1500;
		config.height = 1000;
		config.resizable = false;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;

		new LwjglApplication(new ScreenManager(), config);
	}
}
