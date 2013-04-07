package com.a4.ceritanusantara.models;

import com.a4.ceritanusantara.utils.InitCerita;

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
		
		initCeritaSumatera();
	}
	
	public Cerita getCerita(int indeks){
		return cerita[indeks];
	}
	
	private void initCeritaSumatera(){
		
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
				new Kuis("Kuis", SubCerita.KUIS)
		});
		
		InitCerita.initSumatera(cerita[SUMATERA]);
		
		
		
		
		
	}
}
