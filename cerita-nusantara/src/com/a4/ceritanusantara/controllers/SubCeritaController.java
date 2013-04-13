package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.views.SettingsScreen;
import com.a4.ceritanusantara.views.SubCeritaScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class SubCeritaController {
	private Aplikasi app;
	
	private SubCeritaScreen screen;
	private Rectangle pauseButtonBounds;
	private Preferences prefs;

	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public SubCeritaController(SubCeritaScreen screen){
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = screen.getAplikasi();
		pauseButtonBounds = this.screen.getPauseButtonBounds();
		prefs = Gdx.app.getPreferences("preferences");
		System.out.println("sub cerita controller di create");
		cam = this.screen.getCam();
		viewport = this.screen.getViewport();

	}
	
	public void processInput(){
		//System.out.println("masuk setting controller process input");
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			//System.out.println(soundButtonBounds+" x: "+Gdx.input.getX()/screen.ppuX+" y: "+(screen.height-Gdx.input.getY())/screen.ppuY);
			if(OverlapTester.pointInRectangle( pauseButtonBounds,pos.x, pos.y)){
				screen.setPauseButtonPressed(true);
				
			}
		}
			
			if(Gdx.input.isTouched()){
				//kosongin dulu deh~
			}
			else{
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				if(screen.pauseButtonIsPressed()){
					screen.setPauseButtonPressed(false);
					if(OverlapTester.pointInRectangle( pauseButtonBounds, 
							pos.x, pos.y)){
						screen.setStatus(screen.PAUSED);
						app.setScreen(new PauseScreen(app));
						
					}
				}
				
			}
			
			

		}

}
