package com.a4.ceritanusantara.models;

public class CeritaNusantara {
	
	public static final int SUMATERA = 1;
	public static final int KALIMANTAN = 2;
	public static final int JAWA = 3;
	public static final int BALI = 4;
	
	
	Cerita[] cerita;
	
	public CeritaNusantara(){
		cerita = new Cerita[]{
				null,
				null,
				null,
				null
		};
	}
	
	public Cerita getCerita(int indeks){
		return cerita[indeks];
	}
}
