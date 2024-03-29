package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PilihCeritaScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>PilihCeritaController</code> adalah controller yang memproses input
 * untuk screen untuk memilih cerita.
 */
	public class PilihCeritaController {
	
	private Aplikasi app;
	
	private PilihCeritaScreen screen;
	private Rectangle[] sumateraBounds;
	private Rectangle[] kalimantanBounds;
	private Rectangle[] jawaBounds;
	private Rectangle[] baliBounds;
	
	private Rectangle backButtonBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public PilihCeritaController(PilihCeritaScreen screen){
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		app = screen.getAplikasi();
		sumateraBounds = screen.getSumateraBounds();
		kalimantanBounds = screen.getKalimantanBounds();
		jawaBounds = screen.getJawaBounds();
		baliBounds = screen.getBaliBounds();
		
		backButtonBounds = this.screen.getBackButtonBounds();
		cam = screen.getCam();
		viewport = screen.getViewport();
		
	}
	
	public void processInput(){
		
		
		if(Gdx.input.justTouched()){
			
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			for(int i=0; i<sumateraBounds.length; i++){
				if(OverlapTester.pointInRectangle( sumateraBounds[i], pos.x, pos.y)){
					screen.playSoundFx("pilihcerita");
					screen.setSumateraPressed(true);
				}
			}
			
			for(int i=0; i<kalimantanBounds.length; i++){
				if(OverlapTester.pointInRectangle( kalimantanBounds[i], pos.x, pos.y)){
					screen.playSoundFx("pilihcerita");
					screen.setKalimantanPressed(true);
				}
			}
			
			for(int i=0; i<jawaBounds.length; i++){
				if(OverlapTester.pointInRectangle( jawaBounds[i], pos.x, pos.y)){
					screen.playSoundFx("pilihcerita");
					screen.setJawaPressed(true);
				}
			}
			
			for(int i=0; i<baliBounds.length; i++){
				if(OverlapTester.pointInRectangle( baliBounds[i], pos.x, pos.y)){
					screen.playSoundFx("pilihcerita");
					screen.setBaliPressed(true);
				}
			}
			if(OverlapTester.pointInRectangle( backButtonBounds,pos.x, pos.y)){
				screen.playSoundFx("back");
				screen.setBackButtonPressed(true);
			}
		}
		
		if(Gdx.input.isTouched()){
			//kosongin dulu deh~
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.sumateraIsPressed()){
				screen.setSumateraPressed(false);
				for(int i=0; i<sumateraBounds.length; i++){
					if(OverlapTester.pointInRectangle( sumateraBounds[i], pos.x, pos.y)){
						screen.stopMusic();
						app.getScreen().dispose();
						app.setScreen(new PilihSubCeritaScreen(app, 
								app.getCeritaNusantara().getCerita(CeritaNusantara.SUMATERA)));
						
					}
				}
			}
			
			if(screen.kalimantanIsPressed()){
				screen.setKalimantanPressed(false);
				for(int i=0; i<kalimantanBounds.length; i++){
					if(OverlapTester.pointInRectangle( kalimantanBounds[i], pos.x, pos.y)){
						screen.stopMusic();
						app.getScreen().dispose();
						app.setScreen(new PilihSubCeritaScreen(app, 
								app.getCeritaNusantara().getCerita(CeritaNusantara.KALIMANTAN)));
						
					}
				}
			}
			
			if(screen.jawaIsPressed()){
				screen.setJawaPressed(false);
				for(int i=0; i<jawaBounds.length; i++){
					if(OverlapTester.pointInRectangle( jawaBounds[i], pos.x, pos.y)){
						screen.stopMusic();
						app.getScreen().dispose();
						app.setScreen(new PilihSubCeritaScreen(app, 
								app.getCeritaNusantara().getCerita(CeritaNusantara.JAWA)));
						
					}
				}
			}
			
			if(screen.baliIsPressed()){
				screen.setBaliPressed(false);
				for(int i=0; i<baliBounds.length; i++){
					if(OverlapTester.pointInRectangle( baliBounds[i], pos.x, pos.y)){
						screen.stopMusic();
						app.getScreen().dispose();
						app.setScreen(new PilihSubCeritaScreen(app, 
								app.getCeritaNusantara().getCerita(CeritaNusantara.BALI)));
						
					}
				}
			}
			
			if(screen.backButtonIsPressed()){
				screen.setBackButtonPressed(false);
				if(OverlapTester.pointInRectangle( backButtonBounds, pos.x, pos.y)){
					//app.setScreen(new SettingsScreen(app, screen.width, screen.height));
					screen.stopMusic();
					app.getScreen().dispose();
					app.setScreen(new MainMenuScreen(app));
				}
			}
			
		}
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			app.getScreen().dispose();
			app.setScreen(new MainMenuScreen(app));
		}
	}
}
