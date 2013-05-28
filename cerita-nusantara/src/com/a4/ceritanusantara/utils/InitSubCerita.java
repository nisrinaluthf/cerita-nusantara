package com.a4.ceritanusantara.utils;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.models.Labirin;  
import com.a4.ceritanusantara.models.PuzzlePiece;
import com.a4.ceritanusantara.models.PuzzleTarget;
import com.a4.ceritanusantara.models.RunningGame;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.Kuis;
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
		//localFile.writeString(file.readString(), false);
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		while(st.hasMoreTokens()){
			String token = st.nextToken().trim();
			StringTokenizer st2 = new StringTokenizer(token,  " ");
			boolean unlocked = false;
			if(st2.nextToken().trim().equals("unlocked")) unlocked = true;
			int score = Integer.parseInt(st2.nextToken().trim());
			
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
			String token = st.nextToken().trim();
			
			StringTokenizer st2 = new StringTokenizer(token,  " ");
			
			boolean unlocked = false;
			if(st2.nextToken().trim().equals("unlocked")) unlocked = true;
			int score = Integer.parseInt(st2.nextToken().trim());
			
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
		localFile.writeString(file.readString(), false);
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		while(st.hasMoreTokens()){
			String token = st.nextToken().trim();
			StringTokenizer st2 = new StringTokenizer(token,  " ");
			boolean unlocked = false;
			if(st2.nextToken().trim().equals("unlocked")) unlocked = true;
			int score = Integer.parseInt(st2.nextToken().trim());
			
			cerita.getSubCerita(i).setUnlocked(unlocked);
			cerita.getSubCerita(i).setScore(score);
			
			i++;
		}
		
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(0), 
				Gdx.files.internal("dialog/jawa/scene1_jawa.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(1), 
				Gdx.files.internal("dialog/jawa/scene2_jawa.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(3), 
				Gdx.files.internal("dialog/jawa/scene3_jawa.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(4), 
				Gdx.files.internal("dialog/jawa/scene4_jawa.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(5), 
				Gdx.files.internal("dialog/jawa/scene5_jawa.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(6), 
				Gdx.files.internal("dialog/jawa/scene6_jawa.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(7), 
				Gdx.files.internal("dialog/jawa/scene7_jawa.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(8), 
				Gdx.files.internal("dialog/jawa/scene8_jawa.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(10), 
				Gdx.files.internal("dialog/jawa/scene9_jawa.txt"));
		
		((Puzzle) cerita.getSubCerita(9)).setBackground(
				new Texture(Gdx.files.internal("ajisaka_puzzle/bg.png")));
		
		((Puzzle) cerita.getSubCerita(9)).setSolvedTexture(
				new Texture(Gdx.files.internal("ajisaka_puzzle/finished.png")));
		
		PuzzlePiece[][] pieces = new PuzzlePiece[4][4];
		
		file = Gdx.files.internal("ajisaka_puzzle/puzzle_pos");
		data = file.readString();
		st = new StringTokenizer(data,  System.getProperty("line.separator"));
		
		float offset = 65;
		float dimension = 20;
		
		for(int j=0; j<pieces.length; j++){
			for(int k=0; k<pieces[j].length; k++){	
				String line = st.nextToken().trim();
				StringTokenizer st2 = new StringTokenizer(line, ",");
				Vector2 tmp = new Vector2(Integer.parseInt(st2.nextToken().trim()),
						Integer.parseInt(st2.nextToken().trim()));
				
				Rectangle bounds = new Rectangle((k*150)+offset, ((j*150)+offset), 
						dimension, dimension);
				pieces[j][k] = new PuzzlePiece(new PuzzleTarget(bounds), tmp,
						new Texture(Gdx.files.internal
								("ajisaka_puzzle/"+(4-j)+"_"+(k+1)+".png")));		
			}
		}	
		
		((Puzzle) cerita.getSubCerita(9)).setPieces(pieces);
		((Puzzle) cerita.getSubCerita(9)).randomize();
		
		InitLabirin.initLabirin((Labirin) cerita.getSubCerita(2), 
				Gdx.files.internal("ajisaka_labirin/ajisaka_labirin.txt"));
		
		InitKuis.initKuis((Kuis) cerita.getSubCerita(11), 
				Gdx.files.internal("kuis/ajisaka.txt"));
	}
	
	public static void initBali(Cerita cerita){
		FileHandle file = Gdx.files.internal("data/databali");
		FileHandle localFile = Gdx.files.local("databali");
		if(!localFile.exists()){
			localFile.writeString(file.readString(), false);
		}
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		while(st.hasMoreTokens()){
			String token = st.nextToken().trim();
			StringTokenizer st2 = new StringTokenizer(token,  " ");
			boolean unlocked = false;
			if(st2.nextToken().trim().equals("unlocked")) unlocked = true;
			int score = Integer.parseInt(st2.nextToken().trim());
			
			cerita.getSubCerita(i).setUnlocked(unlocked);
			cerita.getSubCerita(i).setScore(score);
			
			i++;
		}
		
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(0), 
				Gdx.files.internal("dialog/bali/scene1_bali.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(2), 
				Gdx.files.internal("dialog/bali/scene2_bali.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(3), 
				Gdx.files.internal("dialog/bali/scene3_bali.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(4), 
				Gdx.files.internal("dialog/bali/scene4_bali.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(5), 
				Gdx.files.internal("dialog/bali/scene5_bali.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(7), 
				Gdx.files.internal("dialog/bali/scene6_bali.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(8), 
				Gdx.files.internal("dialog/bali/scene7_bali.txt"));
		InitAdegan.initAdegan((Adegan)cerita.getSubCerita(9), 
				Gdx.files.internal("dialog/bali/scene8_bali.txt"));
		
		
		((RunningGame) cerita.getSubCerita(1)).setBackground(
				new Texture(Gdx.files.internal("selatbali_running/bg.jpg")));
		((RunningGame) cerita.getSubCerita(1)).setPanelBackground(new 
				Texture(Gdx.files.internal("selatbali_running/panel_bg.jpg")));
		((RunningGame) cerita.getSubCerita(1)).setPlayer(new 
				Texture(Gdx.files.internal("selatbali_running/player.png")));
		((RunningGame) cerita.getSubCerita(1)).setProgressIcon(new 
				Texture(Gdx.files.internal("selatbali_running/progress_icon.png")));
		((RunningGame) cerita.getSubCerita(1)).setPlayerBounds(new Rectangle(
				((RunningGame) cerita.getSubCerita(1)).getPlayerXPosition()-(((RunningGame) cerita.getSubCerita(1)).getPlayer().getWidth()/2)+(((RunningGame) cerita.getSubCerita(1)).getPlayer().getWidth()/5), 
				30,
				((RunningGame) cerita.getSubCerita(1)).getPlayer().getWidth()*(2/5), 
				((RunningGame) cerita.getSubCerita(1)).getPlayer().getHeight()*(4/5)));
		
		
		((TapGame) cerita.getSubCerita(6)).setBackground(new 
				Texture(Gdx.files.internal("selatbali_tapgame/bg.png")));
		((TapGame) cerita.getSubCerita(6)).setPanelBackground(new 
				Texture(Gdx.files.internal("siparkit_tapgame/panel_bg.png")));
		((TapGame) cerita.getSubCerita(6)).setTargetsTexture(new Texture[]{
				new Texture(Gdx.files.internal("selatbali_tapgame/target1.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1_bad.png")),
		});
		((TapGame) cerita.getSubCerita(6)).setTargetsPressedTexture(new Texture[]{
				new Texture(Gdx.files.internal("selatbali_tapgame/target1.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/target1_bad.png")),
		});
		((TapGame) cerita.getSubCerita(6)).setIndicators(new Texture[]{
				new Texture(Gdx.files.internal("selatbali_tapgame/senang.png")),
				new Texture(Gdx.files.internal("selatbali_tapgame/sedih.png")),
		});
		
		
		((TapGame) cerita.getSubCerita(6)).setButtons(new TapGameButton[]{
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
		
		InitKuis.initKuis((Kuis) cerita.getSubCerita(10), 
				Gdx.files.internal("kuis/selatbali.txt"));
	}
	
}
