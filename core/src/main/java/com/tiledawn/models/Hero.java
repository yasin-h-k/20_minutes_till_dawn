package com.tiledawn.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Hero {
    SHANA(4, 4),
    DIAMOND(7,1),
    SCARLET(3,5),
    LILITH(5,3),
    DASHER(2,10);

    private final int health;
    private final int speed;
    private Animation<TextureRegion> animation;

    Hero(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }
}
