package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.AdeganController;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.AdeganText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class AdeganScreen extends AbstractScreen {
	private Adegan adegan;
	
	private Texture background;
	private Texture textArea;
	private Texture previousButtonTexture;
	private Texture nextButtonTexture;
	
	private Texture previousButtonPressedTexture;
	private Texture nextButtonPressedTexture;
	
	private Texture[] character;
	
	private AdeganText[] adeganText;
	
	private Rectangle previousButtonBounds;
	private Rectangle nextButtonBounds;
	
	private boolean previousButtonPressed;
	private boolean nextButtonPressed;
	
	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;

	private boolean pauseButtonPressed;
	
	private BitmapFont font;
	
	private AdeganController controller;
	
	private Music adeganMusicBg;
	
	private Sound pauseClickSfx;
	
	
	public AdeganScreen(Aplikasi app, Adegan adegan) {
		super(app);
		
		this.adegan = adegan;
		this.adegan.reinit();
		
		background = adegan.getBackground();
		textArea = new Texture(Gdx.files.internal("backgrounds/dialog_box.png"));
		character = adegan.getCharaTexture();
		
		adeganText = adegan.getAdeganText();

		previousButtonTexture = new Texture(
				Gdx.files.internal("buttons/dialog_prev.png"));
		nextButtonTexture = new Texture(
				Gdx.files.internal("buttons/dialog_next.png"));

		previousButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/dialog_prev.png"));
		nextButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/dialog_next.png"));

		
		previousButtonBounds = new Rectangle(940, 19,
				previousButtonTexture.getWidth(), previousButtonTexture.getHeight());
		nextButtonBounds = new Rectangle(940, 82,
				nextButtonTexture.getWidth(), nextButtonTexture.getHeight());
		
		
		previousButtonPressed = false;
		nextButtonPressed = false;
		
		
		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));
		
		pauseButtonBounds = new Rectangle( 950, 526, pauseButtonTexture.getWidth(),
				pauseButtonTexture.getHeight());
		
		pauseButtonPressed = false;
		
		font = new BitmapFont(
				Gdx.files.internal("fonts/sf-cartoonist-hand-30-white-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-30-white-bold.png"),
				false);
		
		adeganMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/adegan.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.adeganMusicBg != null) {
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				adeganMusicBg.setLooping(true);
				adeganMusicBg.setVolume(1.0f);
				adeganMusicBg.play();
			} 
		} else if(this.adeganMusicBg != null && this.adeganMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		this.pauseClickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		controller = new AdeganController(this);
	}
	
	public void render(float delta) {
		// TODO Auto-generated method stub

		cam.update();

		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			
			batcher.draw(background, 0, 0);

			batcher.draw(textArea, 0, 0);
			
			AdeganText text = adeganText[adegan.getCurrentText()];
			
			if(adegan.getCurrentText()==adegan.getLength()-1){
				font.drawWrapped(batcher, text.getText(), 10, 125, 892);
			}
			else if(text.getType()==AdeganText.NAR){
				font.drawWrapped(batcher, text.getText(), 10, 125, 1004);
			}
			else if(text.getType()==AdeganText.DIA){
				font.drawWrapped(batcher, text.getText(), 10, 125, 892);
				
			}
			
			if(adegan.isDone()){
				
				if(adegan.getNext()!=null){
					if(nextButtonPressed){
						batcher.draw(nextButtonPressedTexture, 940, 82);
					}
					else{
						batcher.draw(nextButtonTexture, 940, 82);
					}
				}
				if(adegan.getPrev()!=null){
					if(previousButtonPressed){
						batcher.draw(previousButtonPressedTexture, 940, 19);
					}
					else{
						batcher.draw(previousButtonTexture, 940, 19);
					}
				}
			}
			else{
				if(text.getType()==AdeganText.DIA){
					batcher.draw(character[text.getChara()], 900, 15);
				}
			}
			
			if (pauseButtonPressed) {
				batcher.draw(pauseButtonPressedTexture, 950, 526);
			} else {
				batcher.draw(pauseButtonTexture, 950, 526);
			}

		batcher.end();

		controller.processInput(delta);

	}
	
	public Rectangle getPreviousButtonBounds(){
		return previousButtonBounds;
	}
	
	public boolean previousButtonIsPressed() {
		return previousButtonPressed;
	}
	
	public void setPreviousButtonPressed(boolean b) {
		this.previousButtonPressed = b;
	}
	
	public Rectangle getNextButtonBounds(){
		return nextButtonBounds;
	}
	
	public boolean nextButtonIsPressed() {
		return nextButtonPressed;
	}
	
	public void setNextButtonPressed(boolean b) {
		this.nextButtonPressed = b;
	}
	
	public Rectangle getPauseButtonBounds(){
		return pauseButtonBounds;
	}
	
	public boolean pauseButtonIsPressed() {
		return pauseButtonPressed;
	}
	
	public void setPauseButtonPressed(boolean b) {
		this.pauseButtonPressed = b;
	}
	
	public void setAdegan(Adegan adegan) {
		this.adegan = adegan;
	}
	
	public Adegan getAdegan() {
		return adegan;
	}

	public void playSoundFx(String key) {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			if (key.equals("default"))
				this.pauseClickSfx.play();
	}
	
	
	public void stopMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.adeganMusicBg.getPosition());
		System.out.println("stop");
		if(this.adeganMusicBg != null) {
			if (this.adeganMusicBg.isPlaying()) {
				if (this.adeganMusicBg.isLooping()) {
					this.adeganMusicBg.setLooping(false);
				}
				this.adeganMusicBg.stop();
				this.adeganMusicBg.dispose();
				this.adeganMusicBg = null;
			}
			this.adeganMusicBg = null;
		}
	}
	public void pauseMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.adeganMusicBg.getPosition());
		System.out.println("stop");
		if(this.adeganMusicBg != null) {
			if (this.adeganMusicBg.isPlaying()) {
				if (this.adeganMusicBg.isLooping()) {
					this.adeganMusicBg.setLooping(false);
				}
				this.adeganMusicBg.pause();
				//this.adeganMusicBg.dispose();
				//this.adeganMusicBg = null;
			}
			//this.adeganMusicBg = null;
		}
	}
	
	public void resume() {
		super.resume();
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.adeganMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				adeganMusicBg.play();
			} else {
				this.adeganMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/adegan.mp3"));
				System.out.println("play music after null");
				adeganMusicBg.setLooping(true);
				adeganMusicBg.setVolume(1.0f);
				adeganMusicBg.play();
			}
		} else if(this.adeganMusicBg != null && this.adeganMusicBg.isPlaying()) {
			this.stopMusic();
		}
	}
	
}
