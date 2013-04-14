package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LabirinWall {
	private Vector2 pos;
	private Rectangle bounds;
	
	public LabirinWall(Vector2 pos){
		this.pos = pos;
		bounds = new Rectangle(pos.x, pos.y, 25, 25);
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public Vector2 getPos(){
		return pos;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
}
