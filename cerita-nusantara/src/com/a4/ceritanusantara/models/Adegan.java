package com.a4.ceritanusantara.models;

import com.badlogic.gdx.graphics.Texture;

public class Adegan extends SubCerita {
	
	private Texture background;
	private Texture[] charaTexture;

	public Adegan(String nama, int tipe) {
		super(nama, tipe);
		// TODO Auto-generated constructor stub
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
	
	public Texture getCharaTexture(int i){
		return charaTexture[i];
	}
	
}