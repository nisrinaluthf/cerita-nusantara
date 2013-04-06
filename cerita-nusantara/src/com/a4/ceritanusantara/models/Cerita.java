package com.a4.ceritanusantara.models;

public class Cerita {
	String namaDaerah;
	String namaCerita;
	boolean unlocked;
	SubCerita[] subcerita;
	
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
	
	public void setSubCerita(SubCerita[] subcerita){
		this.subcerita = subcerita;
	}
	
}
