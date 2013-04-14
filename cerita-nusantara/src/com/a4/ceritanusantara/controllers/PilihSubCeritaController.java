package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.KuisScreen;
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
	
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	
	public PilihSubCeritaController(PilihSubCeritaScreen screen, SubCerita[] subcerita){
		
		Gdx.input.setCatchBackKey(true);
		
		this.screen = screen;
		this.subcerita = subcerita;
		app = screen.getAplikasi();
		subCeritaButtonBounds = screen.getSubCeritaButtonBounds();
		
		cam = screen.getCam();
		viewport = screen.getViewport();
	}
	
	public void processInput(){
		
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			
			app.setScreen(new PilihCeritaScreen(app));
		}
		
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			for(int i=0; i<subCeritaButtonBounds.length; i++){
				if(OverlapTester.pointInRectangle(subCeritaButtonBounds[i], pos.x, pos.y)){
					if(subcerita[i].isUnlocked()){
						screen.setSubCeritaButtonPressed(i, true);
					}
				}
			}
			
			
		}
		
		if(!Gdx.input.isTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			for(int i=0; i<subCeritaButtonBounds.length; i++){
				if(screen.isSubCeritaButtonPressed(i)){
					screen.setSubCeritaButtonPressed(i, false);
					if(OverlapTester.pointInRectangle(subCeritaButtonBounds[i], pos.x, pos.y)){
						if(screen.getSubCerita(i).getTipe()==SubCerita.KUIS){
							app.setScreen(new KuisScreen(app, (Kuis)(screen.getSubCerita(i))));
						}
						if(screen.getSubCerita(i).getTipe()==SubCerita.TAP_GAME){
							app.setScreen(new TapGameScreen(app, (TapGame)(screen.getSubCerita(i))));
						}
						
						
					}
				}
			}
		}
	}
	
}
