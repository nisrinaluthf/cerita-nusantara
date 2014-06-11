package com.a4.ceritanusantara.views;

import java.util.Iterator;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.TapGameController;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.TapGameButton;
import com.a4.ceritanusantara.models.TapGameTarget;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/**
 * Kelas <code>TapGameScreen</code> adalah kelas untuk mengatur tampilan tap game termasuk suara dan musiknya
 * Kelas ini menerima input dari pengguna dan meneruskannya ke kelas TapGameController.
 */
public class TapGameScreen extends AbstractScreen{
	
	private TapGame tapGame;
	private TapGameController controller;
	
	private Texture background;
	private Texture panelBgTexture;
	private Texture[] targetsTexture;
	private Texture[] targetsPressedTexture;
	private Texture[] indicatorsTexture;
	private Texture scoreBgTexture;
	private Texture scoreFrameTexture;
	
	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;
	
	private Texture helpButtonTexture;
	private Texture helpButtonPressedTexture;
	private Rectangle helpButtonBounds;
	
	private boolean helpButtonPressed;
	
	private boolean pauseButtonPressed;
	
	private Texture gameOverBg;
	private Texture panel;
	private Texture replayTexture;
	private Texture replayPressedTexture;
	private Texture mainMenuTexture;
	private Texture mainMenuPressedTexture;
	private Texture nextTexture;
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	
	private TapGameButton[] buttons;
	
	private boolean debug = false;
	
	private boolean replayButtonPressed;
	private boolean mainMenuButtonPressed;
	private boolean nextButtonPressed;
	private BitmapFont font;
	
	private Sound falseAnswerSfx;
	
	private Sound rightAnswerSfx;
	
	private Sound pauseClickSfx;
	
	private Music tapGameMusicBg;
	private Texture gameover;
		
	public TapGameScreen(Aplikasi app, TapGame tapGame){
		super(app);
		
		this.tapGame = tapGame;
		tapGame.reinit();
		
		background = tapGame.getBackground();
		panelBgTexture = tapGame.getPanelBackground();
		targetsTexture = tapGame.getTargetsTexture();
		targetsPressedTexture = tapGame.getTargetsPressedTexture();
		indicatorsTexture = tapGame.getIndicators();
		
		gameOverBg = 
				new Texture(Gdx.files.internal("backgrounds/bg.png"));
		panel = 
				new Texture(Gdx.files.internal("backgrounds/currentscore_bg.png"));
		gameover = 
				new Texture(Gdx.files.internal("backgrounds/gameover_bg.png"));
		replayTexture = new
				Texture(Gdx.files.internal("buttons/restart_80.png"));
		replayPressedTexture = new
				Texture(Gdx.files.internal("buttons/restart_pressed_80.png"));
		mainMenuTexture = new
				Texture(Gdx.files.internal("buttons/mainmenu_80.png"));
		mainMenuPressedTexture = new
				Texture(Gdx.files.internal("buttons/mainmenu_pressed_80.png"));
		nextTexture = new
				Texture(Gdx.files.internal("buttons/dialog_next.png"));
		
		//inisialisasi bounds buat game over screen here
		replayBounds = new Rectangle((VIRTUAL_WIDTH-replayTexture.getWidth())/2+50, 200,
				replayTexture.getWidth(), replayTexture.getHeight());
		mainMenuBounds = new Rectangle((VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2-50, 200,
				mainMenuTexture.getWidth(), mainMenuTexture.getHeight());
		nextBounds = new Rectangle(950, 30, nextTexture.getWidth(), nextTexture.getHeight());
		
		replayButtonPressed = false;
		mainMenuButtonPressed = false;
		nextButtonPressed = false;
		
		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));

		pauseButtonBounds = new Rectangle(97, 
				VIRTUAL_HEIGHT-pauseButtonTexture.getHeight()-7, 
				pauseButtonTexture.getWidth(), pauseButtonTexture.getHeight());

		pauseButtonPressed = false;
		
		helpButtonTexture = new Texture(
				Gdx.files.internal("buttons/help.png"));
		helpButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/help_pressed.png"));

		helpButtonBounds = new Rectangle(7, 
				VIRTUAL_HEIGHT-pauseButtonTexture.getHeight()-7, 
				helpButtonTexture.getWidth(), helpButtonTexture.getHeight());
		
		helpButtonPressed = false;
		
		scoreBgTexture = new Texture(Gdx.files.internal("siparkit_tapgame/score_bg.png"));
		scoreFrameTexture = new Texture(Gdx.files.internal("siparkit_tapgame/score_frame.png"));
		
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		
		buttons = tapGame.getButtons();
		
		tapGameMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/tapGame.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.tapGameMusicBg != null) {
				tapGameMusicBg.setLooping(true);
				tapGameMusicBg.setVolume(1.0f);
				tapGameMusicBg.play();
			} 
		} else if(this.tapGameMusicBg != null && this.tapGameMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		this.falseAnswerSfx = Gdx.audio.newSound(Gdx.files.internal("sound/tapgame_miss.ogg"));
		this.rightAnswerSfx = Gdx.audio.newSound(Gdx.files.internal("sound/tapgame_hit.mp3"));
		this.pauseClickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		controller = new TapGameController(this, app, tapGame);
	}
	
	public void render(float delta){
		
		tapGame.generateTargets(delta);
		tapGame.updateTargets(delta);
		
		cam.update();

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
        batcher.setProjectionMatrix(cam.combined);
        if(tapGame.isGameOver()){
			batcher.begin();
				batcher.draw(gameOverBg, 0, 0);
				batcher.draw(panel, (VIRTUAL_WIDTH-panel.getWidth())/2, 
						(VIRTUAL_HEIGHT-panel.getHeight())/2);
				
				
				
				if(tapGame.getScore()>=60){
					batcher.draw(nextTexture, 950, 30);
					font.draw(batcher, "Skor kamu: "+tapGame.getScore(), 380, 400);
				}
				else{
					batcher.draw(gameover,  (VIRTUAL_WIDTH-panel.getWidth())/2, 
							(VIRTUAL_HEIGHT-panel.getHeight())/2);
					font.draw(batcher, "Maaf kamu gagal,", 362, 420);
					font.draw(batcher, "silakan coba lagi :)", 358, 370);
				}
				
				if(replayButtonPressed){
					batcher.draw(replayPressedTexture, 
							replayBounds.x, replayBounds.y);
				}
				else{
					batcher.draw(replayTexture, 
							replayBounds.x, replayBounds.y);
				}
				
				if(mainMenuButtonPressed){
					batcher.draw(mainMenuPressedTexture, 
							mainMenuBounds.x, mainMenuBounds.y);
				}
				else{
					batcher.draw(mainMenuTexture, 
							mainMenuBounds.x, mainMenuBounds.y);
				}
				
			batcher.end();
		}
        else{
			batcher.begin();
				
				batcher.draw(background, 0, 0);
				batcher.draw(panelBgTexture, 
						(VIRTUAL_WIDTH-panelBgTexture.getWidth())/2, 0);
				batcher.draw(scoreBgTexture, 900, 80);
				
				if (pauseButtonPressed) {
					batcher.draw(pauseButtonPressedTexture, pauseButtonBounds.getX(), 
							pauseButtonBounds.getY());
				} else {
					batcher.draw(pauseButtonTexture, pauseButtonBounds.getX(), 
							pauseButtonBounds.getY());
				}
				
				if (helpButtonPressed) {
					batcher.draw(helpButtonPressedTexture, helpButtonBounds.getX(), 
							helpButtonBounds.getY());
				} else {
					batcher.draw(helpButtonTexture, helpButtonBounds.getX(), 
							helpButtonBounds.getY());
				}
				
			batcher.end();
			
			debugRenderer.setProjectionMatrix(cam.combined);
			debugRenderer.begin(ShapeType.FilledRectangle);
				int hits = tapGame.getHits();
				if(hits>10){
					debugRenderer.setColor(new Color(0, 1, 0, 1));
				}
				else if(hits>4){
					debugRenderer.setColor(new Color(1, 1, 0, 1));
				}
				else{
					debugRenderer.setColor(new Color(1, 0, 0, 1));
				}
				debugRenderer.filledRect(904, 84, 35, (hits/25.0f)*480);
			debugRenderer.end();
			
			batcher.begin();
				batcher.draw(scoreFrameTexture, 900, 80);
				if(tapGame.getHits()>10){
					batcher.draw(indicatorsTexture[0], 865, 10);
				}
				else{
					batcher.draw(indicatorsTexture[1], 865, 0);
				}
			
				Iterator<TapGameTarget> itr = tapGame.getTargets().iterator();
				while(itr.hasNext()){
					TapGameTarget target = itr.next();
					if(target.isHit()){
						batcher.draw(targetsPressedTexture[target.getType()], 
								target.getIndex() == 0 ? 274 : target.getIndex() == 1 ? 449 : 634,
								target.getPos());
					}
					else{
						batcher.draw(targetsTexture[target.getType()], 
								target.getIndex() == 0 ? 274 : target.getIndex() == 1 ? 449 : 634,
								target.getPos());
					}
							
				}
				
				for(int i=0; i<buttons.length; i++){
					if(buttons[i].isPressed()){
						batcher.draw(buttons[i].getButtonPressedTexture(), 
								buttons[i].getX(), buttons[i].getY());
					}
					else{
						batcher.draw(buttons[i].getButtonTexture(), 
								buttons[i].getX(), buttons[i].getY());
					}
				}
			batcher.end();
        }	
		
		if(debug){
			drawDebug();
		}
		
		controller.processInput();
	}
	
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			for(int i=0; i<buttons.length; i++){
				debugRenderer.rect(buttons[i].getBounds().x, buttons[i].getBounds().y,
						buttons[i].getBounds().getWidth(), buttons[i].getBounds().getHeight());
			}
			
			Iterator<TapGameTarget> itr = tapGame.getTargets().iterator();
			while(itr.hasNext()){
				Rectangle rect = itr.next().getBounds();
				debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
			}	
			
		debugRenderer.end();
	}
	
	public Rectangle getPauseButtonBounds(){
		return pauseButtonBounds;
	}

	public void setPauseButtonPressed(boolean b) {
		pauseButtonPressed = b;
		
	}
	
	public boolean pauseButtonIsPressed(){
		return pauseButtonPressed;
	}
	
	public Rectangle getReplayBounds(){
		return replayBounds;
	}
	
	public Rectangle getMainMenuBounds(){
		return mainMenuBounds;
	}
	
	public Rectangle getNextBounds(){
		return nextBounds;
	}
	
	public void setReplayButtonPressed(boolean b){
		replayButtonPressed = b;
	}
	
	public void setMainMenuButtonPressed(boolean b){
		mainMenuButtonPressed = b;
	}
	
	public void setNextButtonPressed(boolean b){
		nextButtonPressed = b;
	}
	
	public boolean replayButtonIsPressed(){
		return replayButtonPressed;
	}
	
	public boolean mainMenuButtonIsPressed(){
		return mainMenuButtonPressed;
	}
	
	public boolean nextButtonIsPressed(){
		return nextButtonPressed;
	}
	
	public void playSoundFx(String key) {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			if (key.equals("default"))
				this.pauseClickSfx.play();
			else if (key.equals("false"))
				this.falseAnswerSfx.play();
			else if (key.equals("true"))
				this.rightAnswerSfx.play();
	}
	
	
	public void stopMusic() {
		if(this.tapGameMusicBg != null) {
			if (this.tapGameMusicBg.isPlaying()) {
				if (this.tapGameMusicBg.isLooping()) {
					this.tapGameMusicBg.setLooping(false);
				}
				this.tapGameMusicBg.stop();
				//this.tapGameMusicBg.dispose();
				//this.tapGameMusicBg = null;
			}
			//this.tapGameMusicBg = null;
		}
	}
	public void pauseMusic() {
		if(this.tapGameMusicBg != null) {
			if (this.tapGameMusicBg.isPlaying()) {
				if (this.tapGameMusicBg.isLooping()) {
					this.tapGameMusicBg.setLooping(false);
				}
				this.tapGameMusicBg.pause();
				//this.tapGameMusicBg.dispose();
				//this.tapGameMusicBg = null;
			}
			//this.tapGameMusicBg = null;
		}
	}
	
	public void resume() {
		super.resume();
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.tapGameMusicBg != null) {
				tapGameMusicBg.play();
			} else {
				this.tapGameMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/tapGame.ogg"));
				tapGameMusicBg.setLooping(true);
				tapGameMusicBg.setVolume(1.0f);
				tapGameMusicBg.play();
			}
		} else if(this.tapGameMusicBg != null && this.tapGameMusicBg.isPlaying()) {
			this.stopMusic();
		}
	}

	public Rectangle getHelpButtonBounds() {
		return helpButtonBounds;
	}

	public void setHelpButtonPressed(boolean b) {
		helpButtonPressed = b;
	}

	public boolean helpButtonIsPressed() {
		return helpButtonPressed;
	}
	
	public void dispose(){
		super.dispose();
		/*
		background.dispose();
		panelBgTexture.dispose();
		for(int i=0; i<targetsTexture.length; i++){
			targetsTexture[i].dispose();
		}
		for(int i=0; i<targetsPressedTexture.length; i++){
			targetsPressedTexture[i].dispose();
		}
		for(int i=0; i<indicatorsTexture.length; i++){
			indicatorsTexture[i].dispose();
		}
		*/
		scoreBgTexture.dispose();
		scoreFrameTexture.dispose();
		
		pauseButtonTexture.dispose();
		pauseButtonPressedTexture.dispose();
		
		helpButtonTexture.dispose();
		helpButtonPressedTexture.dispose();
		
		gameOverBg.dispose();
		panel.dispose();
		replayTexture.dispose();
		replayPressedTexture.dispose();
		mainMenuTexture.dispose();
		mainMenuPressedTexture.dispose();
		nextTexture.dispose();
		
		font.dispose();
		falseAnswerSfx.dispose();
		rightAnswerSfx.dispose();
		pauseClickSfx.dispose();
		tapGameMusicBg.dispose();
		gameover.dispose();
	}
}
