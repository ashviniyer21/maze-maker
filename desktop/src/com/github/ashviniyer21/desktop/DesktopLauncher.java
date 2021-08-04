package com.github.ashviniyer21.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.ashviniyer21.MazeMaker;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		int width = (int)size.getWidth() - 200;
		int height = (int)size.getHeight() - 200;
		MazeMaker.SCALE = Math.min((double) width / 900, (double) height / 700);
		config.width = (int) (900 * MazeMaker.SCALE);
		config.height = (int) (700 * MazeMaker.SCALE);
		System.out.print(MazeMaker.SCALE);
		new LwjglApplication(new MazeMaker(), config);
	}
}
