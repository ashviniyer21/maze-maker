package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.ashviniyer21.components.items.Item;
import com.github.ashviniyer21.player.Player;

public class ItemSquare extends GridComponent{
    private boolean hasItem;
    private final Item item;
    public ItemSquare(Item item, int x, int y) {
        super(item.toString() + ".png", false, x, y);
        hasItem = true;
        this.item = item;
    }

    @Override
    public void interact(Player player) {
        if(hasItem){
            hasItem = false;
            player.addItem(item);
            walkable = true;
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("floor.png"))));
        }
    }

    @Override
    public void update(Player player) {

    }
}
