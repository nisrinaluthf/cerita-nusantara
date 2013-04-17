package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.LabirinController;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.LabirinItem;
import com.a4.ceritanusantara.models.LabirinPlayer;
import com.a4.ceritanusantara.models.LabirinWall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LabirinScreen extends AbstractScreen{
	
	private Labirin labirin;
	private LabirinController controller;
	
	private Texture background;
	private Texture wallTexture;
	private Texture[] playerTexture;
	
	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;
	
	private boolean pauseButtonPressed;
	
	private LabirinWall[] walls;
	private LabirinItem[] items;
	
	private boolean debug = false;
	private Texture itemTexture;
	
	private Texture gameOverBg;
	private Texture replayTexture;
	private Texture replayPressedTexture;
	private Texture mainMenuTexture;
	private Texture mainMenuPressedTexture;
	private Texture nextTexture;
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	
	private boolean replayButtonPressed;
	private boolean mainMenuButtonPressed;
	private boolean nextButtonPressed;
	
	private BitmapFont font44;
	
	
	private Sound eggObtainedSfx;
	
	private Sound pauseClickSfx;
	
	private Music labirinMusicBg;
	
	public LabirinScreen(Aplikasi app, Labirin labirin) {
		super(app);
		// TODO Auto-generated constructor stub
		this.labirin = labirin;
		
		labirin.reinit();
		
		walls = labirin.getWalls();
		for(int i=0; i<walls.length; i++){
			System.out.println("wall"+walls[i].getX());
		}
		items = labirin.getItems();
		
		background = labirin.getBackground();
		wallTexture = labirin.getWallTexture();
		playerTexture = labirin.getPlayerTexture();
		itemTexture = labirin.getItemTexture();
		
		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));

		pauseButtonBounds = new Rectangle(950, 526, 60, 60);
		
		pauseButtonPressed = false;
		
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
		
		font44 = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		
labirinMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/labirin.mp3"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.labirinMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				labirinMusicBg.setLooping(true);
				labirinMusicBg.setVolume(1.0f);
				labirinMusicBg.play();
			} 
		} else if(this.labirinMusicBg != null && this.labirinMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		this.eggObtainedSfx = Gdx.audio.newSound(Gdx.files.internal("sound/labirin_eggobtained.mp3"));
		this.pauseClickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		
		controller = new LabirinController(this);
		
	}
	
	public void render(float delta){
		cam.update();

	      
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			if(labirin.isGameOver()){
			
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
					System.out.println("mainmenu pressed drawed");
					batcher.draw(mainMenuPressedTexture, 
							(VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2, 180);
				}
				else{
					batcher.draw(mainMenuTexture, 
							(VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2, 180);
				}
				
				
				
				if(labirin.getScore()>=50){
					batcher.draw(nextTexture, 950, 30);
					font44.draw(batcher, "Skor kamu: "+labirin.getScore(), 380, 400);
				}
				else{
					font44.draw(batcher, "Maaf kamu gagal,", 362, 420);
					font44.draw(batcher, "silakan coba lagi :)", 358, 370);
				}
				
			
			}
			else{
				batcher.draw(background, 0, 0);
				for(int i=0; i<walls.length; i++){
					batcher.draw(wallTexture, walls[i].getX(), walls[i].getY());
				}
				for(int i=0; i<items.length; i++){
					if(!labirin.getItem(i).isFound()){
						batcher.draw(itemTexture, items[i].getX(), items[i].getY());
					}	
				}
				int state = labirin.getPlayer().getState();
				batcher.draw(playerTexture[state], labirin.getPlayer().getX(), 
						labirin.getPlayer().getY());
				
				if (pauseButtonPressed) {
					batcher.draw(pauseButtonPressedTexture,950, 526);
				} else {
					batcher.draw(pauseButtonTexture, 950, 526);
				}
				
				int time = (int)labirin.timeLeft;
				
				font44.draw(batcher, (time/60)+":"+((time%60)/10)+(((time%60)%10)), 23, 580);
				
			}
		batcher.end();
		labirin.getPlayer().updatePos();
		controller.processInput(delta);
		
		if(debug){
			drawDebug();
		}
		
	}
	
	
	private void drawDebug() {
		// TODO Auto-generated method stub
		
		Rectangle[] top = new Rectangle[walls.length];
		Rectangle[] right = new Rectangle[walls.length];
		Rectangle[] bottom = new Rectangle[walls.length];
		Rectangle[] left = new Rectangle[walls.length];
		
		Rectangle player = labirin.getPlayer().getBounds();
		
		for(int i=0; i<walls.length; i++){
			top[i] = walls[i].getBounds(0);
			right[i] = walls[i].getBounds(1);
			bottom[i] = walls[i].getBounds(2);
			left[i] = walls[i].getBounds(3);
		}
		
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			
			for(int i=0; i<top.length; i++){	
				debugRenderer.rect(top[i].x, top[i].y, top[i].width, top[i].height);
				debugRenderer.rect(right[i].x, right[i].y, right[i].width, right[i].height);
				debugRenderer.rect(left[i].x, left[i].y, left[i].width, left[i].height);
				debugRenderer.rect(bottom[i].x, bottom[i].y, bottom[i].width, bottom[i].height);
			}
			
			debugRenderer.rect(player.x, player.y, player.width, player.height);
		
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

	public Labirin getLabirin() {
		// TODO Auto-generated method stub
		return labirin;
	}
	
	public void playSoundFx(String key) {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			if (key.equals("default"))
				this.pauseClickSfx.play();
			else if (key.equals("true"))
				this.eggObtainedSfx.play();
	}
	
	
	public void stopMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.labirinMusicBg.getPosition());
		System.out.println("stop");
		if(this.labirinMusicBg != null) {
			if (this.labirinMusicBg.isPlaying()) {
				if (this.labirinMusicBg.isLooping()) {
					this.labirinMusicBg.setLooping(false);
				}
				this.labirinMusicBg.stop();
				this.labirinMusicBg.dispose();
				this.labirinMusicBg = null;
			}
			this.labirinMusicBg = null;
		}
	}
	public void pauseMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.labirinMusicBg.getPosition());
		System.out.println("stop");
		if(this.labirinMusicBg != null) {
			if (this.labirinMusicBg.isPlaying()) {
				if (this.labirinMusicBg.isLooping()) {
					this.labirinMusicBg.setLooping(false);
				}
				this.labirinMusicBg.pause();
				//this.labirinMusicBg.dispose();
				//this.labirinMusicBg = null;
			}
			//this.labirinMusicBg = null;
		}
	}
	
	public void resume() {
		super.resume();
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.labirinMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				labirinMusicBg.play();
			} else {
				this.labirinMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/labirin.mp3"));
				System.out.println("play music after null");
				labirinMusicBg.setLooping(true);
				labirinMusicBg.setVolume(1.0f);
				labirinMusicBg.play();
			}
		} else if(this.labirinMusicBg != null && this.labirinMusicBg.isPlaying()) {
			this.stopMusic();
		}
	}

}
