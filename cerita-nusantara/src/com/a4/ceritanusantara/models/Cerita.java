package com.a4.ceritanusantara.models;

import com.badlogic.gdx.graphics.Texture;

public class Cerita {
	private String namaDaerah;
	private String namaCerita;
	private Texture[] subCeritaIcons;
	private boolean unlocked;
	private SubCerita[] subcerita;
	
	public Cerita(String namaDaerah, String namaCerita){
		this.namaDaerah = namaDaerah;
		this.namaCerita = namaCerita;
	}
	
	public String getNamaDaerah(){
		return namaDaerah;
	}
	
	public String getNamaCerita(){
		return namaCerita;
	}
	
	public void setUnlocked(boolean unlocked){
		this.unlocked = unlocked;
	}
	
	public boolean isUnlocked(){
		return unlocked;
	}
	
	public SubCerita[] getSubCerita(){
		return subcerita;
	}
	
	public SubCerita getSubCerita(int i){
		return subcerita[i];
	}
	
	public void setSubCerita(SubCerita[] subcerita){
		this.subcerita = subcerita;
	}
	
	public void setSubCeritaIcons(Texture[] icons){
		subCeritaIcons = icons;
	}
	
	public Texture[] getSubCeritaIcons(){
		return subCeritaIcons;
	}
	
}
