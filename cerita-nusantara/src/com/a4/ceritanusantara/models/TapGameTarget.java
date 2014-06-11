package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;

/**
 * Kelas TapGameTarget merepresentasikan target-target pada Tap Game.
 *
 */
public class TapGameTarget {
    /*
     * Kelas ini merupakan class model yang berisikan karakterisasi target
     * pada permainan tap game.
     */

    public static final float VELOCITY = 4.0f;
    public static final float INIT_POS = 660;
    private int index;
    private float pos;
    private Rectangle bounds;
    private int type;
    private boolean bad;
    private boolean pressed;
    private boolean hit;

    /**
     * Konstruktor dengan parameter index, tipe, dan tanda gagal menekan target.
     *
     * @param index
     * @param type
     * @param bad
     */
    public TapGameTarget(int index, int type, boolean bad) {
        this.index = index;
        this.type = type;
        this.bad = bad;

        pos = INIT_POS;

        float xBounds = -100;
        if (index == 0) {
            xBounds = 274;
        } else if (index == 1) {
            xBounds = 449;
        } else if (index == 2) {
            xBounds = 634;
        }

        bounds = new Rectangle(xBounds, INIT_POS + 31, 124, 80);

        pressed = false;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public int getIndex() {
        return index;
    }

    public float getPos() {
        return pos;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getType() {
        return type;
    }

    public boolean isBad() {
        return bad;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isHit() {
        return hit;
    }

    public void update() {
        pos -= VELOCITY;
        bounds.y -= VELOCITY;
    }
}
