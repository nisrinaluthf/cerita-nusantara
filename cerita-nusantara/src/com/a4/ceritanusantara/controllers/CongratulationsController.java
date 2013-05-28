package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.CongratulationsScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class CongratulationsController {
	
	CongratulationsScreen screen;
	private OrthographicCamera cam;
	private Rectangle viewport;
	private Rectangle nextBounds;
	private Aplikasi app;
	private Cerita cerita;

	public CongratulationsController(CongratulationsScreen screen, Cerita cerita) {
		this.screen = screen;
		this.cerita = cerita;
		app = screen.getAplikasi();
		cam = screen.getCam();
		viewport = screen.getViewport();
		nextBounds = screen.getNextButtonBounds();
	}
	
	public void processInput(float delta){
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle(nextBounds, 
					pos.x, pos.y)){
				//screen.playSoundFx("default");
				//screen.stopMusic();
				screen.setNextButtonPressed(true);
			}
		}
			
		if(!Gdx.input.isTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.nextButtonIsPressed()){
				screen.setNextButtonPressed(false);
				if(OverlapTester.pointInRectangle(nextBounds, 
						pos.x, pos.y)){
					app.getScreen().dispose();
					app.setScreen(
							new PilihSubCeritaScreen(app, cerita));
				}
			}
		}
		
	}
	
}
