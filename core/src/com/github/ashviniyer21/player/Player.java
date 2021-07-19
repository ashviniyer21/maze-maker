package com.github.ashviniyer21.player;


import com.github.ashviniyer21.components.GridComponent;
import com.github.ashviniyer21.components.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Player extends GridComponent {
    Map<Item, Integer> items;
    private boolean electricity;
    private Color color;
    private Color lastColor;
    public enum Color {
        Blue, Orange, None;
    }

    public Player(int x, int y){
        super("pan-cursor.png", true, x, y);
        items = new HashMap<>();
        electricity = false;
        color = Color.None;
        lastColor = Color.Orange;
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

    public void move(int x, int y){
        setX(getX() + x);
        setY(getY() + y);
    }
}