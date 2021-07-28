package com.github.ashviniyer21.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.ashviniyer21.MazeMaker;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		int width = (int)size.getWidth();
		int height = (int)size.getHeight();
		MazeMaker.SCALE = Math.min(width / 900, height / 700);
		config.width = 900 * MazeMaker.SCALE;
		config.height = 700 * MazeMaker.SCALE;
		new LwjglApplication(new MazeMaker(), config);
	}
}
