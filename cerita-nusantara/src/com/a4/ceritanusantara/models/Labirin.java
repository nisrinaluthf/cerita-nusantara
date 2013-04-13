package com.a4.ceritanusantara.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Labirin extends SubCerita{
	
	private LabirinWall[] walls;
	private LabirinPlayer player;
	
	private Texture wallTexture;
	private Texture playerTexture;
	
	private Vector2[] wallsPos;
	private Vector2 playerPos;
	
	private Rectangle[] wallsBounds;
	private Rectangle playerBounds;
	private Rectangle finishBounds;
	
	public Labirin(String nama, int tipe){
		super(nama, tipe);
		
		initWalls();
	}
	
	
	private void initWalls(){
		
	}
	
	public void setWallTexture(Texture wallTexture){
		this.wallTexture  = wallTexture;
	}
	
	public Texture getWallTexture(){
		return wallTexture;
	}
	
	public void setPlayerTexture(Texture playerTexture){
		this.playerTexture  = playerTexture;
	}
	
	public Texture getPlayerTexture(){
		return playerTexture;
	}
	
	public void setWallsPos(Vector2[] wallsPos){
		this.wallsPos = wallsPos;
	}
	
	public Vector2 getWallsPos(int i){
		return wallsPos[i];
	}
	
	public void setPlayerPos(Vector2 playerPos){
		this.playerPos = playerPos;
	}
	
	public Vector2 getPlayerPos(){
		return playerPos;
	}
	
	public void setWallsBounds(Rectangle[] wallsBounds){
		this.wallsBounds = wallsBounds;
	}
	
	public Rectangle getWallsBounds(int i){
		return wallsBounds[i];
	}
	
	public void setPlayerBounds(Rectangle playerBounds){
		this.playerBounds = playerBounds;
	}
	
	public Rectangle getPlayerBounds(){
		return playerBounds;
	}
	
	public void setFinishBounds(Rectangle finishBounds){
		this.finishBounds = finishBounds;
	}
	
	public Rectangle getFinishBounds(){
		return finishBounds;
	}
	
}
