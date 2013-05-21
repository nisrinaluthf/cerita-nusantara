package com.a4.ceritanusantara.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PuzzlePiece {
	public static int TOL = 40;
	
	private final Vector2 INIT_POS = new Vector2(0,0);
	
	private PuzzleTarget target;
	
	private Vector2 pos;
	private Rectangle bounds;
	private boolean focused;
	private boolean solved;
	
	private Texture texture;
	
	public PuzzlePiece(PuzzleTarget target, Texture texture){
		this.target = target;
		this.texture = texture;
		focused = false;
		solved = false;
		pos = INIT_POS;
		bounds = new Rectangle(pos.x+(texture.getWidth()/2)-TOL/2, 
				pos.y+(texture.getHeight()/2)-TOL/2, TOL, TOL);
	}
	
	public void reinit(){
		focused = false;
		solved = false;
	}
	
	public void setX(float x){
		pos.x = x;
		bounds.setX(pos.x);
	}
	
	public void setY(float y){
		pos.y = y;
		bounds.setY(pos.y);
	}
	
	public PuzzleTarget getTarget(){
		return target;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
}
