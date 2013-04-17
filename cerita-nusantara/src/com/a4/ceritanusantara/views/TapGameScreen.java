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

public class TapGameScreen extends AbstractScreen{
	
	private TapGame tapGame;
	private TapGameController controller;
	
	private Texture background;
	private Texture panelBgTexture;
	private Texture[] targetsTexture;
	private Texture[] indicatorsTexture;
	private Texture scoreBgTexture;
	private Texture scoreFrameTexture;
	
	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;
	
	private boolean pauseButtonPressed;
	
	private Texture gameOverBg;
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
		
	public TapGameScreen(Aplikasi app, TapGame tapGame){
		super(app);
		
		this.tapGame = tapGame;
		tapGame.reinit();
		
		background = tapGame.getBackground();
		panelBgTexture = tapGame.getPanelBackground();
		targetsTexture = tapGame.getTargetsTexture();
		indicatorsTexture = tapGame.getIndicators();
		
		gameOverBg = new
				Texture(Gdx.files.internal("backgrounds/gameover_bg.png"));
		replayTexture = new
				Texture(Gdx.files.internal("buttons/restart.png"));
		replayPressedTexture = new
				Texture(Gdx.files.internal("buttons/restart_pressed.png"));
		mainMenuTexture = new
				Texture(Gdx.files.internal("buttons/mainmenu.png"));
		mainMenuPressedTexture = new
				Texture(Gdx.files.internal("buttons/mainmenu_pressed.png"));
		nextTexture = new
				Texture(Gdx.files.internal("buttons/dialog_next.png"));
		
		//inisialisasi bounds buat game over screen here
		replayBounds = new Rectangle((VIRTUAL_WIDTH-replayTexture.getWidth())/2, 240,
				replayTexture.getWidth(), replayTexture.getHeight());
		mainMenuBounds = new Rectangle((VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2, 180,
				mainMenuTexture.getWidth(), mainMenuTexture.getHeight());
		nextBounds = new Rectangle(950, 30, nextTexture.getWidth(), nextTexture.getHeight());
		
		replayButtonPressed = false;
		mainMenuButtonPressed = false;
		nextButtonPressed = false;
		
		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));

		pauseButtonBounds = new Rectangle(950, 526, 60, 60);
		
		pauseButtonPressed = false;
		
		scoreBgTexture = new Texture(Gdx.files.internal("parkit-tapgame/score_bg.png"));
		scoreFrameTexture = new Texture(Gdx.files.internal("parkit-tapgame/score_frame.png"));
		
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		
		buttons = tapGame.getButtons();
		
		tapGameMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/labirin.mp3"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.tapGameMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				tapGameMusicBg.setLooping(true);
				tapGameMusicBg.setVolume(1.0f);
				tapGameMusicBg.play();
			} else {
				this.tapGameMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/labirin.mp3"));
				System.out.println("play music after null");
				tapGameMusicBg.setLooping(true);
				tapGameMusicBg.setVolume(1.0f);
				tapGameMusicBg.play();
			}
		} else if(this.tapGameMusicBg != null && this.tapGameMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		this.falseAnswerSfx = Gdx.audio.newSound(Gdx.files.internal("sound/miss.ogg"));
		this.rightAnswerSfx = Gdx.audio.newSound(Gdx.files.internal("sound/answer_true.mp3"));
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
				if(replayButtonPressed){
					batcher.draw(replayPressedTexture, 
							(VIRTUAL_WIDTH-replayTexture.getWidth())/2, 240);
				}
				else{
					batcher.draw(replayTexture, 
							(VIRTUAL_WIDTH-replayTexture.getWidth())/2, 240);
				}
				
				if(mainMenuButtonPressed){
					batcher.draw(mainMenuPressedTexture, 
							(VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2, 180);
				}
				else{
					batcher.draw(mainMenuTexture, 
							(VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2, 180);
				}
				
				if(tapGame.getBadHits()>10) tapGame.setScore(50);
				else{
					tapGame.setScore(100-(tapGame.getBadHits()*5));
				}
				
				
				if(tapGame.getScore()>=60){
					batcher.draw(nextTexture, 950, 30);
					font.draw(batcher, "Skor kamu: "+tapGame.getScore(), 380, 400);
				}
				else{
					font.draw(batcher, "Maaf kamu gagal,", 362, 420);
					font.draw(batcher, "silakan coba lagi :)", 358, 370);
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
					batcher.draw(pauseButtonPressedTexture,950, 526);
				} else {
					batcher.draw(pauseButtonTexture, 950, 526);
				}
				
			batcher.end();
			
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
					batcher.draw(targetsTexture[target.getType()], 
							target.getIndex() == 0 ? 274 : target.getIndex() == 1 ? 449 : 634,
							target.getPos());
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
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.tapGameMusicBg.getPosition());
		System.out.println("stop");
		if(this.tapGameMusicBg != null) {
			if (this.tapGameMusicBg.isPlaying()) {
				if (this.tapGameMusicBg.isLooping()) {
					this.tapGameMusicBg.setLooping(false);
				}
				this.tapGameMusicBg.stop();
				this.tapGameMusicBg.dispose();
				this.tapGameMusicBg = null;
			}
			this.tapGameMusicBg = null;
		}
	}
	public void pauseMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.tapGameMusicBg.getPosition());
		System.out.println("stop");
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
			//System.out.println("play music");
			if (this.tapGameMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				tapGameMusicBg.setLooping(true);
				tapGameMusicBg.setVolume(1.0f);
				tapGameMusicBg.play();
			} else {
				this.tapGameMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/tapGame.mp3"));
				System.out.println("play music after null");
				tapGameMusicBg.setLooping(true);
				tapGameMusicBg.setVolume(1.0f);
				tapGameMusicBg.play();
			}
		} else if(this.tapGameMusicBg != null && this.tapGameMusicBg.isPlaying()) {
			this.stopMusic();
		}
	}
}
