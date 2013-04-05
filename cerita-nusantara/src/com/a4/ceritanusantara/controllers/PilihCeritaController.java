package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.PilihCeritaScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

	

	public class PilihCeritaController {
	
	private Aplikasi app;
	
	private PilihCeritaScreen screen;
	private Rectangle[] sumateraBounds;
	private Rectangle[] kalimantanBounds;
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public PilihCeritaController(PilihCeritaScreen screen){
		this.screen = screen;
		app = screen.getAplikasi();
		sumateraBounds = screen.getSumateraBounds();
		kalimantanBounds = screen.getKalimantanBounds();
		cam = screen.getCam();
		viewport = screen.getViewport();
		
	}
	
	public void processInput(){
		if(Gdx.input.justTouched()){
			
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			for(int i=0; i<sumateraBounds.length; i++){
				if(OverlapTester.pointInRectangle( sumateraBounds[i], pos.x, pos.y)){
					screen.setSumateraPressed(true);
				}
			}
			
			for(int i=0; i<kalimantanBounds.length; i++){
				if(OverlapTester.pointInRectangle( kalimantanBounds[i], pos.x, pos.y)){
					screen.setKalimantanPressed(true);
				}
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
						app.setScreen(new PilihSubCeritaScreen(app, 
								app.getCeritaNusantara().getCerita(CeritaNusantara.SUMATERA)));
						
					}
				}
			}
			
			if(screen.kalimantanIsPressed()){
				screen.setKalimantanPressed(false);
				for(int i=0; i<kalimantanBounds.length; i++){
					if(OverlapTester.pointInRectangle( kalimantanBounds[i], pos.x, pos.y)){
						app.setScreen(new PilihSubCeritaScreen(app, 
								app.getCeritaNusantara().getCerita(CeritaNusantara.KALIMANTAN)));
						
					}
				}
			}
			
			
		}
	}
}
