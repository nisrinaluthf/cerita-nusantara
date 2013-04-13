package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;

public class TapGameTarget {
	
	public static final float VELOCITY = 4.0f;
	public static final float INIT_POS = 660;
	
	private int index;
	private float pos;
	private Rectangle bounds;
	private int type;
	private boolean bad;
	private boolean pressed;
	
	public TapGameTarget(int index, int type, boolean bad){
		this.index = index;
		this.type = type;
		this.bad = bad;
		
		pos = INIT_POS;
		
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
		
		bounds = new Rectangle(xBounds, INIT_POS+51, 124, 40);
		
		pressed = false;
	}
	
	public void setPressed(boolean pressed){
		this.pressed = pressed;
	}
	
	public int getIndex(){
		return index;
	}
	
	public float getPos(){
		return pos;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public int getType(){
		return type;
	}
	
	public boolean isBad(){
		return bad;
	}
	
	public boolean isPressed(){
		return pressed;
	}
	
	public void update(){
		pos -= VELOCITY;
		bounds.y -=VELOCITY;
	}
	
}
