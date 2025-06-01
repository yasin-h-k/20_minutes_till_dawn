package com.tiledawn.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Point {
    public final static int height = 20;
    public final static int width = 20;
    public final static int Xp = 3;

    private final Rectangle bounds;

    public Point(Rectangle bounds) {
        this.bounds = bounds;
        bounds.setSize(height, width);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
