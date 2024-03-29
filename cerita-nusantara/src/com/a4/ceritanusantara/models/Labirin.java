package com.a4.ceritanusantara.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Kelas <code>Puzzle</code> merepresentasikan suatu subcerita bertipe permainan,
 * berjenis Labirin secara keseluruhan. Kelas ini menampung di antaranya keadaan papan permainan,
 * status pemain, serta aset gambar. 
 * 
 */

public class Labirin extends SubCerita{
	
	private LabirinWall[] walls;
	private LabirinPlayer player;
	private LabirinItem[] items;
	
	private Texture background;
	private Texture wallTexture;
	private Texture[] playerTexture;
	private Texture itemTexture;
	
	private Rectangle finish;
	
	public float timeLeft;
	private boolean gameOver;
	
	private final float TIME = 91f;
	
	/*
	private Vector2[] wallsPos;
	private Vector2 playerPos;
	
	private Rectangle[] wallsBounds;
	private Rectangle playerBounds;
	private Rectangle finishBounds;
	*/
	
	public Labirin(String nama, int tipe){
		super(nama, tipe);
		
		timeLeft = TIME;
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
	
	public LabirinItem getItem(int i){
		return items[i];
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
		timeLeft = TIME;
		for(int i=0; i<items.length; i++){
			items[i].setFound(false);
		}
		gameOver = false;
	}
	
	public void update(){
		player.updatePos();
	}

	public boolean isGameOver() {
		return gameOver;
	}
	
	public void gameOver(){
		gameOver = true;
	}

	public void setFinishBounds(Rectangle r){
		finish = r;
	}
	
	public Rectangle getFinishBounds() {
		return finish;
	}
	
	public void dispose() {
		this.background.dispose();
		this.wallTexture.dispose();
		this.itemTexture.dispose();
		for(int idx = 0; idx < this.playerTexture.length; idx++) {
			this.playerTexture[idx].dispose();
		}
	}
}
