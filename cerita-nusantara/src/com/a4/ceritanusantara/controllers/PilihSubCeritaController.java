package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.views.PilihCeritaScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PilihSubCeritaController {
	
	Aplikasi app;
	
	public PilihSubCeritaController(Aplikasi app){
		this.app = app;
		Gdx.input.setCatchBackKey(true);
	}
	
	public void processInput(){
		
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			
			app.setScreen(new PilihCeritaScreen(app));
		}
	}
	
}
