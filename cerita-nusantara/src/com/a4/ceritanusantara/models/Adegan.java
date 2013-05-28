package com.a4.ceritanusantara.models;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Adegan extends SubCerita {
	
	private Texture background;
	private Texture[] charaTexture;
	private AdeganText[] adeganText;
	
	private Music music;
	
	public float time;
	public int currentText;
	
	private boolean isDone;

	public Adegan(String nama, int tipe) {
		super(nama, tipe);
		// TODO Auto-generated constructor stub
		time = 0f;
		isDone = false;
		currentText = 0;
	}

	public void setBackground(Texture background) {
		// TODO Auto-generated method stub
		this.background = background;
	}
	
	public Texture getBackground(){
		return background;
	}
	
	public void setCharaTexture(Texture[] charaTexture){
		this.charaTexture = charaTexture;
	}
	
	public Texture[] getCharaTexture(){
		return charaTexture;
	}
	
	public Texture getCharaTexture(int i){
		return charaTexture[i];
	}
	
	public void setAdeganText(AdeganText[] adeganText){
		this.adeganText = adeganText;
	}
	
	public AdeganText[] getAdeganText(){
		return adeganText;
	}
	
	public AdeganText getAdeganText(int i){
		return adeganText[i];
	}
	
	public void setMusic(Music music){
		this.music = music;
	}
	
	public Music getMusic(){
		return music;
	}
	
	public boolean isDone(){
		return isDone;
	}
	
	public void done(){
		isDone = true;
	}
	
	public void updateCurrentText(){
		currentText++;
	}
	
	public int getCurrentText(){
		return currentText;
	}
	
	public void reinit(){
		time = 0f;
		isDone = false;
		currentText = 0;
	}
	
	public int getLength(){
		return adeganText.length;
	}
	
}