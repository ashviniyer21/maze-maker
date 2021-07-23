package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.ashviniyer21.components.items.Item;
import com.github.ashviniyer21.player.Player;

public class Door extends GridComponent{
    private boolean closed;
    public Door(int x, int y) {
        super("closed-door.png", false, x, y);
        closed = true;
    }

    @Override
    public void interact(Player player) {
        if(player.hasElectricity() && closed && player.useItem(Item.key)){
            closed = false;
            walkable = true;
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("open-door.png"))));
        }
    }

    @Override
    public void update(Player player) {

    }

    @Override
    public TextureRegionDrawable getMakeScreenTexture() {
        return new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("closed-door.png"))));
    }
}
