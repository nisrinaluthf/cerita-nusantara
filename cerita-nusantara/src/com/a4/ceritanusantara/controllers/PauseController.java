package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.LabirinScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.views.TapGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class PauseController {
  private Aplikasi app;
	private PauseScreen screen;
	private Rectangle resumeButtonBounds;
	private Rectangle restartButtonBounds;
	private Rectangle exitButtonBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public PauseController(PauseScreen screen){
		this.screen = screen;
		app = screen.getAplikasi();
		resumeButtonBounds = screen.getResumeButtonBounds();
		restartButtonBounds = screen.getRestartButtonBounds();
		exitButtonBounds = screen.getExitButtonBounds();
		cam = screen.getCam();
		viewport = screen.getViewport();
		
	}
	
public void processInput(){
		
		
		if(Gdx.input.justTouched()){
			
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			System.out.printf("%f %f %f %f%n", viewport.x, viewport.y, viewport.width, viewport.height);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle( resumeButtonBounds, pos.x, pos.y)){
				screen.setResumeButtonPressed(true);
				
			}
			
			else if(OverlapTester.pointInRectangle( restartButtonBounds, 
					pos.x, pos.y)){
				screen.setRestartButtonPressed(true);
				
			}
			
			else if(OverlapTester.pointInRectangle( exitButtonBounds, 
					pos.x, pos.y)){
				screen.setExitButtonPressed(true);
				
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
			
			else if(screen.restartButtonIsPressed()){
				screen.setRestartButtonPressed(false);
				if(OverlapTester.pointInRectangle( restartButtonBounds, pos.x, pos.y)){
					int type = screen.getType();
					if(type==SubCerita.ADEGAN){
						//set screen ke adegan
					}
					else if(type==SubCerita.TAP_GAME){
						app.setScreen(new TapGameScreen(app, (TapGame)(screen.getSubCerita())));
					}
					else if(type==SubCerita.LABIRIN){
						//app.setScreen(new LabirinScreen(app, (Labirin)(screen.getSubCerita())));
					}
					else if(type==SubCerita.KUIS){
						app.setScreen(new TapGameScreen(app, (TapGame)(screen.getSubCerita())));
					}
					
					//app.setScreen(new SettingsScreen(app));
					// ke awal subcerita
				}
			}
			
			else if(screen.exitButtonIsPressed()){
				screen.setExitButtonPressed(false);
				if(OverlapTester.pointInRectangle( exitButtonBounds, pos.x, pos.y)){
					app.setScreen(new MainMenuScreen(app));
					
				}
			}
		}
	}
	
	
}
