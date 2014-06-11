package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;

/**
 * Kelas <code>PuzzleTarget</code> merepresentasikan suatu target posisi
 * dari suatu keping puzzle pada permainan puzzle. Kelas ini adalah bagian dari
 * kumpulan kelas model untuk permainan puzzle.
 * 
 */

public class PuzzleTarget {
	Rectangle bounds;
	
	public PuzzleTarget(Rectangle bounds){
		this.bounds = bounds;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
}
