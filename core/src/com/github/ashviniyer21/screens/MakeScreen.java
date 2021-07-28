package com.github.ashviniyer21.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.github.ashviniyer21.MazeMaker;
import com.github.ashviniyer21.Grid;
import com.github.ashviniyer21.components.*;
import com.github.ashviniyer21.components.items.Item;
import com.github.ashviniyer21.player.Player;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MakeScreen implements Screen {
    private final Stage stage;
    private MazeMaker mazeMaker;
    private Grid grid;
    private GridComponent selectedComponent;
    private boolean pan;

    public MakeScreen(Stage stage, MazeMaker maker, final Grid grid) {
        this.stage = stage;
        this.mazeMaker = maker;
        this.grid = grid;
        pan = false;
    }

    @Override
    public void show() {
        drawUI();
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

    public void drawUI(){
        stage.clear();
        grid.addToMakeScreen(stage, this);
        Image cover = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("cover.png")))));
        cover.setWidth(900);
        cover.setHeight(150);
        cover.setPosition(0, 550);
        stage.addActor(cover);
        TextButton menuButton = new TextButton("Go Back", MazeMaker.skin);
        menuButton.setPosition(50, 600);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mazeMaker.setScreen(new MenuScreen(stage, mazeMaker));
            }
        });
        stage.addActor(menuButton);
        final TextField widthInput = new TextField("w", MazeMaker.skin);
        widthInput.setPosition(75, 650);
        widthInput.setWidth(30);
        stage.addActor(widthInput);

        final TextField heightInput = new TextField("h", MazeMaker.skin);
        heightInput.setPosition(125, 650);
        heightInput.setWidth(30);
        stage.addActor(heightInput);

        TextButton resizeGrid = new TextButton("Resize", MazeMaker.skin);
        resizeGrid.setPosition(175, 650);
        resizeGrid.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    int x = Integer.parseInt(widthInput.getText());
                    int y = Integer.parseInt(heightInput.getText());
                    grid = new Grid(y, x, grid);
                    drawUI();
                } catch (NumberFormatException e) {
                    System.out.println("Must have integer values");
                }
            }
        });
        stage.addActor(resizeGrid);

        ImageButton floorButton = makeButton("floor.png", 250, 615, new Floor(0, 0));
        stage.addActor(floorButton);

        ImageButton keyButton = makeButton("key.png", 300, 615, new ItemSquare(Item.key, 0, 0));
        stage.addActor(keyButton);

        ImageButton doorButton = makeButton("closed-door.png", 350, 615, new Door(0, 0));
        stage.addActor(doorButton);

        ImageButton fenceButton = makeButton("electric-fence-enabled.png", 400, 615, new ElectricFence(0, 0));
        stage.addActor(fenceButton);

        ImageButton powerButton = makeButton("power-on.png", 450, 615, new PowerSwitch(0, 0));
        stage.addActor(powerButton);

        ImageButton switchButton = makeButton("switch-orange.png", 500, 615, new Switch(0, 0));
        stage.addActor(switchButton);

        ImageButton temporaryOrangeFloorButton = makeButton("orange-floor.png", 550, 615, new TemporaryFloor(Player.Color.Orange, 0, 0));
        stage.addActor(temporaryOrangeFloorButton);

        ImageButton temporaryBlueFloorButton = makeButton("blue-floor.png", 600, 615, new TemporaryFloor(Player.Color.Blue, 0, 0));
        stage.addActor(temporaryBlueFloorButton);

        ImageButton wallButton = makeButton("wall.png", 650, 615, new Wall(0, 0));
        stage.addActor(wallButton);

        ImageButton startButton = makeButton("start.png", 700, 615, new StartSpace(0, 0));
        stage.addActor(startButton);

        ImageButton endButton = makeButton("end.png", 750, 615, new EndSpace(0, 0));
        stage.addActor(endButton);

        ImageButton panButton = makeButton("pan-cursor.png", 800, 615, null, true);
        stage.addActor(panButton);

        TextButton saveGrid = new TextButton("Save", MazeMaker.skin);
        saveGrid.setPosition(550, 650);
        saveGrid.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final Json json = new Json();
                final String[][] check = grid.convertToJson();
                int hasStart = 0;
                boolean hasEnd = false;
                for (String[] strings : check) {
                    for (String string : strings) {
                        if (string.equals("StartSpace")) {
                            hasStart++;
                        } else if (string.equals("EndSpace")) {
                            hasEnd = true;
                        }
                    }
                }
                if(hasStart == 1 && hasEnd){
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
                            chooser.setSelectedFile(new File("defaultsave.mzmk"));
                            chooser.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(e.getActionCommand().equals("ApproveSelection")){
                                        FileHandle handle = Gdx.files.absolute(chooser.getSelectedFile().getAbsolutePath());
                                        handle.writeString(json.prettyPrint(check), false);
                                    }
                                }
                            });
                            chooser.showSaveDialog(f);
                            f.dispose();
                        }
                    }).start();
                } else {
                    System.out.println("Needs Start and End Space");
                }
            }
        });
        stage.addActor(saveGrid);
        MazeMaker.scaleStage(stage);
    }

    public GridComponent getSelectedComponent(){
        return selectedComponent;
    }

    public void resetSelectedComponent(){
        selectedComponent = null;
    }

    public boolean getPanSelected(){
        return pan;
    }

    private ImageButton makeButton(final String path, int x, int y, final GridComponent component){
        return makeButton(path, x, y, component, false);
    }

    private ImageButton makeButton(final String path, int x, int y, final GridComponent component, final boolean val){
        ImageButton button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(path)))));
        button.setPosition(x, y);
        button.setWidth(MazeMaker.GRID_WIDTH);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pan = val;
                Pixmap pixmap = new Pixmap(Gdx.files.internal(path));
                int xHotspot = pixmap.getWidth() / 2;
                int yHotspot = pixmap.getHeight() / 2;
                Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
                Gdx.graphics.setCursor(cursor);
                selectedComponent = component;
                pixmap.dispose();
            }
        });
        return button;
    }
}