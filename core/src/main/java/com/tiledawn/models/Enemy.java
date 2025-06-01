package com.tiledawn.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy {
    protected final Rectangle bounds;
    protected final Sprite sprite;
    protected int health;
    protected final float speed;
    protected float animationTime;

    public Enemy(Rectangle bounds, float speed, int health) {
        this.bounds = bounds;
        this.sprite = new Sprite();
        this.speed = speed;
        this.health = health;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public void decreaseHealth(int health) {
        this.health -= health;
    }

    public void setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
    }
}
