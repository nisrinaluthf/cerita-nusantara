package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AboutScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PilihCeritaScreen;
import com.a4.ceritanusantara.views.SettingsScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>MainMenuController</code> adalah controller yang memproses input
 * untuk screen menu utama.
 */
public class MainMenuController {
	
	private Aplikasi app;
	private MainMenuScreen screen;
	private Rectangle playButtonBounds;
	private Rectangle settingsButtonBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;

	private Rectangle aboutButtonBounds;
	
	public MainMenuController(MainMenuScreen screen){
		Gdx.input.setCatchBackKey(false);
		this.screen = screen;
		app = screen.getAplikasi();
		playButtonBounds = screen.getPlayButtonBounds();
		settingsButtonBounds = screen.getSettingsButtonBounds();
		aboutButtonBounds = screen.getAboutButtonBounds();
		cam = screen.getCam();
		viewport = screen.getViewport();
		
	}
	
	
	public void processInput(){
		
		
		if(Gdx.input.justTouched()){
			
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle( playButtonBounds, pos.x, pos.y)){
				screen.stopMusic();
				screen.playSoundFx();
				screen.setPlayButtonPressed(true);
				
			}
			
			else if(OverlapTester.pointInRectangle( settingsButtonBounds, 
					pos.x, pos.y)){
				screen.stopMusic();
				screen.playSoundFx();
				screen.setSettingsButtonPressed(true);
				
			}
			
			else if(OverlapTester.pointInRectangle( aboutButtonBounds, 
					pos.x, pos.y)){
				screen.stopMusic();
				screen.playSoundFx();
				screen.setAboutButtonPressed(true);
				
			}
		}
		
		if(Gdx.input.isTouched()){
			//kosongin dulu deh~
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.playButtonIsPressed()){
				screen.setPlayButtonPressed(false);
				if(OverlapTester.pointInRectangle( playButtonBounds, pos.x, pos.y)){
					//screen.stopMusic();
					app.getScreen().dispose();
					app.setScreen(new PilihCeritaScreen(app));
					
				}
			}
			
			else if(screen.settingsButtonIsPressed()){
				screen.setSettingsButtonPressed(false);
				if(OverlapTester.pointInRectangle( settingsButtonBounds, pos.x, pos.y)){
					app.getScreen().dispose();
					app.setScreen(new SettingsScreen(app, screen));
					
				}
			}
			
			else if(screen.aboutButtonIsPressed()){
				screen.setAboutButtonPressed(false);
				if(OverlapTester.pointInRectangle( aboutButtonBounds, pos.x, pos.y)){
					app.getScreen().dispose();
					app.setScreen(new AboutScreen(app));
					
				}
			}
		}
	}
	
}
