package com.a4.ceritanusantara.utils;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.AdeganText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class InitAdegan {
	
	public static void InitAdegan(Adegan adegan, FileHandle file){
		String data = file.readString();
		StringTokenizer st = new StringTokenizer(data, System.getProperty("line.separator"));
		
		Texture background = new Texture(Gdx.files.internal(st.nextToken()));
		adegan.setBackground(background);
		
		Texture[] chara = new Texture[Integer.parseInt(st.nextToken())];
		
		for(int i=0; i<chara.length; i++){
			chara[i] = new Texture(Gdx.files.internal(st.nextToken()));
		}
		adegan.setCharaTexture(chara);
		
		AdeganText[] text = new AdeganText[Integer.parseInt(st.nextToken())];
		
		for(int i=0; i<text.length; i++){
			//float 
			//text[i] = new AdeganText
		}
		
		while(st.hasMoreTokens()){
			
		}
	}
}
