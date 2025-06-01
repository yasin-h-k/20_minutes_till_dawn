package com.tiledawn.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Explosion {
    public static final int width = 50;
    public static final int height = 50;


    private final Rectangle bounds;
    private final Sprite sprite;
    private float animationTime;

    public Explosion(Rectangle bounds) {
        this.bounds = bounds;
        this.sprite = new Sprite();
        this.sprite.setSize(width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public void setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
    }
}
