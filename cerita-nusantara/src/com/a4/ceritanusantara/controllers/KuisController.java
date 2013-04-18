package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.CongratulationsScreen;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	
	private OrthographicCamera cam;
	private Rectangle viewport;

	public KuisController(KuisScreen screen) {
		// TODO Auto-generated constructor stub
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		app = screen.getAplikasi();
		kuis = screen.getKuis();
		kuisQuestion = kuis.getKuisQuestion(0);
		
		pauseButtonBounds = screen.getPauseButtonBounds();
		optionsBounds = kuisQuestion.getBounds();
		
		replayBounds = screen.getReplayBounds();
		mainMenuBounds = screen.getMainMenuBounds();
		nextBounds = screen.getNextBounds();
		
		cam = screen.getCam();
		viewport = screen.getViewport();
		
	}
	
	public void processInput(float delta){
		
		if(kuis.isGameOver()){
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle(replayBounds, pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.stopMusic();
					screen.setReplayButtonPressed(true);
				}
				
				else if(OverlapTester.pointInRectangle(mainMenuBounds, 
						pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.stopMusic();
					screen.setMainMenuButtonPressed(true);
				}
				
				else if(OverlapTester.pointInRectangle(nextBounds, 
						pos.x, pos.y)&&kuis.getScore()>60){
					screen.playSoundFx("default");
					screen.stopMusic();
					screen.setNextButtonPressed(true);
					
				}
			}
			
			if(!Gdx.input.isTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				if(screen.replayButtonIsPressed()){
					screen.setReplayButtonPressed(false);
					if(OverlapTester.pointInRectangle(replayBounds, pos.x, pos.y)){
						app.getScreen().dispose();
						app.setScreen(new KuisScreen(app, kuis));
					}
				}
				
				else if(screen.mainMenuButtonIsPressed()){
					screen.setMainMenuButtonPressed(false);
					if(OverlapTester.pointInRectangle(mainMenuBounds, 
							pos.x, pos.y)){
						app.getScreen().dispose();
						app.setScreen(new MainMenuScreen(app));
					}
				}
				
				
				else if(screen.nextButtonIsPressed()){
					screen.setNextButtonPressed(false);
					if(OverlapTester.pointInRectangle(nextBounds, 
							pos.x, pos.y)){
						//app.getScreen().dispose();
						//app.setScreen(new CongratulationsScreen(app));
					}
				}
			}
		}
		else{
		
			if (Gdx.input.isKeyPressed(Keys.BACK)){
				screen.pause();
				screen.playSoundFx("default");
				screen.pauseMusic();
				//app.getScreen().dispose();
				app.setScreen(new PauseScreen(app, screen, kuis));
			}
			
			kuis.timeLeft-=delta;
			
			if(kuis.timeLeft<0){
				if(kuis.getCurrentNo()<4){
					kuis.timeLeft=21.0f;
					kuis.setCurrentNo(kuis.getCurrentNo()+1);
				}
				else{
					//kuis.setScore()
					kuis.gameOver();
				}
			}
			
			kuisQuestion = kuis.getKuisQuestion(kuis.getCurrentNo());
			
			optionsBounds = kuisQuestion.getBounds();
			
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.pauseMusic();
					screen.setPauseButtonPressed(true);	
				}
				
				for(int i=0; i<optionsBounds.length; i++){
					if(OverlapTester.pointInRectangle(optionsBounds[i], pos.x, pos.y)){
						kuisQuestion.setOptionPressed(i, true);
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
								screen.playSoundFx("true");
								kuis.setScore(kuis.getScore()+20);
							} else {
								screen.playSoundFx("false");
							}
							
							if(kuis.getCurrentNo()<4){
								kuis.timeLeft=21.0f;
								kuis.setCurrentNo(kuis.getCurrentNo()+1);
							}
							else{
								kuis.gameOver();
								//app.setScreen(new WinLoseScreen(app, true, kuis.getScore()));
							}
						}
					}
				}
			}
		}
	}
	
}
