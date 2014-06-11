package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.Puzzle;
import com.a4.ceritanusantara.models.RunningGame;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AdeganScreen;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.LabirinScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.views.RunningGameScreen;
import com.a4.ceritanusantara.views.SettingsScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.a4.ceritanusantara.views.PuzzleScreen;
import com.a4.ceritanusantara.views.TapGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>PauseController</code> adalah controller yang memproses input
 * untuk screen pause yang bisa diakses dari subcerita.
 */
public class PauseController {
  private Aplikasi app;
	private PauseScreen screen;
	private Rectangle resumeButtonBounds;
	private Rectangle restartButtonBounds;
	private Rectangle settingButtonBounds;
	private Rectangle exitButtonBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public PauseController(PauseScreen screen){
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		app = screen.getAplikasi();
		resumeButtonBounds = screen.getResumeButtonBounds();
		restartButtonBounds = screen.getRestartButtonBounds();
		exitButtonBounds = screen.getExitButtonBounds();
		settingButtonBounds = screen.getSettingButtonBounds();
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
			
			else if(OverlapTester.pointInRectangle(restartButtonBounds, 
					pos.x, pos.y)){
				screen.stopMusic();
				screen.playSoundFx();
				screen.setRestartButtonPressed(true);
				
			}
			
			else if(OverlapTester.pointInRectangle(exitButtonBounds, 
					pos.x, pos.y)){
				screen.stopMusic();
				screen.playSoundFx();
				screen.setExitButtonPressed(true);
				
			}
					
			else if(OverlapTester.pointInRectangle(settingButtonBounds, 
					pos.x, pos.y)){
				screen.playSoundFx();
				screen.pauseMusic();
				screen.setSettingButtonPressed(true);
				
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
					screen.stopMusic();
					app.getScreen().dispose();
					app.setScreen(screen.getOriginScreen());
					// ganti state game jadi main lagi
				}
			}
			
			else if(screen.restartButtonIsPressed()){
				screen.setRestartButtonPressed(false);
				if(OverlapTester.pointInRectangle( restartButtonBounds, pos.x, pos.y)){
					int type = screen.getType();
					if(type==SubCerita.ADEGAN){
						app.setScreen(new AdeganScreen(app, (Adegan)(screen.getSubCerita())));
					}
					else if(type==SubCerita.TAP_GAME){
						app.setScreen(new TapGameScreen(app, (TapGame)(screen.getSubCerita())));
					}
					else if(type==SubCerita.LABIRIN){
						app.setScreen(new LabirinScreen(app, (Labirin)(screen.getSubCerita())));
					}
					else if(type==SubCerita.PUZZLE){
						app.setScreen(new PuzzleScreen(app, (Puzzle)(screen.getSubCerita())));
					}
					else if(type==SubCerita.KUIS){
						app.setScreen(new KuisScreen(app, (Kuis)(screen.getSubCerita())));
					}
					
					else if(type==SubCerita.RUNNING_GAME){
						app.setScreen(new RunningGameScreen(app, (RunningGame)(screen.getSubCerita())));
					}

					
				}
			}
			
			else if(screen.exitButtonIsPressed()){
				screen.setExitButtonPressed(false);
				if(OverlapTester.pointInRectangle( exitButtonBounds, pos.x, pos.y)){
					screen.stopMusic();
					app.getScreen().dispose();
					app.setScreen(new PilihSubCeritaScreen(app,
							app.getCeritaNusantara().getCerita(screen.getSubCerita().getAsalCerita())));
					
				}
			}
			
			else if(screen.settingButtonIsPressed()){
				screen.setSettingButtonPressed(false);
				if(OverlapTester.pointInRectangle( settingButtonBounds, pos.x, pos.y)){
					screen.pause();
					app.setScreen(new SettingsScreen(app, screen));
				}
			}
		}
		
		
	}	
	
}
