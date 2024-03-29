package com.a4.ceritanusantara.controllers;

import java.util.Iterator;
import java.util.StringTokenizer;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.RunningGame;
import com.a4.ceritanusantara.models.RunningGameObstacle;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AdeganScreen;
import com.a4.ceritanusantara.views.HelpScreen;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.LabirinScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.a4.ceritanusantara.views.RunningGameScreen;
import com.a4.ceritanusantara.views.TapGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>RunningGameController</code> adalah controller yang memproses input
 * untuk screen subcerita bertipe permainan running game.
 */
public class RunningGameController {

	private RunningGameScreen screen;
	private Aplikasi app;
	private RunningGame runningGame;
	private OrthographicCamera cam;
	private Rectangle viewport;
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	private Rectangle leftButtonBounds;
	private Rectangle rightButtonBounds;
	private Rectangle pauseButtonBounds;
	private Rectangle helpButtonBounds;

	public RunningGameController(RunningGameScreen screen,
			Aplikasi app, RunningGame runningGame) {
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = app;
		this.runningGame = runningGame;
		this.cam = screen.getCam();
		this.viewport = screen.getViewport();
		//this.buttons = tapGame.getButtons();
		
		replayBounds = screen.getReplayBounds();
		mainMenuBounds = screen.getMainMenuBounds();
		nextBounds = screen.getNextBounds();
		leftButtonBounds = screen.getLeftButtonBounds();
		rightButtonBounds = screen.getRightButtonBounds();

		pauseButtonBounds = screen.getPauseButtonBounds();
		helpButtonBounds = screen.getHelpButtonBounds();
		// TODO Auto-generated constructor stub
	}

