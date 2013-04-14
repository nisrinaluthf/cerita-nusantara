package com.a4.ceritanusantara.utils;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.a4.ceritanusantara.models.TapGameButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class InitSubCerita {
	
	public static void initSumatera(Cerita cerita){
		
		/*
		((Labirin) cerita.getSubCerita(8)).setWallTexture(new 
				Texture(Gdx.files.internal("backgrounds/sumatera_labirin_wall.png")));
		((Labirin) cerita.getSubCerita(8)).setPlayerTexture(new 
				Texture(Gdx.files.internal("backgrounds/sumatera_labirin_player.png")));
		*/
		
		FileHandle file = Gdx.files.local("datasumatera");
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
		
		((Kuis) cerita.getSubCerita(10)).setQuestions(new KuisQuestion[]{
				new KuisQuestion("Apa strategi raja parkit agar terbebas dari tangkapan si pemburu?", 
						"A. berpura-pura tidur", 
						"B. berusaha melepas perekat", 
						"C. berpura-pura mati", 
						"D. meronat meminta tolong", 
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
						"B. karena si Parkit ingin bebas dan kembali ke hutan",
						"C. karena si Parkit tidak suka pada Raja",
						"D. karena si Parkit dibujuk oleh teman-temannya", 
						0),
				new KuisQuestion("Apa rencana si Parkit agar bisa terbebas dari sangkar sang Raja?",
						"a. berpura-pura tidur",
						"b. memohon pada Raja agar dilepaskan",
						"c. memberontak dalam sangkar",
						"d. berpura-pura mati",
						3)
		});
	}
	
	
	
	public static void initKalimantan(Cerita cerita){
		
		((TapGame) cerita.getSubCerita(7)).setBackground(new 
				Texture(Gdx.files.internal("nusa-tapgame/bg.png")));
		((TapGame) cerita.getSubCerita(7)).setPanelBackground(new 
				Texture(Gdx.files.internal("parkit-tapgame/panel_bg.png")));
		((TapGame) cerita.getSubCerita(7)).setTargetsTexture(new Texture[]{
				new Texture(Gdx.files.internal("parkit-tapgame/target1.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target2.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target3.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target1_bad.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target2_bad.png")),
				new Texture(Gdx.files.internal("parkit-tapgame/target3_bad.png")),
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
