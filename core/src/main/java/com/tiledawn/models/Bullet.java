package com.tiledawn.models;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Bullet {

    private final Rectangle bounds;
    private final static float speed =200f;
    private final int damage;
    private final Vector3 direction;

    public Bullet(float x,float y, int damage,Vector3 direction) {
        this.bounds = new Rectangle(x,y,20,20);
        this.damage = damage;
        this.direction = direction;
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public void update (float delta){
        this.bounds.setPosition(bounds.x + direction.x * speed * delta, bounds.y + direction.y * speed * delta);
    }

    public boolean isOffScreen(float worldSize){
        return this.bounds.x < 0 || this.bounds.x > worldSize || this.bounds.y < 0 || this.bounds.y > worldSize;
    }

}
