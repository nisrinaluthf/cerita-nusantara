package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AbstractScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.SettingsScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>SettingsController</code> adalah controller yang memproses input
 * untuk screen pengaturan suara dan sound FX.
 */
public class SettingsController {
	
	private Aplikasi app;
	
	/* 
	 * ambil screen yang dia control, terus ambil bounds2 dari
	 * screen itu (biar kalo manggil ngga pake get-get lagi).
	 * 
	 */
	private SettingsScreen screen;
	private Rectangle soundButtonBounds;
	private Rectangle musicButtonBounds;
	private Rectangle backButtonBounds;
	private Preferences prefs;

	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public SettingsController(SettingsScreen screen){
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = screen.getAplikasi();
		soundButtonBounds = this.screen.getSoundButtonBounds();
		musicButtonBounds = this.screen.getMusicButtonBounds();
		backButtonBounds = this.screen.getBackButtonBounds();
		prefs = Gdx.app.getPreferences("preferences");
		cam = this.screen.getCam();
		viewport = this.screen.getViewport();

	}
	
	public void processInput(){
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle( soundButtonBounds,pos.x, pos.y)){
				
				screen.setSoundButtonPressed(true);
	
			}
			
			else if(OverlapTester.pointInRectangle( musicButtonBounds,pos.x, pos.y)){
				//screen.playSoundFx();
				screen.setMusicButtonPressed(true);
			}
			
			else if(OverlapTester.pointInRectangle( backButtonBounds,pos.x, pos.y)){
				screen.stopMusic();
				screen.playSoundFx();
				screen.setBackButtonPressed(true);
			}
		}
		
		if(Gdx.input.isTouched()){
			//kosongin dulu deh~
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			if(screen.soundButtonIsPressed()){
				screen.setSoundButtonPressed(false);
				if(OverlapTester.pointInRectangle( soundButtonBounds, 
						pos.x, pos.y)){
					//app.setScreen(new PilihCeritaScreen(app, screen.width, screen.height));
					
					//screen.updateMusic();
					prefs.putBoolean("soundOn", !prefs.getBoolean("soundOn"));
					screen.playSoundFx();
				}
			}
			
			else if(screen.musicButtonIsPressed()){
				screen.setMusicButtonPressed(false);
				if(OverlapTester.pointInRectangle( musicButtonBounds, pos.x, pos.y)){
					//app.setScreen(new SettingsScreen(app, screen.width, screen.height));

					screen.updateMusic();
					prefs.putBoolean("musicOn", !prefs.getBoolean("musicOn"));
					screen.playSoundFx();
				}
			}
			
			else if(screen.backButtonIsPressed()){
				screen.setBackButtonPressed(false);
				if(OverlapTester.pointInRectangle( backButtonBounds, pos.x, pos.y)){
					//app.setScreen(new SettingsScreen(app, screen.width, screen.height));
					app.getScreen().dispose();
					Screen ori = screen.getOriginScreen();
					if(((AbstractScreen) ori).getScreenType() == 0) {
						//app.getScreen().dispose();
						app.setScreen(new MainMenuScreen(app));
					} 
					else {
						screen.getOriginScreen().resume();
						//app.getScreen().dispose();
						app.setScreen(screen.getOriginScreen());
					}

				}
			}
		}
		prefs.flush();
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			Screen ori = screen.getOriginScreen();
					if(((AbstractScreen) ori).getScreenType() == 0) {
						app.getScreen().dispose();
						app.setScreen(new MainMenuScreen(app));
					} else {
						screen.getOriginScreen().resume();
						app.getScreen().dispose();
						app.setScreen(screen.getOriginScreen());
					}

		}

	}

	public Preferences getSettings() {
		return prefs;
	}
}
