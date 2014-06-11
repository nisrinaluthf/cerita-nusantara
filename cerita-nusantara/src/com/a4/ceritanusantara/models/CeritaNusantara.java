package com.a4.ceritanusantara.models;

import java.io.IOException;
import java.util.StringTokenizer;

import com.a4.ceritanusantara.utils.InitSubCerita;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * Kelas <code>CeritaNusantara</code> merepresentasikan kumpulan cerita-cerita
 * yang ada dalam aplikasi ini. 
 * 
 */

public class CeritaNusantara {
	
	public static final int SUMATERA = 0;
	public static final int KALIMANTAN = 1;
	public static final int JAWA = 2;
	public static final int BALI = 3;
	
	Cerita[] cerita;
	
	public CeritaNusantara(){
		cerita = new Cerita[]{
				new Cerita("Sumatera", "Parkit Si Raja Parakeet"),
				new Cerita("Kalimantan", "Asal Mula Pulau Nusa"),
				new Cerita("Jawa", "Aji Saka, Asal Mula Huruf Jawa"),
				new Cerita("Bali", "Asal Mula Selat Bali")
		};
		try{
			initCerita();
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	public Cerita getCerita(int indeks){
		return cerita[indeks];
	}
	
	private void initCerita() throws IOException{
		
		FileHandle file = Gdx.files.internal("data/datacerita");
		String data = file.readString();
		StringTokenizer st = new StringTokenizer(data,  " ");
		
		boolean sumateraUnlocked = false, kalimantanUnlocked = false, 
				jawaUnlocked = false, baliUnlocked = false;
		if(st.nextToken().equals("unlocked")) sumateraUnlocked = true;
		if(st.nextToken().equals("unlocked")) kalimantanUnlocked = true;
		if(st.nextToken().equals("unlocked")) jawaUnlocked = true;
		if(st.nextToken().equals("unlocked")) baliUnlocked = true;
		
		cerita[SUMATERA].setUnlocked(sumateraUnlocked);
		cerita[SUMATERA].setSubCerita(new SubCerita[]{
				new Adegan("Adegan 1", SubCerita.ADEGAN),
				new Adegan("Adegan 2", SubCerita.ADEGAN),
				new Adegan("Adegan 3", SubCerita.ADEGAN),
				new TapGame("Permainan 1", SubCerita.TAP_GAME),
				new Adegan("Adegan 4", SubCerita.ADEGAN),
				new Adegan("Adegan 5", SubCerita.ADEGAN),
				new Adegan("Adegan 6", SubCerita.ADEGAN),
				new Adegan("Adegan 7", SubCerita.ADEGAN),
				new Labirin("Permainan 2", SubCerita.LABIRIN),
				new Adegan("Adegan 8", SubCerita.ADEGAN),
				new Kuis("Kuis", SubCerita.KUIS)
		});
		
		for(int i=0; i<cerita[SUMATERA].getSubCerita().length; i++){
			cerita[SUMATERA].getSubCerita(i).setAsalCerita(SUMATERA);
			cerita[SUMATERA].getSubCerita(i).setIndex(i);
		}
		
		
		InitSubCerita.initSumatera(cerita[SUMATERA]);
		
		int len = cerita[SUMATERA].getSubCerita().length;
		for(int i=0; i<len; i++){
			if(i<len-1){
				cerita[SUMATERA].getSubCerita(i).setNext(cerita[SUMATERA].getSubCerita(i+1));
			}
			if(i>0){
				cerita[SUMATERA].getSubCerita(i).setPrev(cerita[SUMATERA].getSubCerita(i-1));
			}
		}
		
		cerita[KALIMANTAN].setUnlocked(kalimantanUnlocked);
		cerita[KALIMANTAN].setSubCerita(new SubCerita[]{
				new Adegan("Adegan 1", SubCerita.ADEGAN),
				new Adegan("Adegan 2", SubCerita.ADEGAN),
				new Labirin("Permainan 1", SubCerita.LABIRIN),
				new Adegan("Adegan 3", SubCerita.ADEGAN),
				new Adegan("Adegan 4", SubCerita.ADEGAN),
				new Adegan("Adegan 5", SubCerita.ADEGAN),
				new Adegan("Adegan 6", SubCerita.ADEGAN),
				new TapGame("Permainan 2", SubCerita.TAP_GAME),
				new Adegan("Adegan 7", SubCerita.ADEGAN),
				new Kuis("Kuis", SubCerita.KUIS)
		});
		
		for(int i=0; i<cerita[KALIMANTAN].getSubCerita().length; i++){
			cerita[KALIMANTAN].getSubCerita(i).setAsalCerita(KALIMANTAN);
			cerita[KALIMANTAN].getSubCerita(i).setIndex(i);
		}
		
		InitSubCerita.initKalimantan(cerita[KALIMANTAN]);
		
		len = cerita[KALIMANTAN].getSubCerita().length;
		for(int i=0; i<len; i++){
			if(i<len-1){
				cerita[KALIMANTAN].getSubCerita(i).setNext(cerita[KALIMANTAN].getSubCerita(i+1));
			}
			if(i>0){
				cerita[KALIMANTAN].getSubCerita(i).setPrev(cerita[KALIMANTAN].getSubCerita(i-1));
			}
		}
		
		cerita[JAWA].setUnlocked(jawaUnlocked);
		cerita[JAWA].setSubCerita(new SubCerita[]{
				new Adegan("Adegan 1", SubCerita.ADEGAN),
				new Adegan("Adegan 2", SubCerita.ADEGAN),
				new Labirin("Permainan 1", SubCerita.LABIRIN),
				new Adegan("Adegan 3", SubCerita.ADEGAN),
				new Adegan("Adegan 4", SubCerita.ADEGAN),
				new Adegan("Adegan 5", SubCerita.ADEGAN),
				new Adegan("Adegan 6", SubCerita.ADEGAN),
				new Adegan("Adegan 7", SubCerita.ADEGAN),
				new Adegan("Adegan 8", SubCerita.ADEGAN),
				new Puzzle("Permainan 2", SubCerita.PUZZLE),
				new Adegan("Adegan 9", SubCerita.ADEGAN),
				new Kuis("Kuis", SubCerita.KUIS)
		});
		
		for(int i=0; i<cerita[JAWA].getSubCerita().length; i++){
			cerita[JAWA].getSubCerita(i).setAsalCerita(JAWA);
			cerita[JAWA].getSubCerita(i).setIndex(i);
		}
		
		InitSubCerita.initJawa(cerita[JAWA]);
		
		len = cerita[JAWA].getSubCerita().length;
		for(int i=0; i<len; i++){
			if(i<len-1){
				cerita[JAWA].getSubCerita(i).setNext(cerita[JAWA].getSubCerita(i+1));
			}
			if(i>0){
				cerita[JAWA].getSubCerita(i).setPrev(cerita[JAWA].getSubCerita(i-1));
			}
		}
		
		cerita[BALI].setUnlocked(baliUnlocked);
		cerita[BALI].setSubCerita(new SubCerita[]{
				new Adegan("Adegan 1", SubCerita.ADEGAN),
				new RunningGame("Permainan 1", SubCerita.RUNNING_GAME),
				new Adegan("Adegan 2", SubCerita.ADEGAN),
				new Adegan("Adegan 3", SubCerita.ADEGAN),
				new Adegan("Adegan 4", SubCerita.ADEGAN),
				new Adegan("Adegan 5", SubCerita.ADEGAN),
				new TapGame("Permainan 2", SubCerita.TAP_GAME),
				new Adegan("Adegan 6", SubCerita.ADEGAN),
				new Adegan("Adegan 7", SubCerita.ADEGAN),
				new Adegan("Adegan 8", SubCerita.ADEGAN),
				new Kuis("Kuis", SubCerita.KUIS)
		});
		
		for(int i=0; i<cerita[BALI].getSubCerita().length; i++){
			cerita[BALI].getSubCerita(i).setAsalCerita(BALI);
			cerita[BALI].getSubCerita(i).setIndex(i);
		}
		
		InitSubCerita.initBali(cerita[BALI]);
		
		len = cerita[BALI].getSubCerita().length;
		for(int i=0; i<len; i++){
			if(i<len-1){
				cerita[BALI].getSubCerita(i).setNext(cerita[BALI].getSubCerita(i+1));
			}
			if(i>0){
				cerita[BALI].getSubCerita(i).setPrev(cerita[BALI].getSubCerita(i-1));
			}
		}
		
		Texture[] sumateraIcons = new Texture[cerita[SUMATERA].getSubCerita().length];
		Texture[] kalimantanIcons = new Texture[cerita[KALIMANTAN].getSubCerita().length];
		Texture[] jawaIcons = new Texture[cerita[JAWA].getSubCerita().length];
		Texture[] baliIcons = new Texture[cerita[BALI].getSubCerita().length];
		
		for(int i=0; i<sumateraIcons.length; i++){
			sumateraIcons[i] = new Texture(
					Gdx.files.internal("select_scene/sumatera/"+(i+1)+".png"));
		}
		
		for(int i=0; i<kalimantanIcons.length; i++){
			kalimantanIcons[i] = new Texture(
					Gdx.files.internal("select_scene/kalimantan/"+(i+1)+".png"));
		}
		
		for(int i=0; i<jawaIcons.length; i++){
			jawaIcons[i] = new Texture(
					Gdx.files.internal("select_scene/jawa/"+(i+1)+".png"));
		}
		
		for(int i=0; i<baliIcons.length; i++){
			baliIcons[i] = new Texture(
					Gdx.files.internal("select_scene/bali/"+(i+1)+".png"));
		}
		
		cerita[SUMATERA].setSubCeritaIcons(sumateraIcons);
		cerita[KALIMANTAN].setSubCeritaIcons(kalimantanIcons);
		cerita[JAWA].setSubCeritaIcons(jawaIcons);
		cerita[BALI].setSubCeritaIcons(baliIcons);
		
		cerita[SUMATERA].setBgSelectScene(new Texture(
				Gdx.files.internal("select_scene/select_scene_sumatera.jpg")));
		cerita[KALIMANTAN].setBgSelectScene(new Texture(
				Gdx.files.internal("select_scene/select_scene_kalimantan.jpg")));
		cerita[JAWA].setBgSelectScene(new Texture(
				Gdx.files.internal("select_scene/select_scene_jawa.jpg")));
		cerita[BALI].setBgSelectScene(new Texture(
				Gdx.files.internal("select_scene/select_scene_bali.jpg")));
	}
}
