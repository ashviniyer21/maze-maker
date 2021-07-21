package com.github.ashviniyer21.components;

import com.github.ashviniyer21.player.Player;

public class EndSpace extends GridComponent{
    public EndSpace(int x, int y) {
        super("end.png", false, x, y);
    }

    @Override
    public void interact(Player player) {
        System.out.println("Won");
        player.won();
    }

    @Override
    public void update(Player player) {

    }
}
