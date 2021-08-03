package com.github.ashviniyer21.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.ashviniyer21.MazeMaker;
import com.github.ashviniyer21.Grid;

public class MenuScreen implements Screen {
    private final Stage stage;
    private final MazeMaker mazeMaker;
    private TextButton setMakeScreenButton;
    private TextButton setPlayScreenButton;

    public MenuScreen(Stage stage, MazeMaker maker) {
        this.stage = stage;
        this.mazeMaker = maker;
    }

    @Override
    public void show() {
        stage.clear();


        setPlayScreenButton = new TextButton("Play", MazeMaker.skin);
        setMakeScreenButton = new TextButton("Make", MazeMaker.skin);
        setPlayScreenButton.setPosition(25, 425);
        setMakeScreenButton.setPosition(425, 425);
        setPlayScreenButton.scaleBy(5);
        setMakeScreenButton.scaleBy(5);

        setMakeScreenButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mazeMaker.setScreen(new MakeScreen(stage, mazeMaker, new Grid(12, 12)));
            }
        });

        setPlayScreenButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mazeMaker.setScreen(new PlayScreen(stage, mazeMaker));
            }
        });

        MazeMaker.scaleLocation(setMakeScreenButton);
        MazeMaker.scaleLocation(setPlayScreenButton);

        stage.addActor(setMakeScreenButton);
        stage.addActor(setPlayScreenButton);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}