package com.a4.ceritanusantara.utils;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.AdeganText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class InitAdegan {
	
	public static void initAdegan(Adegan adegan, FileHandle file){
		String data = file.readString();
		StringTokenizer st = new StringTokenizer(data, System.getProperty("line.separator"));
		
		Texture background = new Texture(Gdx.files.internal((st.nextToken()).trim()));
		adegan.setBackground(background);
		
		Texture[] chara = new Texture[Integer.parseInt(st.nextToken().trim())];
		
		for(int i=0; i<chara.length; i++){
			chara[i] = new Texture(Gdx.files.internal(st.nextToken().trim()));
		}
		
		adegan.setCharaTexture(chara);
		
		AdeganText[] adeganText = new AdeganText[Integer.parseInt(st.nextToken().trim())];
		
		for(int i=0; i<adeganText.length; i++){
			StringTokenizer st2 = new StringTokenizer(st.nextToken(), ":");
			float end = Float.parseFloat(st2.nextToken().trim());
			
			int type=0;
			
			if(st2.nextToken().equals("dia")){
				type = 1;
			}
			
			int charaType = 0;
			if(type==1){
				charaType = Integer.parseInt(st2.nextToken().trim());
			}
			
			String text = st2.nextToken();
			
			adeganText[i] = new AdeganText(text, type, end);
			if(type==1){
				adeganText[i].setChara(charaType);
			}
			
		}
		
		adegan.setAdeganText(adeganText);
		
	}
}
