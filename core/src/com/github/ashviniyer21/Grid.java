package com.github.ashviniyer21;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.github.ashviniyer21.components.*;
import com.github.ashviniyer21.components.items.Item;
import com.github.ashviniyer21.player.Player;
import com.github.ashviniyer21.screens.MakeScreen;

public class Grid {
    GridComponent[][] grid;
    private final float[] offset;
    private float tempX;
    private float tempY;
    private Player player;
    public Grid(int width, int height, GridComponent... components){
        grid = new GridComponent[width][height];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                addComponent(new Floor(j, i));
            }
        }
        for (GridComponent component : components) {
            addComponent(component);
        }
        offset = new float[]{0, 0};
        tempX = 0;
        tempY = 0;
    }

    public Grid(int width, int height, Grid grid){
        this(width, height);
        for(int i = 0; i < Math.min(grid.grid.length, this.grid.length); i++){
            for(int j = 0; j < Math.min(grid.grid[i].length, this.grid[i].length); j++){
                addComponent(grid.getComponent(j, i));
            }
        }
    }

    public void addPlayer(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] instanceof StartSpace){
                    player = new Player(j, i);
                    return;
                }
            }
        }
    }


    public GridComponent getComponent(int x, int y){
        return grid[y][x];
    }

    private void addComponent(GridComponent component){
        grid[component.getY()][component.getX()] = component;
    }

    public void addToMakeScreen(Stage stage, MakeScreen screen){
        //TODO draw grid lines
        for (GridComponent[] gridComponents : grid) {
            for (GridComponent gridComponent : gridComponents) {
                stage.addActor(makeImageButton(gridComponent, screen));
            }
        }
    }

    public void addToPlayScreen(Stage stage){
        if(player != null){
            if(MazeMaker.GRID_WIDTH * (player.getX() + 1) + offset[0] > 900-MazeMaker.GRID_WIDTH){
                offset[0] -= MazeMaker.GRID_WIDTH;
            } else if(MazeMaker.GRID_WIDTH * (player.getX() + 1) + offset[0] < MazeMaker.GRID_WIDTH){
                offset[0] += MazeMaker.GRID_WIDTH;
            } else if(MazeMaker.GRID_WIDTH * (player.getY() + 1) + offset[1] > 700-MazeMaker.GRID_WIDTH){
                offset[1] -= MazeMaker.GRID_WIDTH;
            } else if(MazeMaker.GRID_WIDTH * (player.getY() + 1) + offset[1] < MazeMaker.GRID_WIDTH){
                offset[1] += MazeMaker.GRID_WIDTH;
            }
        }
        for(GridComponent[] gridComponents: grid){
            for(GridComponent gridComponent: gridComponents){
                stage.addActor(makeImage(gridComponent));
            }
        }
        if(player != null){
            stage.addActor(makeImage(player));
        }
    }

    private Image makeImage(final GridComponent component){
        Image image = new Image(component);
        image.setWidth(MazeMaker.GRID_WIDTH);
        image.setHeight(MazeMaker.GRID_WIDTH);
        image.setPosition((MazeMaker.GRID_WIDTH)*(component.getX()+1) + offset[0], (MazeMaker.GRID_WIDTH)*(component.getY()+1) + offset[1]);
        MazeMaker.resizeImage(image);
        return image;
    }

    private ImageButton makeImageButton(final GridComponent component, final MakeScreen screen){
        final ImageButton image = new ImageButton(component.getMakeScreenTexture());
        image.setWidth(MazeMaker.GRID_WIDTH);
        image.setHeight(MazeMaker.GRID_WIDTH);
        image.setPosition((MazeMaker.GRID_WIDTH)*(component.getX()+1) + offset[0], (MazeMaker.GRID_WIDTH)*(component.getY()+1) + offset[1]);
        image.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(screen.getPanSelected()){
                    offset[0] += x - tempX;
                    offset[1] += y - tempY;
                    screen.drawUI();
                }
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(screen.getPanSelected()){
                    tempX = x;
                    tempY = y;
                } else if(screen.getSelectedComponent() != null){
                    Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor.png"));
                    Cursor cursor = Gdx.graphics.newCursor(pixmap, 0, 0);
                    Gdx.graphics.setCursor(cursor);
                    pixmap.dispose();
                    screen.getSelectedComponent().setX(component.getX());
                    screen.getSelectedComponent().setY(component.getY());
                    addComponent(screen.getSelectedComponent());
                    screen.resetSelectedComponent();
                    screen.drawUI();
                }
                return true;
            }
        });
        MazeMaker.resizeImage(image.getImage());
        return image;
    }

    public String[][] convertToJson(){
        String[][] temp = new String[grid.length][grid[0].length];
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < temp[i].length; j++){
                if(grid[i][j] instanceof TemporaryFloor){
                    if(((TemporaryFloor) grid[i][j]).getColor() == Player.Color.Orange){
                        temp[i][j] = "Orange";
                    } else {
                        temp[i][j] = "Blue";
                    }
                } else {
                    temp[i][j] = grid[i][j].getClass().getSimpleName();
                }
            }
        }
        return temp;
    }

    public static Grid convertToGrid(String s){
        JsonReader reader = new JsonReader();
        JsonValue value = reader.parse(s);
        int height = value.get(0).size;
        int width = value.size;
        System.out.println(width);
        System.out.println(height);
        Grid grid = new Grid(width, height);

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                GridComponent component;
                switch (value.get(j).get(i).toString()){
                    case "Door":
                        component = new Door(i, j);
                        break;
                    case "ItemSquare":
                        component = new ItemSquare(Item.key, i, j);
                        break;
                    case "ElectricFence":
                        component = new ElectricFence(i, j);
                        break;
                    case "PowerSwitch":
                        component = new PowerSwitch(i, j);
                        break;
                    case "Switch":
                        component = new Switch(i, j);
                        break;
                    case "Orange":
                        component = new TemporaryFloor(Player.Color.Orange, i, j);
                        break;
                    case "Blue":
                        component = new TemporaryFloor(Player.Color.Blue, i, j);
                        break;
                    case "Wall":
                        component = new Wall(i, j);
                        break;
                    case "StartSpace":
                        component = new StartSpace(i, j);
                        break;
                    case "EndSpace":
                        component = new EndSpace(i, j);
                        break;
                    default:
                        component = new Floor(i, j);
                        break;
                }
                grid.addComponent(component);
            }
        }
        return grid;
    }

    public Player getPlayer(){
        return player;
    }

    public boolean isWalkable(int x, int y){
        if(player.getX() + x >= grid[0].length || player.getX() + x < 0){
            return false;
        }
        if(player.getY() + y >= grid.length || player.getY() + y < 0){
            return false;
        }
        return getComponent(player.getX() + x, player.getY() + y).isWalkable();
    }

    public void interact(char direction){
        if(direction == 'N'){
            if(player.getY() != grid.length - 1){
                getComponent(player.getX(), player.getY() + 1).interact(player);
            }
        } else if(direction == 'S'){
            if(player.getY() != 0){
                getComponent(player.getX(), player.getY() - 1).interact(player);
            }
        } else if(direction == 'E'){
            if(player.getX() != 0){
                getComponent(player.getX() - 1, player.getY()).interact(player);
            }
        } else if(direction == 'W'){
            if(player.getX() != grid[0].length-1){
                getComponent(player.getX() + 1, player.getY()).interact(player);
            }
        }
    }

    public void update(){
        for (GridComponent[] gridComponents : grid) {
            for (GridComponent gridComponent : gridComponents) {
                gridComponent.update(player);
            }
        }
    }
}