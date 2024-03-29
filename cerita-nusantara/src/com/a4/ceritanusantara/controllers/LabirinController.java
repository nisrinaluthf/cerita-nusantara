package com.a4.ceritanusantara.controllers;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.LabirinPlayer;
import com.a4.ceritanusantara.models.LabirinWall;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
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
 * Kelas <code>LabirinController</code> adalah controller yang memproses input
 * untuk screen subcerita bertipe permainan labirin.
 */
public class LabirinController {
	
private Aplikasi app;
	private LabirinScreen screen;
	private Labirin labirin;
	
	private Rectangle pauseButtonBounds;
	private Rectangle helpButtonBounds;
	
	private LabirinWall[] walls;
	private Vector2[] wallsPos;
	private Rectangle[] wallsBoundsTop;
	private Rectangle[] wallsBoundsRight;
	private Rectangle[] wallsBoundsBottom;
	private Rectangle[] wallsBoundsLeft;
	
	private LabirinPlayer player;
	
	private Rectangle itemsBounds[];
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	
	private OrthographicCamera cam;
	private Rectangle viewport;

	private Rectangle finish;

	public LabirinController(LabirinScreen screen) {
		// TODO Auto-generated constructor stub
		
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		app = screen.getAplikasi();
		labirin = screen.getLabirin();
		
		pauseButtonBounds = screen.getPauseButtonBounds();
		helpButtonBounds = screen.getHelpButtonBounds();
		

		player = labirin.getPlayer();
		walls = labirin.getWalls();
		wallsPos = new Vector2[walls.length];
		wallsBoundsTop = new Rectangle[walls.length];
		wallsBoundsRight = new Rectangle[walls.length];
		wallsBoundsBottom = new Rectangle[walls.length];
		wallsBoundsLeft = new Rectangle[walls.length];
		
		for(int i=0; i<walls.length; i++){
			wallsPos[i] = walls[i].getPos();
			wallsBoundsTop[i] = walls[i].getBounds(0);
			wallsBoundsRight[i] = walls[i].getBounds(1);
			wallsBoundsBottom[i] = walls[i].getBounds(2);
			wallsBoundsLeft[i] = walls[i].getBounds(3);
		}
		
		itemsBounds = new Rectangle[labirin.getItems().length];
		
		
		for(int i=0; i<labirin.getItems().length; i++){
			itemsBounds[i] = labirin.getItem(i).getBounds();
		}
		
		finish = labirin.getFinishBounds();
		
		replayBounds = screen.getReplayBounds();
		mainMenuBounds = screen.getMainMenuBounds();
		nextBounds = screen.getNextBounds();
		
		cam = screen.getCam();
		viewport = screen.getViewport();
	}
	
