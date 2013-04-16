package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LabirinItem {
	
	private Vector2 pos;
	private Rectangle bounds;
	private boolean found;

	public LabirinItem(Vector2 vector2) {
		// TODO Auto-generated constructor stub
		pos = vector2;
		bounds = new Rectangle(pos.x, pos.y, 25, 25);
		found = false;
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public void setFound(boolean b){
		found = b;
	}
	
	public boolean isFound(){
		return found;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	

}
