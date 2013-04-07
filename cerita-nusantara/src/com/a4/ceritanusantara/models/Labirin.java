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
	
	
}