	public void processInput(float delta){
		
		if(labirin.timeLeft<0){
			save();
			labirin.gameOver();
		}
		
		if(labirin.isGameOver()){
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
						pos.x, pos.y)&&labirin.getScore()>=60){
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
						app.setScreen(new LabirinScreen(app, labirin));
					}
				}
				
				else if(screen.mainMenuButtonIsPressed()){
					screen.setMainMenuButtonPressed(false);
					if(OverlapTester.pointInRectangle(mainMenuBounds, 
							pos.x, pos.y)){
						app.getScreen().dispose();
						app.setScreen(new PilihSubCeritaScreen(app,
								app.getCeritaNusantara().getCerita(labirin.getAsalCerita())));
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
								SubCerita next = labirin.getNext();
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
				app.setScreen(new PauseScreen(app, screen, labirin));
			}
			
			labirin.timeLeft-=delta;
			
			if(labirin.timeLeft<0){
				save();
				labirin.gameOver();
			}
			
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle( pauseButtonBounds, pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.pauseMusic();
					screen.setPauseButtonPressed(true);	
				}
				else if(OverlapTester.pointInRectangle( helpButtonBounds, pos.x, pos.y)){
					screen.playSoundFx("default");
					screen.pauseMusic();
					screen.setHelpButtonPressed(true);	
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
				
				else if(screen.helpButtonIsPressed()){
					screen.setHelpButtonPressed(false);
					if(OverlapTester.pointInRectangle( helpButtonBounds, pos.x, pos.y)){
						screen.pause();
						app.setScreen(new HelpScreen(app, screen, labirin));
					}
				}
			}
			
			float accelY = -Gdx.input.getAccelerometerX();
			float accelX = Gdx.input.getAccelerometerY();
			
			player = labirin.getPlayer();
			
			if(Math.abs(accelX)>=Math.abs(accelY)){
				accelY = 0f;
			}
			else{
				accelX = 0f;
			}	
			
			if(Math.abs(accelX)>2f){
				if(accelX<0f){
					accelX = -2f;
				}
				else if(accelX>0f){
					accelX = 2f;
				}
			}
			
			if(Math.abs(accelY)>2f){
				if(accelY<0f){
					accelY = -2f;
				}
				else if(accelY>0f){
					accelY = 2f;
				}
			}
			
			if(accelX<0f){
				for(int i=0; i<wallsBoundsRight.length; i++){
					if(OverlapTester.pointInRectangle(wallsBoundsRight[i], player.getX(), player.getY())||
							OverlapTester.pointInRectangle(wallsBoundsRight[i], player.getX(), player.getY()+23)){
						accelX=0f;
						break;
					}
				}
			}
			else if(accelX>0f){
				for(int i=0; i<wallsBoundsLeft.length; i++){
					if(OverlapTester.pointInRectangle(wallsBoundsLeft[i], player.getX()+23, player.getY())||
							OverlapTester.pointInRectangle(wallsBoundsLeft[i], player.getX()+23, player.getY()+23)){
						accelX=0f;
						break;
					}
				}
			}
			
			if(accelY<0f){
				for(int i=0; i<wallsBoundsTop.length; i++){
					if(OverlapTester.pointInRectangle(wallsBoundsTop[i], player.getX(), player.getY())||
							OverlapTester.pointInRectangle(wallsBoundsTop[i], player.getX()+23, player.getY())){
						accelY=0f;
						break;
					}
				}
			}
			else if(accelY>0f){
				for(int i=0; i<wallsBoundsBottom.length; i++){
					if(OverlapTester.pointInRectangle(wallsBoundsBottom[i], player.getX(), player.getY()+23)||
							OverlapTester.pointInRectangle(wallsBoundsBottom[i], player.getX()+23, player.getY()+23)){
						accelY=0f;
						break;
					}
				}
			}
			
			for(int i=0; i<itemsBounds.length; i++){
				if(OverlapTester.pointInRectangle(itemsBounds[i], player.getX(), player.getY()+23)||
						OverlapTester.pointInRectangle(itemsBounds[i], player.getX()+23, player.getY()+23)||
						OverlapTester.pointInRectangle(itemsBounds[i], player.getX()+23, player.getY())||
						OverlapTester.pointInRectangle(itemsBounds[i], player.getX(), player.getY())){
					accelY=0;
					if(!labirin.getItem(i).isFound()){
						screen.playSoundFx("true");
					}
					labirin.getItem(i).setFound(true);
				}
			}
			
			if(OverlapTester.pointInRectangle(finish, player.getX(), player.getY()+23)||
					OverlapTester.pointInRectangle(finish, player.getX()+23, player.getY()+23)){
				
				boolean itemsFound = true;
				
				for(int i=0; i<itemsBounds.length; i++){
					if(!labirin.getItem(i).isFound()){
						itemsFound = false;
					}
				}
				
				if(itemsFound){
					int score;
					//if(labirin.timeLeft<10){
						//score = 60;
					//}
					//if{
					score = (int) (60+(labirin.timeLeft-10));
					//}
					if(score > 100){
						score = 100;
					}
					
					labirin.setScore(score);
					save();
					labirin.gameOver();
				}
				else{
					if(accelY>0f){
						accelY = 0f;
					}
				}
			}
			
			labirin.getPlayer().setVelocityX(accelX);
			labirin.getPlayer().setVelocityY(accelY);
		}
	}

	private void save() {
		FileHandle localFile = null;
		
		if(labirin.getAsalCerita()==CeritaNusantara.SUMATERA){
			localFile = Gdx.files.local("datasumatera");
		}
		else if(labirin.getAsalCerita()==CeritaNusantara.KALIMANTAN){
			localFile = Gdx.files.local("datakalimantan");
		}
		else if(labirin.getAsalCerita()==CeritaNusantara.JAWA){
			localFile = Gdx.files.local("datajawa");
		}
		else if(labirin.getAsalCerita()==CeritaNusantara.BALI){
			localFile = Gdx.files.local("databali");
		}
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		String tmp = "";
		while(st.hasMoreTokens()){
			if(labirin.getIndex() == i){
				StringTokenizer st2 = new StringTokenizer(st.nextToken(), " ");
				st2.nextToken();
				int currentScore = Integer.parseInt(st2.nextToken());
				if (currentScore < labirin.getScore()) {
					currentScore = labirin.getScore();
				}
				tmp += "unlocked "+ currentScore+";";
			}
			else if(labirin.getNext().getIndex() == i){
				StringTokenizer st2 = 
						new StringTokenizer(st.nextToken(),  " ");
				st2.nextToken();
				tmp += "unlocked "+st2.nextToken()+";";
				labirin.getNext().setUnlocked(true);
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
