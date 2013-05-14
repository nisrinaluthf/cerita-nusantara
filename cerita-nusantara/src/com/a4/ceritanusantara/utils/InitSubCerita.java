package com.a4.ceritanusantara.utils;

import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.models.Labirin; 
import com.a4.ceritanusantara.models.LabirinWall;
import com.a4.ceritanusantara.models.LabirinPlayer;
import com.a4.ceritanusantara.models.LabirinItem; 
import com.a4.ceritanusantara.models.PuzzlePiece;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.a4.ceritanusantara.models.TapGameButton;
import com.a4.ceritanusantara.models.Puzzle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class InitSubCerita {
	
	public static void initSumatera(Cerita cerita){
		
		FileHandle file = Gdx.files.internal("data/datasumatera");
		
		FileHandle localFile = Gdx.files.local("datasumatera");
		
		if(!localFile.exists()){
			localFile.writeString(file.readString(), false);
		}
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(token,  " ");
			boolean unlocked = false;
			if(st2.nextToken().equals("unlocked")) unlocked = true;
			int score = Integer.parseInt(st2.nextToken());
			
			cerita.getSubCerita(i).setUnlocked(unlocked);
			cerita.getSubCerita(i).setScore(score);
			
			i++;
		}
		
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(0), 
				Gdx.files.internal("dialog/sumatera/scene1_sumatera.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(1), 
				Gdx.files.internal("dialog/sumatera/scene2_sumatera.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(2), 
				Gdx.files.internal("dialog/sumatera/scene3_sumatera.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(4), 
				Gdx.files.internal("dialog/sumatera/scene4_sumatera.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(5), 
				Gdx.files.internal("dialog/sumatera/scene5_sumatera.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(6), 
				Gdx.files.internal("dialog/sumatera/scene6_sumatera.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(7), 
				Gdx.files.internal("dialog/sumatera/scene7_sumatera.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(9), 
				Gdx.files.internal("dialog/sumatera/scene8_sumatera.txt"));
		
		((TapGame) cerita.getSubCerita(3)).setBackground(new 
				Texture(Gdx.files.internal("siparkit_tapgame/bg.png")));
		((TapGame) cerita.getSubCerita(3)).setPanelBackground(new 
				Texture(Gdx.files.internal("siparkit_tapgame/panel_bg.png")));
		((TapGame) cerita.getSubCerita(3)).setTargetsTexture(new Texture[]{
				new Texture(Gdx.files.internal("siparkit_tapgame/target1.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target2.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target3.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target2_bad.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target3_bad.png")),
		});
		((TapGame) cerita.getSubCerita(3)).setTargetsPressedTexture(new Texture[]{
				new Texture(Gdx.files.internal("siparkit_tapgame/target1_hit.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target2_hit.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target3_hit.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target1_bad_hit.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target2_bad_hit.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/target3_bad_hit.png")),
		});
		((TapGame) cerita.getSubCerita(3)).setIndicators(new Texture[]{
				new Texture(Gdx.files.internal("siparkit_tapgame/pemburu_senang.png")),
				new Texture(Gdx.files.internal("siparkit_tapgame/pemburu_sedih.png")),
		});
		
		
		((TapGame) cerita.getSubCerita(3)).setButtons(new TapGameButton[]{
				new TapGameButton(1, new Vector2(270, 20),
						new Texture(Gdx.files.internal("siparkit_tapgame/button1.png")),
						new Texture(Gdx.files.internal("siparkit_tapgame/button1_pressed.png"))),
				new TapGameButton(2, new Vector2(445, 20),
						new Texture(Gdx.files.internal("siparkit_tapgame/button2.png")),
						new Texture(Gdx.files.internal("siparkit_tapgame/button2_pressed.png"))),
				new TapGameButton(3, new Vector2(630, 20),
						new Texture(Gdx.files.internal("siparkit_tapgame/button3.png")),
						new Texture(Gdx.files.internal("siparkit_tapgame/button3_pressed.png")))
		});
		
		InitLabirin.initLabirin((Labirin) cerita.getSubCerita(8), 
				Gdx.files.internal("siparkit_labirin/siparkit_labirin.txt"));
		
		InitKuis.initKuis((Kuis) cerita.getSubCerita(10), 
				Gdx.files.internal("kuis/siparkit.txt"));
	}
	
	
	public static void initKalimantan(Cerita cerita){
		
		FileHandle file = Gdx.files.internal("data/datakalimantan");
		FileHandle localFile = Gdx.files.local("datakalimantan");
		
		if(!localFile.exists()){
			localFile.writeString(file.readString(), false);
		}
		
		String data = localFile.readString();
		
		StringTokenizer st = new StringTokenizer(data,  ";");
		
		int i=0;
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			
			StringTokenizer st2 = new StringTokenizer(token,  " ");
			
			boolean unlocked = false;
			if(st2.nextToken().equals("unlocked")) unlocked = true;
			int score = Integer.parseInt(st2.nextToken());
			
			cerita.getSubCerita(i).setUnlocked(unlocked);
			cerita.getSubCerita(i).setScore(score);
			
			i++;
		}
		
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(0), 
				Gdx.files.internal("dialog/kalimantan/scene1_kalimantan.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(1), 
				Gdx.files.internal("dialog/kalimantan/scene2_kalimantan.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(3), 
				Gdx.files.internal("dialog/kalimantan/scene3_kalimantan.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(4), 
				Gdx.files.internal("dialog/kalimantan/scene4_kalimantan.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(5), 
				Gdx.files.internal("dialog/kalimantan/scene5_kalimantan.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(6), 
				Gdx.files.internal("dialog/kalimantan/scene6_kalimantan.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(8), 
				Gdx.files.internal("dialog/kalimantan/scene7_kalimantan.txt"));
		
		
		InitLabirin.initLabirin((Labirin) cerita.getSubCerita(2), 
				Gdx.files.internal("nusa_labirin/nusa_labirin.txt"));
		
		((TapGame) cerita.getSubCerita(7)).setBackground(new 
				Texture(Gdx.files.internal("nusa_tapgame/bg.png")));
		((TapGame) cerita.getSubCerita(7)).setPanelBackground(new 
				Texture(Gdx.files.internal("siparkit_tapgame/panel_bg.png")));
		((TapGame) cerita.getSubCerita(7)).setTargetsTexture(new Texture[]{
				new Texture(Gdx.files.internal("nusa_tapgame/target1.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1_bad.png")),
		});
		((TapGame) cerita.getSubCerita(7)).setTargetsPressedTexture(new Texture[]{
				new Texture(Gdx.files.internal("nusa_tapgame/target1_hit.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1_hit.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1_hit.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1_bad_hit.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1_bad_hit.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/target1_bad_hit.png")),
		});
		((TapGame) cerita.getSubCerita(7)).setIndicators(new Texture[]{
				new Texture(Gdx.files.internal("nusa_tapgame/nusa_senang.png")),
				new Texture(Gdx.files.internal("nusa_tapgame/nusa_sedih.png")),
		});
		
		((TapGame) cerita.getSubCerita(7)).setButtons(new TapGameButton[]{
				new TapGameButton(1, new Vector2(270, 20),
						new Texture(Gdx.files.internal("siparkit_tapgame/button1.png")),
						new Texture(Gdx.files.internal("siparkit_tapgame/button1_pressed.png"))),
				new TapGameButton(2, new Vector2(445, 20),
						new Texture(Gdx.files.internal("siparkit_tapgame/button2.png")),
						new Texture(Gdx.files.internal("siparkit_tapgame/button2_pressed.png"))),
				new TapGameButton(3, new Vector2(630, 20),
						new Texture(Gdx.files.internal("siparkit_tapgame/button3.png")),
						new Texture(Gdx.files.internal("siparkit_tapgame/button3_pressed.png")))
		});
		
		InitKuis.initKuis((Kuis) cerita.getSubCerita(9), 
				Gdx.files.internal("kuis/nusa.txt"));
		
	}
	
	public static void initJawa(Cerita cerita) {
		FileHandle file = Gdx.files.internal("data/datajawa");
		
		FileHandle localFile = Gdx.files.local("datajawa");
		
		if(!localFile.exists()){
			localFile.writeString(file.readString(), false);
		}
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(token,  " ");
			boolean unlocked = false;
			if(st2.nextToken().equals("unlocked")) unlocked = true;
			int score = Integer.parseInt(st2.nextToken());
			
			cerita.getSubCerita(i).setUnlocked(unlocked);
			cerita.getSubCerita(i).setScore(score);
			
			i++;
		}
		
		((Puzzle) cerita.getSubCerita(2)).setBackground(
				new Texture(Gdx.files.internal("ajisaka_puzzle/bg.png")));
		
		PuzzlePiece[] pieces = new PuzzlePiece[20];
		
		for (int j=0; j<pieces.length; j++){
			pieces[j] = new PuzzlePiece(new Texture(Gdx.files.internal("")));
		}
	}
	
}
