package com.github.ashviniyer21.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.ashviniyer21.player.Player;

public class Wall extends GridComponent {

    public Wall(int x, int y) {
        super("wall.png", false, x, y);
    }

    @Override
    public boolean interact(Player player) {
        return false;
    }

    @Override
    public void update(Player player) {

    }

    @Override
    public TextureRegionDrawable getMakeScreenTexture() {
        return new TextureRegionDrawable(new TextureRegion(new Texture("wall.png")));
    }
}
