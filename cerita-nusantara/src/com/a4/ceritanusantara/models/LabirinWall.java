package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Kelas <code>LabirinWall</code> merepresentasikan suatu elemen dinding labirin
 * pada permainan labirin. Kelas ini adalah bagian dari
 * kumpulan kelas model untuk permainan labirin.
 * 
 */

public class LabirinWall {
	private Vector2 pos;
	private Rectangle[] bounds;
	
	public LabirinWall(Vector2 pos){
		this.pos = pos;
		bounds = new Rectangle[]{
				new Rectangle(pos.x, pos.y+25, 25, 2),
				new Rectangle(pos.x+25, pos.y, 2, 25),
				new Rectangle(pos.x, pos.y-2, 25, 2),
				new Rectangle(pos.x-2, pos.y, 2, 25),
		};
		
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public Vector2 getPos(){
		System.out.println(pos.x+" "+pos.y);
		return pos;
	}
	
	public Rectangle[] getBounds(){
		return bounds;
	}
	
	public Rectangle getBounds(int i){
		return bounds[i];
	}
}
