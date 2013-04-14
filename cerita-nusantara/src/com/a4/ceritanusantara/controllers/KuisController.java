package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.views.WinLoseScreen;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class KuisController {
	
	private Aplikasi app;
	
	private KuisScreen screen;
	private Kuis kuis;
	
	private Rectangle pauseButtonBounds;
	private Rectangle[] optionsBounds;
	private KuisQuestion kuisQuestion;
	
	private OrthographicCamera cam;
	private Rectangle viewport;

	public KuisController(KuisScreen screen) {
		// TODO Auto-generated constructor stub
		this.screen = screen;
		app = screen.getAplikasi();
		kuis = screen.getKuis();
		kuisQuestion = kuis.getKuisQuestion(0);
		
		pauseButtonBounds = screen.getPauseButtonBounds();
		System.out.printf("x: %f, y: %f, w: %f, l: %f%n", pauseButtonBounds.x,
				pauseButtonBounds.y, pauseButtonBounds.width, pauseButtonBounds.height);
		optionsBounds = kuisQuestion.getBounds();
		
		cam = screen.getCam();
		viewport = screen.getViewport();
		
	}
	
	public void processInput(float delta){
		
		kuis.timeLeft-=delta;
		
		if(kuis.timeLeft<0){
			if(kuis.getCurrentNo()<4){
				kuis.timeLeft=21.0f;
				kuis.setCurrentNo(kuis.getCurrentNo()+1);
			}
			else{
				//kuis.setScore()
				app.setScreen(new WinLoseScreen(app, true, kuis.getScore()));
			}
		}
		
		kuisQuestion = kuis.getKuisQuestion(kuis.getCurrentNo());
		
		optionsBounds = kuisQuestion.getBounds();
		
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
				screen.setPauseButtonPressed(true);	
			}
			
			for(int i=0; i<optionsBounds.length; i++){
				if(OverlapTester.pointInRectangle(optionsBounds[i], pos.x, pos.y)){
					kuisQuestion.setOptionPressed(i, true);
					System.out.println(i);
				}
			}
		}
		
		if(!Gdx.input.isTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			if(screen.pauseButtonIsPressed()){
				screen.setPauseButtonPressed(false);
				if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
					screen.pause();
					app.setScreen(new PauseScreen(app, screen, kuis));
					
				}
			}
			
			
			for(int i=0; i<4; i++){
				if(kuisQuestion.getOptionPressed(i)){
					kuisQuestion.setOptionPressed(i, false);
					if(OverlapTester.pointInRectangle(optionsBounds[i], pos.x, pos.y)){
						
						//kalo bener
						if(kuisQuestion.getAnswer()==i){
							
							kuis.setScore(kuis.getScore()+20);
						}
						
						if(kuis.getCurrentNo()<4){
							
							System.out.printf("answer = %d, harusnya %d%n", i, kuisQuestion.getAnswer());
							
							kuis.timeLeft=21.0f;
							kuis.setCurrentNo(kuis.getCurrentNo()+1);
						}
						else{
							app.setScreen(new WinLoseScreen(app, true, kuis.getScore()));
						}
					}
				}
			}
		}
		
	}
	
}
