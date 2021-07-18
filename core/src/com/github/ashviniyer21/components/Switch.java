package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.ashviniyer21.player.Player;

public class Switch extends GridComponent{
    private boolean prevElectricity;
    public Switch(int x, int y) {
        super("switch-off.png", false, x, y);
        prevElectricity = false;
    }

    @Override
    public void interact(Player player) {
        if(player.hasElectricity()){
            if(player.getColor() == Player.Color.Orange){
                player.setColor(Player.Color.Blue);
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-blue.png"))));
            } else {
                player.setColor(Player.Color.Orange);
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-orange.png"))));
            }
        }
    }

    @Override
    public void update(Player player) {
        if(player.hasElectricity() != prevElectricity){
            if(player.hasElectricity()){
                player.setColor(Player.Color.Orange);
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-orange.png"))));
            } else {
                player.setColor(Player.Color.None);
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-off.png"))));
            }
            prevElectricity = player.hasElectricity();
        }
    }
}
