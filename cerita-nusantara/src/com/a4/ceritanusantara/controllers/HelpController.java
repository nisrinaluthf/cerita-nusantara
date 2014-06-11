package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.HelpScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>HelpController</code> adalah controller yang memproses input
 * untuk screen help yang bisa diakses dari permainan.
 */
public class HelpController {
  private Aplikasi app;
	private HelpScreen screen;
	private Rectangle resumeButtonBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public HelpController(HelpScreen screen){
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		app = screen.getAplikasi();
		resumeButtonBounds = screen.getResumeButtonBounds();
		cam = screen.getCam();
		viewport = screen.getViewport();
	}
	
public void processInput(){
		
	if (Gdx.input.isKeyPressed(Keys.BACK)){
		screen.setResumeButtonPressed(false);
		screen.getOriginScreen().resume();
		screen.stopMusic();
		app.getScreen().dispose();
		app.setScreen(screen.getOriginScreen());
	}
		
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
			
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.resumeButtonIsPressed()){
				screen.setResumeButtonPressed(false);
				if(OverlapTester.pointInRectangle( resumeButtonBounds, pos.x, pos.y)){
					screen.getOriginScreen().resume();
					screen.stopMusic();
					app.getScreen().dispose();
					app.setScreen(screen.getOriginScreen());
				}
			}		
		}
	}	
}