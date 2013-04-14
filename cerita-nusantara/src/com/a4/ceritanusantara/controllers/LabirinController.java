package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.LabirinWall;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.CongratulationsScreen;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.LabirinScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class LabirinController {
	
private Aplikasi app;
	
	private LabirinScreen screen;
	private Labirin labirin;
	
	private Rectangle pauseButtonBounds;
	
	private LabirinWall[] walls;
	private Vector2[] wallsPos;
	private Rectangle[] wallsBounds;
	private KuisQuestion kuisQuestion;
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	
	private OrthographicCamera cam;
	private Rectangle viewport;

	public LabirinController(LabirinScreen labirinScreen, Aplikasi app,
			Labirin labirin) {
		// TODO Auto-generated constructor stub
		
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		app = screen.getAplikasi();
		labirin = screen.getLabirin();
		
		pauseButtonBounds = screen.getPauseButtonBounds();
		walls = labirin.getWalls();
		
		for(int i=0; i<walls.length; i++){
			wallsPos[i] = walls[i].getPos();
			wallsBounds[i] = walls[i].getBounds();
			}
		
		replayBounds = screen.getReplayBounds();
		mainMenuBounds = screen.getMainMenuBounds();
		nextBounds = screen.getNextBounds();
		
		cam = screen.getCam();
		viewport = screen.getViewport();
	}
	
	public void processInput(float delta){
		if(labirin.isGameOver()){
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle(replayBounds, pos.x, pos.y)){
					screen.setReplayButtonPressed(true);
				}
				
				else if(OverlapTester.pointInRectangle(mainMenuBounds, 
						pos.x, pos.y)){
					screen.setMainMenuButtonPressed(true);
				}
				
				else if(OverlapTester.pointInRectangle(nextBounds, 
						pos.x, pos.y)&&labirin.getScore()>60){
					screen.setNextButtonPressed(true);
					
				}
			}
			
			if(!Gdx.input.isTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				if(screen.replayButtonIsPressed()){
					screen.setReplayButtonPressed(false);
					if(OverlapTester.pointInRectangle(replayBounds, pos.x, pos.y)){
						app.setScreen(new LabirinScreen(app, labirin));
					}
				}
				
				else if(screen.mainMenuButtonIsPressed()){
					screen.setMainMenuButtonPressed(false);
					if(OverlapTester.pointInRectangle(mainMenuBounds, 
							pos.x, pos.y)){
						app.setScreen(new MainMenuScreen(app));
					}
				}
				
				
				else if(screen.nextButtonIsPressed()){
					screen.setNextButtonPressed(false);
					if(OverlapTester.pointInRectangle(nextBounds, 
							pos.x, pos.y)){
						app.setScreen(new CongratulationsScreen(app));
					}
				}
			}
		}
		else{
			if (Gdx.input.isKeyPressed(Keys.BACK)){
				screen.pause();
				app.setScreen(new PauseScreen(app, screen, labirin));
			}
			
			labirin.timeLeft-=delta;
			
			if(labirin.timeLeft<0){
				labirin.gameOver();
			}
			
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
					screen.setPauseButtonPressed(true);	
				}
				
			}
			
			if(!Gdx.input.isTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(screen.pauseButtonIsPressed()){
					screen.setPauseButtonPressed(false);
					if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
						screen.pause();
						app.setScreen(new PauseScreen(app, screen, labirin));
						
					}
				}
			}
			
			float accelX = Gdx.input.getAccelerometerX();
			float accelY = Gdx.input.getAccelerometerX();
			
			if(Math.abs(accelX)>=Math.abs(accelY)){
				accelY = 0f;
			}
			else{
				accelX = 0f;
			}
			
			labirin.getPlayer().setVelocityX(accelX);
			labirin.getPlayer().setVelocityY(accelY);
		}
		
	}

}
