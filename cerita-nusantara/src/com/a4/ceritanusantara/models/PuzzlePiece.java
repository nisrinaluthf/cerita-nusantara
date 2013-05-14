package com.a4.ceritanusantara.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PuzzlePiece {
	public static int TOL = 40;
	
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
	}
	
	public void reinit(){
		focused = false;
		solved = false;
	}
	
	public void setPosition(Vector2 pos){
		this.pos.x = pos.x;
		this.pos.y = pos.y;
		bounds = new Rectangle(pos.x+(texture.getWidth()/2)-TOL/2, 
				pos.y+(texture.getHeight()/2)-TOL/2, TOL, TOL);
	}
	

}
