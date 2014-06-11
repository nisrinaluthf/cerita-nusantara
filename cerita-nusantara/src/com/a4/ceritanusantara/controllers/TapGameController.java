package com.a4.ceritanusantara.controllers;

import java.util.Iterator;
import java.util.StringTokenizer;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.TapGameButton;
import com.a4.ceritanusantara.models.TapGameTarget;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AdeganScreen;
import com.a4.ceritanusantara.views.HelpScreen;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.LabirinScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.a4.ceritanusantara.views.TapGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>TapGameController</code> adalah controller yang memproses input
 * untuk screen subcerita bertipe permainan tap game.
 */
public class TapGameController {

	private Aplikasi app;
	private TapGameScreen screen;
	private TapGame tapGame;
	
	private OrthographicCamera cam;
	private Rectangle viewport;
	
	private TapGameButton[] buttons;
	private Rectangle[] buttonsBounds;
	private Rectangle pauseButtonBounds;
	private Rectangle helpButtonBounds;
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;

	public TapGameController(TapGameScreen screen, Aplikasi app, TapGame tapGame) {
		// TODO Auto-generated constructor stub
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = app;
		this.tapGame = tapGame;
		this.cam = screen.getCam();
		this.viewport = screen.getViewport();
		this.buttons = tapGame.getButtons();
		
		replayBounds = screen.getReplayBounds();
		mainMenuBounds = screen.getMainMenuBounds();
		nextBounds = screen.getNextBounds();
		
		this.buttonsBounds = new Rectangle[buttons.length];
		for(int i=0; i<buttons.length; i++){
			buttonsBounds[i] = buttons[i].getBounds();
		}
		
		pauseButtonBounds = screen.getPauseButtonBounds();
		helpButtonBounds = screen.getHelpButtonBounds();
				
	}

	public void processInput() {
		// Kalo back pause screen muncul
		if(tapGame.isGameOver()){
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle(replayBounds, pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.stopMusic();
					screen.setReplayButtonPressed(true);
					System.out.println("replay");
				}
				
				else if(OverlapTester.pointInRectangle(mainMenuBounds, 
						pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.stopMusic();
					screen.setMainMenuButtonPressed(true);
					System.out.println("mainmenu");
				}
				
				else if(OverlapTester.pointInRectangle(nextBounds, 
						pos.x, pos.y)&&tapGame.getScore()>=60){
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
						app.setScreen(new TapGameScreen(app, tapGame));
					}
				}
				
				else if(screen.mainMenuButtonIsPressed()){
					screen.setMainMenuButtonPressed(false);
					if(OverlapTester.pointInRectangle(mainMenuBounds, 
							pos.x, pos.y)){
						app.getScreen().dispose();
						app.setScreen(new PilihSubCeritaScreen(app,
								app.getCeritaNusantara().getCerita(tapGame.getAsalCerita())));
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
								SubCerita next = tapGame.getNext();
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
			if (Gdx.input.isKeyPressed(Keys.BACK)){
				screen.playSoundFx("default");
				screen.pauseMusic();
				screen.pause();
				app.setScreen(new PauseScreen(app, screen, tapGame));
			}
			
			if(tapGame.getHits()<=0){
				save();
				tapGame.gameOver();
			}
			System.out.println(tapGame.getScore());
			if(tapGame.getHits()>=25){
				
				int score = 60;
				if(tapGame.getBadHits()<=20){
					score = 100 - tapGame.getBadHits()*2;
				}
				tapGame.setScore(score);
				save();
				tapGame.gameOver();
				//implement save score here
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
				
				for(int i=0; i<buttonsBounds.length; i++){
					if(OverlapTester.pointInRectangle(buttonsBounds[i], pos.x, pos.y)){
						buttons[i].setPressed(true);
						Vector2 midPoint = new Vector2(buttonsBounds[i].x+(buttonsBounds[i].width/2), 
								buttonsBounds[i].y+(buttonsBounds[i].height/2));
						Iterator<TapGameTarget> itr = tapGame.getTargets().iterator();
						try{
							while(itr.hasNext()){
								TapGameTarget target = itr.next();
								if(OverlapTester.pointInRectangle(target.getBounds(), midPoint.x, 
										midPoint.y) && target.getIndex()==i && !target.isHit()){
									target.setPressed(true);
									if(target.isBad()){
										screen.playSoundFx("false");
										target.setHit(true);
										tapGame.setHits(tapGame.getHits()-1);
										tapGame.addBadHit();
									}
									else {
										screen.playSoundFx("true");
										target.setHit(true);
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
				else if(screen.helpButtonIsPressed()){
					screen.setHelpButtonPressed(false);
					if(OverlapTester.pointInRectangle( helpButtonBounds, pos.x, pos.y)){
						screen.pause();
						app.setScreen(new HelpScreen(app, screen, tapGame));
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

	private void save() {
		FileHandle localFile = null;
		
		if(tapGame.getAsalCerita()==CeritaNusantara.SUMATERA){
			localFile = Gdx.files.local("datasumatera");
		}
		else if(tapGame.getAsalCerita()==CeritaNusantara.KALIMANTAN){
			localFile = Gdx.files.local("datakalimantan");
		}
		else if(tapGame.getAsalCerita()==CeritaNusantara.JAWA){
			localFile = Gdx.files.local("datajawa");
		}
		else if(tapGame.getAsalCerita()==CeritaNusantara.BALI){
			localFile = Gdx.files.local("databali");
		}
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		String tmp = "";
		while(st.hasMoreTokens()){
			if(tapGame.getIndex() == i){
				StringTokenizer st2 = new StringTokenizer(st.nextToken(), " ");
				st2.nextToken();
				int currentScore = Integer.parseInt(st2.nextToken());
				if (currentScore < tapGame.getScore()) {
					currentScore = tapGame.getScore();
				}
				tmp += "unlocked "+ currentScore+";";
			}
			else if(tapGame.getNext().getIndex() == i){
				StringTokenizer st2 = 
						new StringTokenizer(st.nextToken(),  " ");
				st2.nextToken();
				tmp += "unlocked "+st2.nextToken()+";";
				tapGame.getNext().setUnlocked(true);
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
		//System.out.println(tmp);
		
	}

}
