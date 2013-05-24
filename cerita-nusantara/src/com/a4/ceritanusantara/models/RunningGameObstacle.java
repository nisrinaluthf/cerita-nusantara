package com.a4.ceritanusantara.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class RunningGameObstacle {
	
public static final float INIT_POS = 660;
	
	private int index;
	private float posY;
	private Rectangle bounds;
	private boolean hit;
	private Texture obstacle;
	private int type;
	
	
	public RunningGameObstacle(int index, int type){
		this.setIndex(index);
		this.setType(type);
		setPos(INIT_POS);
		
		float xBounds = -100;
		if(index==0){
			xBounds = 250;
		}
		else if(index==1){
			xBounds = 512;
		}
		else if(index==2){
			xBounds = 774;
		}
		
		if(type==0){
			this.obstacle = new Texture(
					Gdx.files.internal("selatbali_running/obstacle_1.png"));
		}
		else if(type==1){
			this.obstacle = new Texture(
					Gdx.files.internal("selatbali_running/obstacle_2.png"));
		}
		else if(type==2){
			this.obstacle = new Texture(
					Gdx.files.internal("selatbali_running/obstacle_3.png"));
		}
		
		setBounds(new Rectangle(xBounds, INIT_POS+31, obstacle.getWidth(), obstacle.getHeight()));
		
		
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
}
