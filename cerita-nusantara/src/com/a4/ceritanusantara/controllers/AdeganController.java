package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AdeganScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.badlogic.gdx.Gdx;
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
	
	public AdeganController(AdeganScreen screen) {
		// TODO Auto-generated constructor stub
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = screen.getAplikasi();
		previousButtonBounds = this.screen.getPreviousButtonBounds();
		nextButtonBounds = this.screen.getNextButtonBounds();
		pauseButtonBounds = this.screen.getPauseButtonBounds();
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
			else if(OverlapTester.pointInRectangle( previousButtonBounds,pos.x, pos.y)){
				screen.setPreviousButtonPressed(true);	
			}
			else if(OverlapTester.pointInRectangle( nextButtonBounds,pos.x, pos.y)){
				screen.setNextButtonPressed(true);	
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
					else if(OverlapTester.pointInRectangle( previousButtonBounds, 
							pos.x, pos.y)){
						//screen.setAdegan();
						
					}
					else if(OverlapTester.pointInRectangle( nextButtonBounds, 
							pos.x, pos.y)){
						
					}
				}
				
			}
			
			

		}

}
