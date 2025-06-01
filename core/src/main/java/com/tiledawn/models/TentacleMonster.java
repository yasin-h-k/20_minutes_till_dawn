package com.tiledawn.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class TentacleMonster {

    public final static int height = 120;
    public final static int width = 30;

    private final Rectangle bounds;
    private Sprite sprite;
    private final float speed;
    private float animationTime;
    private int health = 25;

    public TentacleMonster(Rectangle bounds) {
        this.bounds = bounds;
        this.sprite = new Sprite();
        this.sprite.setBounds(bounds.x, bounds.y,width,height);

        this.speed = 0.2f;

    }

    public int getHealth() {
        return health;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAnimationTime() {
        return animationTime;
    }

    public void setAnimationTime(float animationTime) {
        this.animationTime = animationTime;
    }

    public void decreaseHealth(int health) {
        this.health -= health;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        if(sprite != null){
            sprite.setSize(width, height);
        }
    }
}
