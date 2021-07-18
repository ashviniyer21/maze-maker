package com.github.ashviniyer21.components;

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
}
