package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.ashviniyer21.player.Player;

public class Switch extends GridComponent{
    public Switch(int x, int y) {
        super("switch-off.png", false, x, y);
    }

    @Override
    public boolean interact(Player player) {
        if(player.hasElectricity()){
            if(player.getColor() == Player.Color.Orange){
                player.setColor(Player.Color.Blue);
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-blue.png"))));
            } else {
                player.setColor(Player.Color.Orange);
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-orange.png"))));
            }
            return true;
        }
        return false;
    }

    @Override
    public void update(Player player) {
        if(player.hasElectricity()){
            if(player.getColor() == Player.Color.Orange){
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-orange.png"))));
            } else {
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-blue.png"))));
            }
        } else {
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("switch-off.png"))));
        }
    }

    @Override
    public TextureRegionDrawable getMakeScreenTexture() {
        return new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch-orange.png"))));
    }
}
