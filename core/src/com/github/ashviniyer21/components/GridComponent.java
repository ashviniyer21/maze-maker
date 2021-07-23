package com.github.ashviniyer21.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.ashviniyer21.player.Player;

public abstract class GridComponent extends TextureRegionDrawable {
    protected boolean walkable;
    private int x, y;
    public GridComponent(String imgLocation, boolean walkable, int x, int y){
        super(new TextureRegion(new Texture(Gdx.files.internal(imgLocation))));
        this.walkable = walkable;
        this.x = x;
        this.y = y;
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public abstract void interact(Player player);

    public abstract void update(Player player);

    public abstract TextureRegionDrawable getMakeScreenTexture();

    public boolean isWalkable(){
        return walkable;
    }
}