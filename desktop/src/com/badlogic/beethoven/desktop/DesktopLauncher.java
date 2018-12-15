package com.badlogic.beethoven.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.beethoven.Play;
import com.badlogic.beethoven.RhyGame;

public class DesktopLauncher {
	   public static void main (String[] arg) {
		      LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		      config.title = "Drop";
		      config.width = RhyGame.WIDTH;
		      config.height = RhyGame.HEIGHT;
		      config.resizable = true;
		      new LwjglApplication(new RhyGame(), config);
		      }
}
