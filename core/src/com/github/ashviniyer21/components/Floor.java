package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.ashviniyer21.player.Player;

public class Floor extends GridComponent {

    public Floor(int x, int y) {
        super("floor.png", true, x, y);
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void update(Player player) {

    }

    @Override
    public TextureRegionDrawable getMakeScreenTexture() {
        return new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("floor.png"))));
    }
}
