package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.ashviniyer21.player.Player;

public class PowerSwitch extends GridComponent {
    public PowerSwitch(int x, int y) {
        super("power-off.png", false, x, y);
    }

    @Override
    public void interact(Player player) {
        player.setElectricity(!player.hasElectricity());
    }

    @Override
    public void update(Player player) {
        if(player.hasElectricity()){
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("power-on.png"))));
        } else {
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("power-off.png"))));
        }
    }

    @Override
    public TextureRegionDrawable getMakeScreenTexture() {
        return new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("power-on.png"))));
    }
}
