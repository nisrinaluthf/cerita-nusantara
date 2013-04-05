package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PilihCeritaScreen;
import com.a4.ceritanusantara.views.SettingsScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuController {
	
	/* 
	 * Dia harus megang Aplikasi juga soalnya yang bisa 
	 * mindahin dari satu screen ke screen lain cuma 
	 * Aplikasi
	 */
	private Aplikasi app;
	
	/* 
	 * ambil screen yang dia control, terus ambil bounds2 dari
	 * screen itu (biar kalo manggil ngga pake get-get lagi).
	 * 
	 */
	private MainMenuScreen screen;
	private Rectangle playButtonBounds;
	private Rectangle settingsButtonBounds;
	
	
	/*
	 * Constructor
	 */
	public MainMenuController(MainMenuScreen screen){
		this.screen = screen;
		app = screen.getAplikasi();
		playButtonBounds = screen.getPlayButtonBounds();
		settingsButtonBounds = screen.getSettingsButtonBounds();
		
	}
	
	
	public void processInput(){
		
		if(Gdx.input.justTouched()){
			
			if(OverlapTester.pointInRectangle( playButtonBounds, 
					Gdx.input.getX()/screen.ppuX, (screen.height-Gdx.input.getY())/screen.ppuY)){
				screen.setPlayButtonPressed(true);
				
			}
			
			else if(OverlapTester.pointInRectangle( settingsButtonBounds, 
					Gdx.input.getX()/screen.ppuX, (screen.height-Gdx.input.getY())/screen.ppuY)){
				screen.setSettingsButtonPressed(true);
				
			}
		}
		
		if(Gdx.input.isTouched()){
			//kosongin dulu deh~
		}
		else{
			if(screen.playButtonIsPressed()){
				screen.setPlayButtonPressed(false);
				if(OverlapTester.pointInRectangle( playButtonBounds, 
						Gdx.input.getX()/screen.ppuX, (screen.height-Gdx.input.getY())/screen.ppuY)){
					app.setScreen(new PilihCeritaScreen(app, screen.width, screen.height));
					
				}
			}
			
			else if(screen.settingsButtonIsPressed()){
				screen.setSettingsButtonPressed(false);
				if(OverlapTester.pointInRectangle( settingsButtonBounds, 
						Gdx.input.getX()/screen.ppuX, (screen.height-Gdx.input.getY())/screen.ppuY)){
					app.setScreen(new SettingsScreen(app, screen.width, screen.height));
					
				}
			}
		}
	}
	
}
