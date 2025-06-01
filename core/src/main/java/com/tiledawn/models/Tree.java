package com.tiledawn.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Tree {

    public final static int height = 250;
    public final static int width = 100;


    private final Rectangle bounds;
    private Sprite sprite;
    private float animationTime;


    public Tree(Rectangle bounds) {
        this.bounds = bounds;
        this.sprite = new Sprite();
        this.sprite.setBounds(bounds.x, bounds.y,width,height);
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

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        if(sprite != null){
            sprite.setSize(width, height);
        }
    }
}
