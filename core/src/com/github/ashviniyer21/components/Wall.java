package com.github.ashviniyer21.components;

import com.github.ashviniyer21.player.Player;

public class Wall extends GridComponent {

    public Wall(int x, int y) {
        super("wall.png", false, x, y);
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void update(Player player) {

    }
}
