package com.tiledawn.models;

public class Game {
    private final Player player;
    private long time;

    public Game(Player player,int time) {
        this.player = player;
        this.time = (long) time * 60 * 1000;
    }


    public Player getPlayer() {
        return player;
    }

    public long getTime() {
        return time;
    }


}
