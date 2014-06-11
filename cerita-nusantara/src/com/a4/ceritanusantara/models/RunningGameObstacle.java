package com.a4.ceritanusantara.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Kelas RunningGameObstacle merepresentasikan objek-objek yang menjadi
 * penghalang dalam Running Game dan karakterisasinya.
 *
 */
public class RunningGameObstacle {
    /*
     * Kelas ini merupakan class model yang berisikan method untuk objek 
     * rintangan pada Running Game.
     */

    public static final float INIT_POS = 660;
    private int index;
    private float posY;
    private Rectangle bounds;
    private boolean hit;
    private Texture obstacle;
    private int type;

    /**
     * Konstruktor dengan parameter index dan tipe obstacle.
     *
     * @param index
     * @param type
     */
    public RunningGameObstacle(int index, int type) {
        this.setIndex(index);
        this.setType(type);
        setPos(INIT_POS);
        hit = false;
        if (type == 0) {
            this.obstacle = new Texture(
                    Gdx.files.internal("selatbali_running/obstacle_1.png"));
        } else if (type == 1) {
            this.obstacle = new Texture(
                    Gdx.files.internal("selatbali_running/obstacle_2.png"));
        } else if (type == 2) {
            this.obstacle = new Texture(
                    Gdx.files.internal("selatbali_running/obstacle_3.png"));
        } else if (type == 3) {
            this.obstacle = new Texture(
                    Gdx.files.internal("selatbali_running/obstacle_4.png"));
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public float getPos() {
        return posY;
    }

    public void setPos(float pos) {
        this.posY = pos;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Texture getObstacleTexture() {
        return obstacle;
    }

    public void setObstacle(Texture obstacle) {
        this.obstacle = obstacle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public void dispose() {
		this.obstacle.dispose();
	}
}
