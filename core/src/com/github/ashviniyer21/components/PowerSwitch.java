package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.ashviniyer21.player.Player;

public class PowerSwitch extends GridComponent {
    public PowerSwitch(int x, int y) {
        super("power-off.png", false, x, y);
    }

    @Override
    public void interact(Player player) {
        if(player.hasElectricity()){
            player.setElectricity(false);
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("power-off.png"))));
        } else {
            player.setElectricity(true);
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("power-on.png"))));
        }
    }

    @Override
    public void update(Player player) {

    }
}
