package com.tiledawn.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class BossFight {

    public static final int width = 140;
    public static final int height = 200;

    private final Rectangle bounds;
    private Sprite sprite;
    private int health;
    private final float speed;
    private long lastDash;
    private float animationTime;

    public BossFight(Rectangle bounds) {
        this.bounds = bounds;
        this.sprite = new Sprite();
        this.sprite.setBounds(bounds.x, bounds.y,width,height);
        this.health = 400;
        this.speed = 0.4f;
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

    public long getLastDash() {
        return lastDash;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        if(sprite != null){
            sprite.setSize(width, height);
        }
    }

    public void setLastDash(long lastDash) {
        this.lastDash = lastDash;
    }

    public void decreaseHealth(int health) {
        this.health -= health;
    }

    public void setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
    }
}
