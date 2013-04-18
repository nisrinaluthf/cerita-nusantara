package com.a4.ceritanusantara.controllers;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AdeganScreen;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.LabirinScreen;
import com.a4.ceritanusantara.views.PauseScreen;
import com.a4.ceritanusantara.views.TapGameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class AdeganController {

	private Aplikasi app;
	
	private AdeganScreen screen;
	private Rectangle previousButtonBounds;
	private Rectangle nextButtonBounds;
	private Rectangle pauseButtonBounds;

	private OrthographicCamera cam;
	private Rectangle viewport;

	private Adegan adegan;
	
	public AdeganController(AdeganScreen screen) {
		// TODO Auto-generated constructor stub
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = screen.getAplikasi();
		this.adegan = screen.getAdegan();
		
		previousButtonBounds = this.screen.getPreviousButtonBounds();
		nextButtonBounds = this.screen.getNextButtonBounds();
		
		pauseButtonBounds = this.screen.getPauseButtonBounds();
		
		cam = this.screen.getCam();
		viewport = this.screen.getViewport();

	}
	
	public void processInput(float delta){
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			app.setScreen(new PauseScreen(app, screen, adegan));
		}
		
		adegan.time+=delta;
		
		if(adegan.time>(adegan.getAdeganText(adegan.getCurrentText())).getEnd()){
			if(adegan.getCurrentText()<adegan.getLength()-1){
				adegan.updateCurrentText();
			}
			else{
				FileHandle localFile = null;
				
				if(adegan.getAsalCerita()==CeritaNusantara.SUMATERA){
					localFile = Gdx.files.local("datasumatera");
				}
				else if(adegan.getAsalCerita()==CeritaNusantara.KALIMANTAN){
					localFile = Gdx.files.local("datakalimantan");
				}
				
				
				String data = localFile.readString();
				StringTokenizer st = new StringTokenizer(data,  ";");
				int i=0;
				String tmp = "";
				while(st.hasMoreTokens()){
					String token = st.nextToken();
					StringTokenizer st2 = new StringTokenizer(token,  " ");
					
					if(adegan.getIndex()==i){
						
					}
					else{
						
					}
					
					i++;
				}
				adegan.done();
			}
			
		}
		
		if(adegan.isDone()){
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(adegan.getNext()!=null){
					if(OverlapTester.pointInRectangle(nextButtonBounds,pos.x, pos.y)){
						screen.playSoundFx("default");
						screen.setNextButtonPressed(true);	
					}
				}
				if(adegan.getPrev()!=null){
					if(OverlapTester.pointInRectangle(previousButtonBounds,pos.x, pos.y)){
						screen.playSoundFx("default");
						screen.setPreviousButtonPressed(true);	
					}
				}
				
			}
			
			if(!Gdx.input.isTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(adegan.getNext()!=null){
					if(screen.nextButtonIsPressed()){
						screen.setNextButtonPressed(false);
						if(OverlapTester.pointInRectangle(nextButtonBounds, pos.x, pos.y)){
							SubCerita next = adegan.getNext();
							int type = next.getTipe();
							
							if(type==SubCerita.ADEGAN){
								screen.stopMusic();
								app.getScreen().dispose();
								app.setScreen(new AdeganScreen(app, (Adegan)next));
							}
							else if(type==SubCerita.TAP_GAME){
								screen.stopMusic();
								app.getScreen().dispose();
								app.setScreen(new TapGameScreen(app, (TapGame)next));
							}
							else if(type==SubCerita.LABIRIN){
								screen.stopMusic();
								app.getScreen().dispose();app.setScreen(new LabirinScreen(app, (Labirin)next));
							}
							else if(type==SubCerita.KUIS){
								screen.stopMusic();
								app.getScreen().dispose();app.setScreen(new KuisScreen(app, (Kuis)next));
							}
						}
					}
				}
				if(adegan.getPrev()!=null){
					if(screen.previousButtonIsPressed()){
						screen.setPreviousButtonPressed(false);
						if(OverlapTester.pointInRectangle(previousButtonBounds, pos.x, pos.y)){
							SubCerita prev = adegan.getPrev();
							int type = prev.getTipe();
							
							if(type==SubCerita.ADEGAN){
								screen.stopMusic();
								app.getScreen().dispose();
								app.setScreen(new AdeganScreen(app, (Adegan)prev));
							}
							else if(type==SubCerita.TAP_GAME){
								screen.stopMusic();
								app.getScreen().dispose();
								app.setScreen(new TapGameScreen(app, (TapGame)prev));
							}
							else if(type==SubCerita.LABIRIN){
								screen.stopMusic();
								app.getScreen().dispose();
								app.setScreen(new LabirinScreen(app, (Labirin)prev));
							}
							else if(type==SubCerita.KUIS){
								screen.stopMusic();
								app.getScreen().dispose();
								app.setScreen(new KuisScreen(app, (Kuis)prev));
							}
						}
					}
				}
			}
		}
		else{
			if(Gdx.input.justTouched()){
				Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
				
				if(OverlapTester.pointInRectangle( pauseButtonBounds,pos.x, pos.y)){
					screen.pauseMusic();
					screen.playSoundFx("default");
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
						app.setScreen(new PauseScreen(app, screen, adegan));
						
					}
				}
			}
		}
		
	}

}
