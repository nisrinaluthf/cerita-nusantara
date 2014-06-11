package com.a4.ceritanusantara.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Kelas <code>Cerita</code> merepresentasikan suatu cerita
 * yang ada dalam aplikasi ini. 
 * 
 */

public class Cerita {
	private String namaDaerah;
	private String namaCerita;
	private Texture[] subCeritaIcons;
	private boolean unlocked;
	private SubCerita[] subcerita;
	private Texture bgSelectScene;
	
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
	
	public void setSubCeritaIcons(String[] paths){
		subCeritaIcons = new Texture[paths.length];
		for(int i=0; i<subCeritaIcons.length; i++){
			subCeritaIcons[i] = new Texture(
					Gdx.files.internal(paths[i]));
		}
	}
	
	public Texture[] getSubCeritaIcons(){
		return subCeritaIcons;
	}

	public Texture getBgSelectScene() {
		return bgSelectScene;
	}

	public void setBgSelectScene(Texture bgSelectScene) {
		this.bgSelectScene = bgSelectScene;
	}
	
	public void dispose() {
		this.bgSelectScene.dispose();
		for(int idx = 0; idx < this.subCeritaIcons.length; idx++) {
			this.subCeritaIcons[idx].dispose();
		}
	}
}
