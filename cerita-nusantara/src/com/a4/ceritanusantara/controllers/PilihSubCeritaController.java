package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.LabirinScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PilihCeritaScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.a4.ceritanusantara.views.TapGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class PilihSubCeritaController {
	
	private Aplikasi app;
	private SubCerita[] subcerita;
	private PilihSubCeritaScreen screen;
	
	private Rectangle[] subCeritaButtonBounds;

	private Rectangle backButtonBounds;
	
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	
	public PilihSubCeritaController(PilihSubCeritaScreen screen, SubCerita[] subcerita){
		
		Gdx.input.setCatchBackKey(true);
		
		this.screen = screen;
		this.subcerita = subcerita;
		app = screen.getAplikasi();
		subCeritaButtonBounds = screen.getSubCeritaButtonBounds();
		backButtonBounds = this.screen.getBackButtonBounds();
		
		cam = screen.getCam();
		viewport = screen.getViewport();
	}
	
	public void processInput(){
		
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			screen.playSoundFx("back");
			app.setScreen(new PilihCeritaScreen(app));
		}
		
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			for(int i=0; i<subCeritaButtonBounds.length; i++){
				if(OverlapTester.pointInRectangle(subCeritaButtonBounds[i], pos.x, pos.y)){
					if(subcerita[i].isUnlocked()){
						screen.playSoundFx("pilihsubcerita");
						screen.stopMusic();
						screen.setSubCeritaButtonPressed(i, true);
					}
				}
			}
			if(OverlapTester.pointInRectangle( backButtonBounds,pos.x, pos.y)){
				screen.playSoundFx("back");
				screen.stopMusic();
				screen.setBackButtonPressed(true);
				System.out.println("back to pilih cerita");
			}
			
		}
		
		if(!Gdx.input.isTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			for(int i=0; i<subCeritaButtonBounds.length; i++){
				if(screen.isSubCeritaButtonPressed(i)){
					screen.setSubCeritaButtonPressed(i, false);
					//screen.stopMusic();
					if(OverlapTester.pointInRectangle(subCeritaButtonBounds[i], pos.x, pos.y)){
						
						app.getScreen().dispose();
						
						if(screen.getSubCerita(i).getTipe()==SubCerita.KUIS){
							app.setScreen(new KuisScreen(app, (Kuis)(screen.getSubCerita(i))));
						}
						if(screen.getSubCerita(i).getTipe()==SubCerita.LABIRIN){
							app.setScreen(new LabirinScreen(app, (Labirin)(screen.getSubCerita(i))));
						}
						if(screen.getSubCerita(i).getTipe()==SubCerita.TAP_GAME){
							app.setScreen(new TapGameScreen(app, (TapGame)(screen.getSubCerita(i))));
						}
						
						
					}
				}
			}
			if(screen.backButtonIsPressed()){
				screen.setBackButtonPressed(false);
				if(OverlapTester.pointInRectangle( backButtonBounds, pos.x, pos.y)){
					//app.setScreen(new SettingsScreen(app, screen.width, screen.height));
					//screen.stopMusic();
					app.getScreen().dispose();
					app.setScreen(new PilihCeritaScreen(app));
				}
				System.out.println("back diklik");
			}
		}
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			//screen.stopMusic();
			app.getScreen().dispose();
			
			app.setScreen(new PilihCeritaScreen(app));
		}
	}
	
}
