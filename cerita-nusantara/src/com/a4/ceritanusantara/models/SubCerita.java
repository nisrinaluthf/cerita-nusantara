package com.a4.ceritanusantara.models;

import com.badlogic.gdx.utils.Json;

public class SubCerita {
	
	public static final int ADEGAN = 0;
	public static final int LABIRIN = 1;
	public static final int TAP_GAME = 2;
	public static final int PUZZLE = 3;
	public static final int RUNNING_GAME = 4;
	public static final int KUIS = 5;
	

	private String nama;
	private int tipe;
	private boolean unlocked;
	
	public SubCerita(String nama, int tipe){
		this.nama = nama;
		this.tipe = tipe;
		
		Json json = new Json();
		
	}
	
	
	public String getNama(){
		return nama;
	}
	
	public int getTipe(){
		return tipe;
	}
	
	public boolean isUnlocked(){
		return unlocked;
	}
}
