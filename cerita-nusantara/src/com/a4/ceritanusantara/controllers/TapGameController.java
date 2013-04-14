package com.a4.ceritanusantara.controllers;

import java.util.Iterator;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.TapGameButton;
import com.a4.ceritanusantara.models.TapGameTarget;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.views.PilihCeritaScreen;
import com.a4.ceritanusantara.views.TapGameScreen;
import com.a4.ceritanusantara.views.WinLoseScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class TapGameController {

	private Aplikasi app;
	private TapGameScreen screen;
	private TapGame tapGame;
	
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	private TapGameButton[] buttons;
	private Rectangle[] buttonsBounds;
	private Rectangle pauseButtonBounds;

	public TapGameController(TapGameScreen screen, Aplikasi app, TapGame tapGame) {
		// TODO Auto-generated constructor stub
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = app;
		this.tapGame = tapGame;
		this.cam = screen.getCam();
		this.viewport = screen.getViewport();
		this.buttons = tapGame.getButtons();
		this.buttonsBounds = new Rectangle[buttons.length];
		for(int i=0; i<buttons.length; i++){
			buttonsBounds[i] = buttons[i].getBounds();
		}
		
		pauseButtonBounds = screen.getPauseButtonBounds();
		System.out.println(pauseButtonBounds.x+" "+pauseButtonBounds.y);
				
	}

	public void processInput() {
		// TODO Auto-generated method stub
		// Kalo back pause screen muncul
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			screen.pause();
			app.setScreen(new PauseScreen(app, screen, tapGame));
		}
		
		if(tapGame.getHits()<=0){
			app.setScreen(new WinLoseScreen(app, false, -1));
		}
		if(tapGame.getHits()>=25){
			int score = 50;
			if(tapGame.getBadHits()<=50){
				score = 100 - tapGame.getBadHits();
			}
			app.setScreen(new WinLoseScreen(app, true, score));
		}
		
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			System.out.println(pauseButtonBounds.x+" "+pauseButtonBounds.y);
			if(OverlapTester.pointInRectangle(pauseButtonBounds, pos.x, pos.y)){
				screen.setPauseButtonPressed(true);	
			}
			
			for(int i=0; i<buttonsBounds.length; i++){
				if(OverlapTester.pointInRectangle(buttonsBounds[i], pos.x, pos.y)){
					buttons[i].setPressed(true);
					Iterator<TapGameTarget> itr = tapGame.getTargets().iterator();
					try{
						while(itr.hasNext()){
							TapGameTarget target = itr.next();
							if(OverlapTester.pointInRectangle(target.getBounds(), pos.x, pos.y) 
									&& target.getIndex()==i){
								target.setPressed(true);
								if(target.isBad()){
									tapGame.setHits(tapGame.getHits()-1);
									tapGame.addBadHit();
								}
								else {
									tapGame.setHits(tapGame.getHits()+1);
								}
							}
						}
					}
					catch(Exception exc){
						System.out.println("error dari tapgamecontroller");	
					}
				}
			}
			
		}
		
		if(Gdx.input.isTouched()){
			
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.pauseButtonIsPressed()){
				screen.setPauseButtonPressed(false);
				if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
					screen.pause();
					app.setScreen(new PauseScreen(app, screen, tapGame));
					
				}
			}
			
			for(int i=0; i<buttonsBounds.length; i++){
				if(buttons[i].isPressed()){
					buttons[i].setPressed(false);	
				}
			}
		}
		
	}

}
