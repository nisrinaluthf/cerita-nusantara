package com.a4.ceritanusantara.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Kelas <code>AdeganText</code> merepresentasikan suatu teks (narasi atau dialog)
 * pada yang ditampilkan pada suatu babak dalam suatu adegan. Kelas ini adalah bagian dari
 * kumpulan kelas model untuk subcerita adegan.
 * 
 */

public class AdeganText {
	public static final int NAR = 0;
	public static final int DIA = 1;
	
	private String text;
	private int type;
	//private float end;
	private int chara;
	private Texture background;
	
	public AdeganText(String text, int type, Texture background){
		this.text = text;
		this.type = type;
		this.background = background;
	}
	
	public AdeganText(String text, int type, String path) {
		this.text = text;
		this.type = type;
		this.background = new Texture(Gdx.files.internal(path));
	}

	public String getText(){
		return text;
	}
	
	public int getType(){
		return type;
	}
	/*
	public float getEnd(){
		return end;
	}
	*/
	
	public void setChara(int chara){
		this.chara = chara;
	}
	
	public int getChara(){
		return chara;
	}
	

	public void setBackground(Texture background) {
		this.background = background;
	}
	
	public Texture getBackground(){
		return background;
	}
	
	public void dispose() {
		this.background.dispose();
	}
}