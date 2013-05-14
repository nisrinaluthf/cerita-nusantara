package com.a4.ceritanusantara.models;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Puzzle extends SubCerita{
	
	private static final float TIME = 91f;
	
	private PuzzlePiece[] pieces;
	
	private Texture background;
	public float timeLeft;
	private boolean gameOver;
	
	private Random rand;
	
	public Puzzle(String nama, int tipe){
		super(nama, tipe);
		rand = new Random();
		timeLeft = TIME;
		gameOver = false;
	}
	
	public void setPieces(PuzzlePiece[] pieces){
		this.pieces = pieces;
	}
	
	public void setBackground(Texture background){
		this.background = background;
	}
	
	public void decTime(float delta){
		timeLeft -= delta;
	}
	
	public void gameOver(){
		gameOver = true;
	}
	
	public PuzzlePiece[] getPieces(){
		return pieces;
	}
	
	public PuzzlePiece getPiece(int i){
		return pieces[i];
	}
	
	public Texture getBackground(){
		return background;
	}
	
	public float getTimeLeft(){
		return timeLeft;
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
	
	public void reinit(){
		/*
		for (int i=0; i<pieces.length; i++){
			pieces[i].reinit();
		}
		*/
		/*randomize();*/
		timeLeft = TIME;
		gameOver = false;
	}

	private void randomize() {
		for (int i=0; i<pieces.length; i++){
			float x = rand.nextFloat();
			//tbc
		}
		
	}
	
}
