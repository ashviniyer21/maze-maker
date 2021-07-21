package com.github.ashviniyer21.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.github.ashviniyer21.Grid;
import com.github.ashviniyer21.MazeMaker;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayScreen implements Screen {

    private Stage stage;
    private MazeMaker mazeMaker;
    private Grid grid;
    private String levelText;
    private char direction;
    public PlayScreen(Stage stage, MazeMaker maker) {
        this.stage = stage;
        this.mazeMaker = maker;
        direction = 'N';
    }

    @Override
    public void show() {
        drawUI();
    }

    private void drawUI(){
        stage.clear();
        if(grid != null){
            grid.addToPlayScreen(stage);
        }
        TextButton selectButton = new TextButton("Select", MazeMaker.skin);
        selectButton.setPosition(550, 650);
        selectButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final JFileChooser chooser = new JFileChooser();
                        final JFrame f = new JFrame();
                        f.setVisible(true);
                        f.toFront();
                        f.setVisible(false);
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Maze File", "mzmk");
                        chooser.setAcceptAllFileFilterUsed(false);
                        chooser.setFileFilter(filter);
                        chooser.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                FileHandle handle = Gdx.files.absolute(chooser.getSelectedFile().getAbsolutePath());
                                levelText = handle.readString();
                            }
                        });
                        chooser.showDialog(f, "Save");
                        f.dispose();
                    }
                }).start();

            }
        });
        TextButton loadButton = new TextButton("Load", MazeMaker.skin);
        loadButton.setPosition(625, 650);
        loadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(levelText);
                grid = Grid.convertToGrid(levelText);
                grid.addPlayer();
                drawUI();
            }
        });
        stage.addActor(selectButton);
        stage.addActor(loadButton);
    }

    @Override
    public void render(float delta) {
        if(grid != null && grid.getPlayer() != null){
            if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
                direction = 'E';
                grid.getPlayer().setImage(direction);
                if(grid.isWalkable(-1, 0)){
                    grid.getPlayer().move(-1, 0);
                    drawUI();
                }
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
                direction = 'W';
                grid.getPlayer().setImage(direction);
                if(grid.isWalkable(1, 0)){
                    grid.getPlayer().move(1, 0);
                    drawUI();
                }
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
                direction = 'N';
                grid.getPlayer().setImage(direction);
                if(grid.isWalkable(0, 1)){
                    grid.getPlayer().move(0, 1);
                    drawUI();
                }
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                direction = 'S';
                grid.getPlayer().setImage(direction);
                if(grid.isWalkable(0, -1)){
                    grid.getPlayer().move(0, -1);
                    drawUI();
                }
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                grid.interact(direction);
                grid.update();
            }
        }
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