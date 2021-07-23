package com.github.ashviniyer21.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.ashviniyer21.components.GridComponent;
import com.github.ashviniyer21.components.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Player extends GridComponent {
    Map<Item, Integer> items;
    private boolean electricity;
    private Color color;
    private Color lastColor;
    private boolean won;
    public enum Color {
        Blue, Orange, None;
    }

    public Player(int x, int y){
        super("player-up.png", true, x, y);
        items = new HashMap<>();
        electricity = false;
        color = Color.None;
        lastColor = Color.Orange;
        won = false;
    }

    public void won(){
        won = true;
    }

    public boolean hasWon(){
        return won;
    }

    public void addItem(Item item){
        if(items.containsKey(item)){
            items.put(item, items.get(item) + 1);
        } else {
            items.put(item, 1);
        }
    }

    public boolean useItem(Item item){
        if(items.containsKey(item) && items.get(item) > 0){
            items.put(item, items.get(item) - 1);
            return true;
        }
        return false;
    }

    public boolean hasElectricity(){
        return electricity;
    }

    public void setElectricity(boolean electricity){
        this.electricity = electricity;
        setColor(lastColor);
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        if(electricity){
            this.color = color;
            lastColor = color;
        } else {
            this.color = Color.None;
        }
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void update(Player player) {

    }

    @Override
    public TextureRegionDrawable getMakeScreenTexture() {
        return null;
    }

    public void move(int x, int y){
        setX(getX() + x);
        setY(getY() + y);
    }

    public void setImage(char c){
        if (c == 'E') {
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("player-right.png"))));
        } else if(c == 'S'){
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("player-down.png"))));
        } else if(c == 'W'){
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("player-left.png"))));
        } else if(c == 'N'){
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("player-up.png"))));
        }

    }
}