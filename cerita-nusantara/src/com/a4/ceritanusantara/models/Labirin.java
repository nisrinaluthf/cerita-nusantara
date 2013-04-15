package com.a4.ceritanusantara.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Labirin extends SubCerita{
	
	private LabirinWall[] walls;
	private LabirinPlayer player;
	private LabirinItem[] items;
	
	private Texture background;
	private Texture wallTexture;
	private Texture[] playerTexture;
	private Texture itemTexture;
	
	public float timeLeft;
	private boolean gameOver;
	
	private final float time = 91f;
	
	/*
	private Vector2[] wallsPos;
	private Vector2 playerPos;
	
	private Rectangle[] wallsBounds;
	private Rectangle playerBounds;
	private Rectangle finishBounds;
	*/
	
	public Labirin(String nama, int tipe){
		super(nama, tipe);
		
		timeLeft = time;
		gameOver = false;
	}
	
	public void setBackground(Texture background){
		this.background = background;
	}
	
	public void setWalls(LabirinWall[] walls){
		this.walls = walls;
	}
	
	public void setPlayer(LabirinPlayer player){
		this.player = player;
	}
	
	public void setItems(LabirinItem[] items){
		this.items = items;
	}
	
	public void setWallTexture(Texture wallTexture){
		this.wallTexture = wallTexture;
	}
	
	public void setPlayerTexture(Texture[] playerTexture){
		this.playerTexture = playerTexture;
	}
	
	public void setItemTexture(Texture itemTexture){
		this.itemTexture = itemTexture;
	}
	
	public Texture getBackground(){
		return background;
	}
	
	public LabirinWall[] getWalls(){
		return walls;
	}
	
	public LabirinPlayer getPlayer(){
		return player;
	}
	
	public LabirinItem[] getItems(){
		return items;
	}
	
	public Texture getWallTexture(){
		return wallTexture;
	}
	
	public Texture[] getPlayerTexture(){
		return playerTexture;
	}
	
	public Texture getItemTexture(){
		return itemTexture;
	}
	

	public void reinit() {
		player.reinit();
		timeLeft = time;
		for(int i=0; i<items.length; i++){
			items[i].setFound(false);
		}
		gameOver = false;
	}
	
	public void update(){
		player.updatePos();
	}

	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return gameOver;
	}
	
	public void gameOver(){
		gameOver = false;
	}
	
	
	
}
