package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.ashviniyer21.player.Player;

public class TemporaryFloor extends GridComponent{
    private final Player.Color color;
    private Player.Color tempColor;
    public TemporaryFloor(Player.Color color, int x, int y) {
        super("invisible-floor.png", false, x, y);
        if(color == Player.Color.Orange){
            setRegion(new TextureRegion(new Texture(Gdx.files.internal("orange-floor.png"))));
            walkable = true;
        }
        this.color = color;
        tempColor = Player.Color.Orange;
    }

    @Override
    public boolean interact(Player player) {
        return false;
    }

    @Override
    public void update(Player player) {
        if(player.getColor() != tempColor){
            if(player.getColor() == color){
                walkable = true;
                if(player.getColor() == Player.Color.Orange){
                    setRegion(new TextureRegion(new Texture(Gdx.files.internal("orange-floor.png"))));
                } else {
                    setRegion(new TextureRegion(new Texture(Gdx.files.internal("blue-floor.png"))));
                }
            } else {
                walkable = false;
                setRegion(new TextureRegion(new Texture(Gdx.files.internal("invisible-floor.png"))));
            }
            tempColor = player.getColor();
        }
    }

    @Override
    public TextureRegionDrawable getMakeScreenTexture() {
        if(this.color == Player.Color.Orange){
            return new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange-floor.png"))));
        } else {
            return new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("blue-floor.png"))));
        }
    }

    public Player.Color getColor(){
        return color;
    }
}
