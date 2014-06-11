package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.utils.ActionResolver;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.CongratulationsScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>CongratulationsController</code> adalah controller yang memproses 
 * input untuk screen yang ditampilkan setelah pemain selesai menyelesaikan suatu
 * cerita.
 */
public class CongratulationsController {
	
	CongratulationsScreen screen;
	private OrthographicCamera cam;
	private Rectangle viewport;
	private Rectangle nextBounds;
	private Aplikasi app;
	private ActionResolver actionResolver;
	private Cerita cerita;
	private Rectangle facebookBounds;
	int score;

	public CongratulationsController(CongratulationsScreen screen, Cerita cerita, int score) {
		this.score = score;
		this.screen = screen;
		this.cerita = cerita;
		app = screen.getAplikasi();
		actionResolver = app.getActionResolver();
		cam = screen.getCam();
		viewport = screen.getViewport();
		nextBounds = screen.getNextButtonBounds();
		facebookBounds = screen.getShareButtonBounds();
	}
	
	public void processInput(float delta){
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle(nextBounds, 
					pos.x, pos.y)){
				screen.setNextButtonPressed(true);
			}
			else if(OverlapTester.pointInRectangle(facebookBounds, 
					pos.x, pos.y)){
				screen.setShareButtonPressed(true);
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
			else if(screen.shareButtonIsPressed()){
				screen.setShareButtonPressed(false);
				if(OverlapTester.pointInRectangle(facebookBounds, 
						pos.x, pos.y)){
					if(Gdx.app.getType()==ApplicationType.Android) {
						app.getScreen().dispose();
						actionResolver.shareToFacebook("Aku baru menyelesaikan cerita "
								+cerita.getNamaCerita()+" dengan skor "+ score+"!");
					}
					
				}		
			}
		}	
	}
}
