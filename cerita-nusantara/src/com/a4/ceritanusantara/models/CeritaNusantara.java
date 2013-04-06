package com.a4.ceritanusantara.models;

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
		
		SubCerita[] tmp = new SubCerita[10];
		
		tmp[0] = new SubCerita("Adegan 1", SubCerita.ADEGAN);
		tmp[1] = new SubCerita("Adegan 2", SubCerita.ADEGAN);
		tmp[2] = new SubCerita("Adegan 3", SubCerita.ADEGAN);
		tmp[3] = new SubCerita("Permainan 1", SubCerita.TAP_GAME);
		tmp[4] = new SubCerita("Adegan 4", SubCerita.ADEGAN);
		tmp[5] = new SubCerita("Adegan 5", SubCerita.ADEGAN);
		tmp[6] = new SubCerita("Adegan 6", SubCerita.ADEGAN);
		tmp[7] = new SubCerita("Adegan 7", SubCerita.ADEGAN);
		tmp[8] = new SubCerita("Permainan 2", SubCerita.LABIRIN);
		tmp[9] = new SubCerita("Kuis", SubCerita.KUIS);
		
		cerita[0].setSubCerita(tmp);
		
	}
}
