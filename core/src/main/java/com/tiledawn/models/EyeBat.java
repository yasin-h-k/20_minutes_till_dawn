package com.tiledawn.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class EyeBat {
    public final static int height = 60;
    public final static int width = 80;


    private final Rectangle bounds;
    private Sprite sprite;
    private int health = 50;
    private final float speed;
    private float animationTime;
    private long lastShotTime;

    public EyeBat(Rectangle bounds) {
        this.bounds = bounds;
        this.sprite = new Sprite();
        this.sprite.setBounds(bounds.x, bounds.y,width,height);

        this.speed = 0.2f;
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

    public long getLastShotTime() {
        return lastShotTime;
    }



    public void setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
    }

    public void decreaseHealth(int health) {
        this.health -= health;
    }

    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        if(sprite != null){
            sprite.setSize(width, height);
        }
    }
}
