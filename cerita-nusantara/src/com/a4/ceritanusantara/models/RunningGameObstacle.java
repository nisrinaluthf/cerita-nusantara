package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;

public class RunningGameObstacle {
	
public static final float INIT_POS = 660;
	
	private int index;
	private float pos;
	private Rectangle bounds;
	private boolean hit;
	public RunningGameObstacle(int index){
		this.setIndex(index);
		
		setPos(INIT_POS);
		
		float xBounds = -100;
		if(index==0){
			xBounds = 274;
		}
		else if(index==1){
			xBounds = 449;
		}
		else if(index==2){
			xBounds = 634;
		}
		
		setBounds(new Rectangle(xBounds, INIT_POS+31, 124, 80));
		
		
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
		return pos;
	}
	public void setPos(float pos) {
		this.pos = pos;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
