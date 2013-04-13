package com.a4.ceritanusantara.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TapGameButton {
	private int index;
	private Vector2 pos;
	private Texture buttonTexture;
	private Texture buttonPressedTexture;
	private Rectangle bounds;
	private boolean pressed;
	
	public TapGameButton(int index, Vector2 pos, Texture buttonTexture, 
			Texture buttonPressedTexture){
		this.index = index;
		this.pos = pos;
		this.buttonTexture = buttonTexture;
		this.buttonPressedTexture = buttonPressedTexture;
		bounds = new Rectangle(pos.x, pos.y, buttonTexture.getWidth(),
				buttonTexture.getHeight());
		pressed = false;
	}
	
	public void setPressed(boolean pressed){
		this.pressed = pressed;
	}
	
	public int getIndex(){
		return index;
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public Texture getButtonTexture(){
		return buttonTexture;
	}
	
	public Texture getButtonPressedTexture(){
		return buttonPressedTexture;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public boolean isPressed(){
		return pressed;
	}
}
