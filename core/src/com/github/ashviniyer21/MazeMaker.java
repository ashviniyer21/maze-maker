package com.github.ashviniyer21;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.ashviniyer21.screens.MenuScreen;


public class MazeMaker extends Game implements ApplicationListener {
	public static Skin skin;
	//stages for the screen
	private Stage stage;
	public static final int GRID_WIDTH = 32;
	@Override
	public void create() {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		setScreen(new MenuScreen(stage, this));
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getScreen().render((float) 0.2);
		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose() {

	}
}