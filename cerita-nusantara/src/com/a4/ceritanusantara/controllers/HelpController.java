package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.HelpScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class HelpController {
  private Aplikasi app;
	private HelpScreen screen;
	private Rectangle resumeButtonBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public HelpController(HelpScreen screen){
		this.screen = screen;
		app = screen.getAplikasi();
		resumeButtonBounds = screen.getResumeButtonBounds();
		cam = screen.getCam();
		viewport = screen.getViewport();
		
	}
	
public void processInput(){
		
		
		if(Gdx.input.justTouched()){
			
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle(resumeButtonBounds, pos.x, pos.y)){
				screen.stopMusic();
				screen.playSoundFx();
				screen.setResumeButtonPressed(true);
				
			}
			
			
		}
		
		if(Gdx.input.isTouched()){
			//kosongin dulu deh~
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.resumeButtonIsPressed()){
				screen.setResumeButtonPressed(false);
				if(OverlapTester.pointInRectangle( resumeButtonBounds, pos.x, pos.y)){
					screen.getOriginScreen().resume();
					app.setScreen(screen.getOriginScreen());
					// ganti state game jadi main lagi
				}
			}
			
			
		}
	}
	
	
}
