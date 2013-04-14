package com.a4.ceritanusantara.models;

import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

import com.a4.ceritanusantara.utils.InitSubCerita;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

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
				null,
				null
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
		
		FileHandle file = Gdx.files.local("datacerita");
		String data = file.readString();
		StringTokenizer st = new StringTokenizer(data,  " ");
		
		boolean sumateraUnlocked = false, kalimantanUnlocked = false;
		if(st.nextToken().equals("unlocked")) sumateraUnlocked = true;
		if(st.nextToken().equals("unlocked")) kalimantanUnlocked = true;
		
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
		
		InitSubCerita.initSumatera(cerita[SUMATERA]);
		
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
		
		InitSubCerita.initKalimantan(cerita[KALIMANTAN]);
		
		
		
		
		
	}
}