	public void processInput() {
		// TODO Auto-generated method stub
		if(runningGame.isGameOver()){
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
						pos.x, pos.y)&&runningGame.getScore()>=60){
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
						app.setScreen(new RunningGameScreen(app, runningGame));
					}
				}
				
				else if(screen.mainMenuButtonIsPressed()){
					screen.setMainMenuButtonPressed(false);
					if(OverlapTester.pointInRectangle(mainMenuBounds, 
							pos.x, pos.y)){
						app.getScreen().dispose();
						app.setScreen(new PilihSubCeritaScreen(app,
								app.getCeritaNusantara().getCerita(runningGame.getAsalCerita())));
					}
				}
				
				
				else if(screen.nextButtonIsPressed()){
					screen.setNextButtonPressed(false);
					if(OverlapTester.pointInRectangle(nextBounds, 
							pos.x, pos.y)){
						screen.setNextButtonPressed(false);
						if(OverlapTester.pointInRectangle(nextBounds, 
								pos.x, pos.y)){
							screen.setNextButtonPressed(false);
							if(OverlapTester.pointInRectangle(nextBounds, pos.x, pos.y)){
								SubCerita next = runningGame.getNext();
								int type = next.getTipe();
								
								if(type==SubCerita.ADEGAN){
									app.setScreen(new AdeganScreen(app, (Adegan)next));
								}
								else if(type==SubCerita.TAP_GAME){
									app.setScreen(new TapGameScreen(app, (TapGame)next));
								}
								else if(type==SubCerita.LABIRIN){
									app.setScreen(new LabirinScreen(app, (Labirin)next));
								}
								else if(type==SubCerita.KUIS){
									app.setScreen(new KuisScreen(app, (Kuis)next));
								}
							}
						}
					}
				}
			}
		}
		else{
			//Vector2 player = new Vector2();
			Iterator<RunningGameObstacle> itr = runningGame.getObstacles().iterator();
			
				while(itr.hasNext()){
					RunningGameObstacle obs = itr.next();
					if(OverlapTester.overlapRectangles(obs.getBounds(), this.runningGame.getPlayerBounds()) && !obs.isHit()){
						obs.setHit(true);
						//if(target.isBad()){
							screen.playSoundFx("false");
							//target.setHit(true);
							runningGame.setHealth(runningGame.getHealth()-1);
							runningGame.setScore(runningGame.getScore() - 10);
							//tapGame.setHits(tapGame.getHits()-1);
							//tapGame.addBadHit();
						//}
					}
				}
			
			if((runningGame.getDistance()/runningGame.getFinishLine()) >= (3/5)) {
				runningGame.setProgressIcon(new Texture(Gdx.files.internal("selatbali_running/progress_icon_2.png")));
			}
			
			if (Gdx.input.isKeyPressed(Keys.BACK)){
				screen.playSoundFx("default");
				screen.pauseMusic();
				screen.pause();
				app.setScreen(new PauseScreen(app, screen, runningGame));
			}
			
			//if(runningGame.getHealth()<=0){
				//runningGame.setGameOver();
			//}
			if (runningGame.getHealth()<=0 || runningGame.getDistance() >=runningGame.getFinishLine()) {
				save();
				runningGame.setGameOver();
			}
			
			
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle(pauseButtonBounds, pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.pauseMusic();
					screen.setPauseButtonPressed(true);	
				}
				else if(OverlapTester.pointInRectangle( helpButtonBounds, pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.pauseMusic();
					screen.setHelpButtonPressed(true);	
				}
				else if(OverlapTester.pointInRectangle( leftButtonBounds, pos.x, pos.y)){
					screen.setLeftButtonPressed(true);
				}
				else if(OverlapTester.pointInRectangle( rightButtonBounds, pos.x, pos.y)){
					screen.setRightButtonPressed(true);
				}
				
				
			}
			
			if(Gdx.input.isTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				if(screen.leftButtonIsPressed()){
					//screen.setLeftButtonPressed(false);
					if(OverlapTester.pointInRectangle( leftButtonBounds, pos.x, pos.y)){
							
						float currentPosition = runningGame.getPlayerXPosition();
						if(currentPosition >= runningGame.getLeftLimit()-5.0f) {
							runningGame.setPlayerXPosition(currentPosition-5.0f);
						} else {
							runningGame.setPlayerXPosition(runningGame.getLeftLimit());
						}
					}
				}
				else if(screen.rightButtonIsPressed()){
					//screen.setRightButtonPressed(false);
					if(OverlapTester.pointInRectangle( rightButtonBounds, pos.x, pos.y)){
							
						float currentPosition = runningGame.getPlayerXPosition();
						if(currentPosition <= (runningGame.getRightLimit()+5.0f)) {
							runningGame.setPlayerXPosition(currentPosition+5.0f);
						} else {
							runningGame.setPlayerXPosition(runningGame.getRightLimit());
						}
					}
				}
			}
			else {
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(screen.pauseButtonIsPressed()){
					screen.setPauseButtonPressed(false);
					if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
						screen.pause();
						app.setScreen(new PauseScreen(app, screen, runningGame));
						
					}
				}
				
				else if(screen.helpButtonIsPressed()){
					screen.setHelpButtonPressed(false);
					if(OverlapTester.pointInRectangle( helpButtonBounds, pos.x, pos.y)){
						screen.pause();
						app.setScreen(new HelpScreen(app, screen, runningGame));
					}
				}
				else if(screen.leftButtonIsPressed()){
					screen.setLeftButtonPressed(false);
					if(OverlapTester.pointInRectangle( leftButtonBounds, pos.x, pos.y)){
							
						
					}
				}
				else if(screen.rightButtonIsPressed()){
					screen.setRightButtonPressed(false);
					if(OverlapTester.pointInRectangle( rightButtonBounds, pos.x, pos.y)){
							
						
					}
				}
				/*
				for(int i=0; i<buttonsBounds.length; i++){
					if(buttons[i].isPressed()){
						buttons[i].setPressed(false);	
					}
				}
				*/
			}
		}
		
	}

	private void save() {
		FileHandle localFile = null;
		
		if(runningGame.getAsalCerita()==CeritaNusantara.SUMATERA){
			localFile = Gdx.files.local("datasumatera");
		}
		else if(runningGame.getAsalCerita()==CeritaNusantara.KALIMANTAN){
			localFile = Gdx.files.local("datakalimantan");
		}
		else if(runningGame.getAsalCerita()==CeritaNusantara.JAWA){
			localFile = Gdx.files.local("datajawa");
		}
		else if(runningGame.getAsalCerita()==CeritaNusantara.BALI){
			localFile = Gdx.files.local("databali");
		}
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		String tmp = "";
		while(st.hasMoreTokens()){
			if(runningGame.getIndex() == i){
				StringTokenizer st2 = new StringTokenizer(st.nextToken(), " ");
				st2.nextToken();
				int currentScore = Integer.parseInt(st2.nextToken());
				if (currentScore < runningGame.getScore()) {
					currentScore = runningGame.getScore();
				}
				tmp += "unlocked "+ currentScore+";";
			}
			else if(runningGame.getNext().getIndex() == i){
				StringTokenizer st2 = 
						new StringTokenizer(st.nextToken(),  " ");
				st2.nextToken();
				tmp += "unlocked "+st2.nextToken()+";";
				runningGame.getNext().setUnlocked(true);
			}
			else{
				tmp += st.nextToken()+";";
			}
			i++;
		}
		
		if(tmp.charAt(tmp.length()-1)==';'){
			tmp = tmp.substring(0, tmp.length()-1);
		}
		localFile.writeString(tmp, false);
		
	}

}
