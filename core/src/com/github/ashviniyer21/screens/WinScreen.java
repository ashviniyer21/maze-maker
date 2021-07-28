package com.github.ashviniyer21.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.ashviniyer21.MazeMaker;

public class WinScreen implements Screen {
    private Stage stage;
    private MazeMaker mazeMaker;

    public WinScreen(Stage stage, MazeMaker maker) {
        this.stage = stage;
        this.mazeMaker = maker;
    }

    @Override
    public void show() {
        stage.clear();
        TextButton winner = new TextButton("Winner", MazeMaker.skin);
        winner.setPosition(425, 325);
        stage.addActor(winner);

        TextButton menuButton = new TextButton("Go Back", MazeMaker.skin);
        menuButton.setPosition(50, 650);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mazeMaker.setScreen(new MenuScreen(stage, mazeMaker));
            }
        });
        stage.addActor(menuButton);
        MazeMaker.scaleStage(stage);
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
