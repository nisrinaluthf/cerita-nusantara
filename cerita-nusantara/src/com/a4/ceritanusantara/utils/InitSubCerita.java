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
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.a4.ceritanusantara.models.TapGameButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class InitSubCerita {
	
	public static void initSumatera(Cerita cerita){
		
		
		
		FileHandle file = Gdx.files.internal("data/datasumatera");
		
		FileHandle localFile = Gdx.files.local("datasumatera");
		
		String data = file.readString();
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
				Texture(Gdx.files.internal("parkit-tapgame/bg.png")));
		((TapGame) cerita.getSubCerita(3)).setPanelBackground(new 
				Texture(Gdx.files.internal("parkit-tapgame/panel_bg.png")));
		((TapGame) cerita.getSubCerita(3)).setTargetsTexture(new Texture[]{
				new Texture(Gdx.files.internal("parkit-tapgame/target1.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target2.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target3.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target2_bad.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target3_bad.png")),
		});
		((TapGame) cerita.getSubCerita(3)).setIndicators(new Texture[]{
				new Texture(Gdx.files.internal("parkit-tapgame/pemburu_senang.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/pemburu_sedih.png")),
		});
		
		
		((TapGame) cerita.getSubCerita(3)).setButtons(new TapGameButton[]{
				new TapGameButton(1, new Vector2(270, 20),
						new Texture(Gdx.files.internal("parkit-tapgame/button1.png")),
						new Texture(Gdx.files.internal("parkit-tapgame/button1_pressed.png"))),
				new TapGameButton(2, new Vector2(445, 20),
						new Texture(Gdx.files.internal("parkit-tapgame/button2.png")),
						new Texture(Gdx.files.internal("parkit-tapgame/button2_pressed.png"))),
				new TapGameButton(3, new Vector2(630, 20),
						new Texture(Gdx.files.internal("parkit-tapgame/button3.png")),
						new Texture(Gdx.files.internal("parkit-tapgame/button3_pressed.png")))
		});
		
		
		List<LabirinWall> walls = new ArrayList<LabirinWall>();
		List<LabirinItem> items = new ArrayList<LabirinItem>();
		
		file = Gdx.files.internal("labirin/labirin_siparkit.txt");
		data = file.readString();
		st = new StringTokenizer(data, System.getProperty("line.separator"));
		
		
		LabirinPlayer player = null;
		Rectangle finish = null;
		int start = 137;
		i=0;
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
		
		((Labirin) cerita.getSubCerita(8)).setWalls(walls.toArray(new LabirinWall[0]));
		((Labirin) cerita.getSubCerita(8)).setItems(items.toArray(new LabirinItem[0]));
		((Labirin) cerita.getSubCerita(8)).setPlayer(player);
		((Labirin) cerita.getSubCerita(8)).setFinishBounds(finish);
		
		((Labirin) cerita.getSubCerita(8)).setBackground(new
				Texture(Gdx.files.internal("parkit-labirin/background.png")));
		((Labirin) cerita.getSubCerita(8)).setPlayerTexture(new Texture[]{
				new Texture(Gdx.files.internal("parkit-labirin/siparkit_up.png")),
				new Texture(Gdx.files.internal("parkit-labirin/siparkit_right.png")),
				new Texture(Gdx.files.internal("parkit-labirin/siparkit_down.png")),
				new Texture(Gdx.files.internal("parkit-labirin/siparkit_left.png")),
		});
		((Labirin) cerita.getSubCerita(8)).setWallTexture(new
				Texture(Gdx.files.internal("parkit-labirin/wall.png")));
		((Labirin) cerita.getSubCerita(8)).setItemTexture(new
				Texture(Gdx.files.internal("nusa-labirin/item.png")));
		
		
		((Kuis) cerita.getSubCerita(10)).setQuestions(new KuisQuestion[]{
				new KuisQuestion("Apa strategi raja parkit agar terbebas dari tangkapan si pemburu?", 
						"A. berpura-pura tidur", 
						"B. berusaha melepas perekat", 
						"C. berpura-pura mati", 
						"D. meronta meminta tolong", 
						2),
				new KuisQuestion("Mengapa kawanan parakeet terbang seketika tanpa menunggu hitungan dari si parkit?", 
						"A. karena kaget si pemburu terpeleset",
						"B. karena si pemburu mendekat",
						"C. karena merasa terancam",
						"D. karena takut pada si pemburu", 
						0),
				new KuisQuestion("Apa yang ditawarkan oleh si parkit agar tidak dibunuh oleh pemburu?", 
						"A. membantu pemburu setiap hari",
						"B. memberikan bulunya untuk pemburu",
						"C. bernyanyi untuk si pemburu setiap hari",
						"D. memberi uang pada pemburu", 
						2),
				new KuisQuestion("Mengapa si Parkit lama-kelamaan enggan bernyanyi untuk sang Raja?", 
						"A. karena si Parkit kelaparan",
						"B. karena si Parkit ingin kembali ke hutan",
						"C. karena si Parkit tidak suka pada Raja",
						"D. karena si Parkit dibujuk oleh temannya", 
						1),
				new KuisQuestion("Apa rencana si Parkit agar bisa terbebas dari sangkar sang Raja?",
						"a. berpura-pura tidur",
						"b. memohon pada Raja agar dilepaskan",
						"c. memberontak dalam sangkar",
						"d. berpura-pura mati",
						3)
		});
	}
	
	
	
	public static void initKalimantan(Cerita cerita){
		
		FileHandle file = Gdx.files.internal("data/datakalimantan");
		String data = file.readString();
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
		
		
		List<LabirinWall> walls = new ArrayList<LabirinWall>();
		List<LabirinItem> items = new ArrayList<LabirinItem>();
		
		file = Gdx.files.internal("labirin/labirin_nusa.txt");
		data = file.readString();
		st = new StringTokenizer(data, System.getProperty("line.separator"));
		
		
		LabirinPlayer player = null;
		Rectangle finish = null;
		int start = 137;
		i=0;
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
		
		((Labirin) cerita.getSubCerita(2)).setWalls(walls.toArray(new LabirinWall[0]));
		((Labirin) cerita.getSubCerita(2)).setItems(items.toArray(new LabirinItem[0]));
		((Labirin) cerita.getSubCerita(2)).setPlayer(player);
		((Labirin) cerita.getSubCerita(2)).setFinishBounds(finish);
		
		((Labirin) cerita.getSubCerita(2)).setBackground(new
				Texture(Gdx.files.internal("nusa-labirin/background.png")));
		((Labirin) cerita.getSubCerita(2)).setPlayerTexture(new Texture[]{
				new Texture(Gdx.files.internal("nusa-labirin/nusa_up.png")),
				new Texture(Gdx.files.internal("nusa-labirin/nusa_right.png")),
				new Texture(Gdx.files.internal("nusa-labirin/nusa_down.png")),
				new Texture(Gdx.files.internal("nusa-labirin/nusa_left.png")),
		});
		((Labirin) cerita.getSubCerita(2)).setWallTexture(new
				Texture(Gdx.files.internal("nusa-labirin/wall.png")));
		((Labirin) cerita.getSubCerita(2)).setItemTexture(new
				Texture(Gdx.files.internal("nusa-labirin/item.png")));
		
		((TapGame) cerita.getSubCerita(7)).setBackground(new 
				Texture(Gdx.files.internal("nusa-tapgame/bg.png")));
		((TapGame) cerita.getSubCerita(7)).setPanelBackground(new 
				Texture(Gdx.files.internal("parkit-tapgame/panel_bg.png")));
		((TapGame) cerita.getSubCerita(7)).setTargetsTexture(new Texture[]{
				new Texture(Gdx.files.internal("nusa-tapgame/target1.png")),
				new Texture(Gdx.files.internal("nusa-tapgame/target1.png")),
				new Texture(Gdx.files.internal("nusa-tapgame/target1.png")),
				new Texture(Gdx.files.internal("nusa-tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("nusa-tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("nusa-tapgame/target1_bad.png")),
		});
		((TapGame) cerita.getSubCerita(7)).setIndicators(new Texture[]{
				new Texture(Gdx.files.internal("nusa-tapgame/nusa_senang.png")),
				new Texture(Gdx.files.internal("nusa-tapgame/nusa_sedih.png")),
		});
		
		((TapGame) cerita.getSubCerita(7)).setButtons(new TapGameButton[]{
				new TapGameButton(1, new Vector2(270, 20),
						new Texture(Gdx.files.internal("parkit-tapgame/button1.png")),
						new Texture(Gdx.files.internal("parkit-tapgame/button1_pressed.png"))),
				new TapGameButton(2, new Vector2(445, 20),
						new Texture(Gdx.files.internal("parkit-tapgame/button2.png")),
						new Texture(Gdx.files.internal("parkit-tapgame/button2_pressed.png"))),
				new TapGameButton(3, new Vector2(630, 20),
						new Texture(Gdx.files.internal("parkit-tapgame/button3.png")),
						new Texture(Gdx.files.internal("parkit-tapgame/button3_pressed.png")))
		});
		
		((Kuis) cerita.getSubCerita(9)).setQuestions(new KuisQuestion[]{
				new KuisQuestion("Apa pekerjaan Nusa sehari-hari?", 
						"A. berdagang",
						"B. bercocok tanam dan menangkap ikan",
						"C. berburu dan memanah",
						"D. menanam sayur dan buah",
						1),
				new KuisQuestion("Telur apa yang dimakan Nusa?", 
						"A. telur angsa",
						"B. telur dinosaurus",
						"C. telur naga",
						"D. telur elang", 
						2 ),
				new KuisQuestion("Apa yang terjadi pada Nusa setelah memakan telur tersebut?", 
						"a. tubuh nusa dipenuhi bintil-bintil merah dan gatal",
						"b. tubuh nusa memerah kemudian terbakar",
						"c. nusa menderita sakit keras",
						"d. tubuh nusa terasa panas dari leher sampai kaki",
						0),
				new KuisQuestion("Apa yang diminta Nusa kepada warga ketika ia tidak kuat menahan terik matahari?", 
						"A. mengambilkan air minum",
						"B. mengambilkan buah segar untuk dimakan",
						"C. mendorong tubuhnya ke sungai",
						"D. menyiram tubuhnya dengan air",
						2),
				new KuisQuestion("Siapa yang bercerita bahwa ada seekor naga besar yang ingin mengadu kekuatan dengan Nusa?",
						"A. ikan saluang",
						"B. ikan teri",
						"C. ikan salmon",
						"D. ikan tengiri",
						0)
		});
	}
}
