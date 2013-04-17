package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AdeganScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class AdeganController {

	private Aplikasi app;
	
	private AdeganScreen screen;
	private Rectangle previousButtonBounds;
	private Rectangle nextButtonBounds;
	private Rectangle pauseButtonBounds;

	private OrthographicCamera cam;
	private Rectangle viewport;

	private Adegan adegan;
	
	public AdeganController(AdeganScreen screen) {
		// TODO Auto-generated constructor stub
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = screen.getAplikasi();
		this.adegan = screen.getAdegan();
		
		previousButtonBounds = this.screen.getPreviousButtonBounds();
		nextButtonBounds = this.screen.getNextButtonBounds();
		
		pauseButtonBounds = this.screen.getPauseButtonBounds();
		
		cam = this.screen.getCam();
		viewport = this.screen.getViewport();

	}
	
	public void processInput(float delta){
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			app.setScreen(new PauseScreen(app, screen, adegan));
		}
		
		adegan.time+=delta;
		
		if(adegan.time>(adegan.getAdeganText(adegan.getCurrentText())).getEnd()){
			if(adegan.getCurrentText()<adegan.getLength()-1){
				adegan.updateCurrentText();
			}
			else{
				adegan.done();
			}
			
		}
		
		if(adegan.isDone()){
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle(nextButtonBounds,pos.x, pos.y)){
					screen.setNextButtonPressed(true);	
				}
				else if(OverlapTester.pointInRectangle(previousButtonBounds,pos.x, pos.y)){
					screen.setPreviousButtonPressed(true);	
				}
				
			}
		}
		else{
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle( pauseButtonBounds,pos.x, pos.y)){
					screen.setPauseButtonPressed(true);	
				}
				
			}
			if(!Gdx.input.isTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(screen.pauseButtonIsPressed()){
					screen.setPauseButtonPressed(false);
					if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
						screen.pause();
						app.setScreen(new PauseScreen(app, screen, adegan));
						
					}
				}
			}
		}
		
	}

}
