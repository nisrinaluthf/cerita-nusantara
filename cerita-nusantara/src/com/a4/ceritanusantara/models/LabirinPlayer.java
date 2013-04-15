package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LabirinPlayer {
	
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	
	private Vector2 initPos;
	private Vector2 pos;
	private Vector2 velocity;
	private Rectangle bounds;
	private int state;
	
	
	public LabirinPlayer(Vector2 vector2) {
		// TODO Auto-generated constructor stub
		initPos = vector2;
		pos = initPos;
		bounds = new Rectangle(pos.x, pos.y, 23, 23);
		velocity = new Vector2(0, 0);
		state = UP;
	}
	
	public void updatePos(){
		pos.x = pos.x + velocity.x;
		pos.y = pos.y + velocity.y;
		bounds.x = bounds.x + velocity.x;
		bounds.y = bounds.y + velocity.y;
	}
	
	public void setVelocityX(float x){
		velocity.x = x;
	}
	
	public void setVelocityY(float y){
		velocity.y = y;
	}
	
	public float getVelocityX(){
		return velocity.x;
	}
	
	public float getVelocityY(){
		return velocity.y;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	public int getState(){
		return state;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	public void reinit() {
		// TODO Auto-generated method stub
		pos = initPos;
		state = UP;
	}

}
