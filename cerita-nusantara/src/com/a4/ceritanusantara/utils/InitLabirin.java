package com.a4.ceritanusantara.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.LabirinItem;
import com.a4.ceritanusantara.models.LabirinPlayer;
import com.a4.ceritanusantara.models.LabirinWall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class InitLabirin {
	public static void initLabirin(Labirin labirin, FileHandle file){
		String data = file.readString();
		StringTokenizer st = new StringTokenizer(data, System.getProperty("line.separator"));
		
		labirin.setBackground(new
				Texture(Gdx.files.internal(st.nextToken())));
		labirin.setPlayerTexture(new Texture[]{
				new Texture(Gdx.files.internal(st.nextToken())),
				new Texture(Gdx.files.internal(st.nextToken())),
				new Texture(Gdx.files.internal(st.nextToken())),
				new Texture(Gdx.files.internal(st.nextToken())),
		});
		labirin.setWallTexture(new
				Texture(Gdx.files.internal(st.nextToken())));
		labirin.setItemTexture(new
				Texture(Gdx.files.internal(st.nextToken())));
	
		LabirinPlayer player = null;
		List<LabirinWall> walls = new ArrayList<LabirinWall>();
		List<LabirinItem> items = new ArrayList<LabirinItem>();
		Rectangle finish = null;
		int start = 137;
		int i=0;
		while(st.hasMoreTokens()){
			String line = st.nextToken();
			for(int j=0; j<30; j++){
				if(line.charAt(j)=='X'){
					walls.add(new LabirinWall(new Vector2(start+(25*j), 575-(i*25))));
				}
				
				else if(line.charAt(j)=='O'){
					items.add(new LabirinItem(new Vector2(start+(25*j), 575-(i*25))));
				}
				
				else if(line.charAt(j)=='P'){
					player = new LabirinPlayer(new Vector2(start+(25*j), 575-(i*25)));
				}
				
				else if(line.charAt(j)=='F'){
					finish = new Rectangle(start+(25*j), 575-(i*25), 75, 25);
					j+=2;
				}
			}
			
			i++;
		}
		
		labirin.setWalls(walls.toArray(new LabirinWall[0]));
		labirin.setItems(items.toArray(new LabirinItem[0]));
		labirin.setPlayer(player);
		labirin.setFinishBounds(finish);
	}
}
