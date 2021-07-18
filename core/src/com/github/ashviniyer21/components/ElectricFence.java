package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.ashviniyer21.player.Player;

public class ElectricFence extends GridComponent{
    private boolean prevElectricity;
    public ElectricFence(int x, int y) {
        super("electric-fence-disabled.png", true, x, y);
        prevElectricity = false;
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void update(Player player) {
        if(player.hasElectricity() != prevElectricity){
            if(player.hasElectricity()){
                walkable = false;
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("electric-fence-enabled.png"))));
            } else {
                walkable = true;
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("electric-fence-disabled.png"))));
            }
            prevElectricity = player.hasElectricity();
        }

    }
}
