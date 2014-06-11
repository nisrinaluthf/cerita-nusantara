package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.SettingsController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/**
 * Kelas <code>SettingsScreen</code> adalah kelas untuk mengatur tampilan menu setting (pengaturan musik dan suara)
 * Kelas ini menerima input dari pengguna dan meneruskannya ke kelas SettingsController.
 */
public class SettingsScreen extends AbstractScreen {

	private SettingsController controller;
	private Texture background;
	private Texture buttonBackground;
	
	private Texture soundOnTexture;
	private Texture soundOffTexture;
	private Texture musicOnTexture;
	private Texture musicOffTexture;
	
	private Texture backButtonTexture;
	private Texture backButtonPressedTexture;
	
	private Rectangle soundButtonBounds;
	private Rectangle musicButtonBounds;
	private Rectangle backButtonBounds;
	
	private boolean soundButtonPressed;
	private boolean musicButtonPressed;
	private boolean backButtonPressed;
	
	private boolean debug = false;
	private BitmapFont font;
	
	private Music settingsMusicBg;
	
	private Sound clickSfx;
	
	private Screen originalScreen;
	
	public SettingsScreen(Aplikasi app, Screen origin) {

		super(app);
		originalScreen = origin;
		background = new Texture(Gdx.files.internal("backgrounds/bg.png"));
		
		buttonBackground = new Texture(Gdx.files.internal("backgrounds/settings_bg.png"));
		soundOnTexture = 
				new Texture(Gdx.files.internal("buttons/sound_on.png"));
		soundOffTexture =
					new Texture(Gdx.files.internal("buttons/sound_off.png"));
		musicOnTexture = 
				new Texture(Gdx.files.internal("buttons/music_on.png"));
		musicOffTexture =
					new Texture(Gdx.files.internal("buttons/music_off.png"));
		
		backButtonTexture = new Texture(Gdx.files.internal("buttons/back.png"));
		backButtonPressedTexture = new Texture(Gdx.files.internal("buttons/back_pressed.png"));
		
		soundButtonBounds = new Rectangle(520, 300, 
				soundOnTexture.getWidth(), soundOnTexture.getHeight()); 
				
		musicButtonBounds = new Rectangle(520, 160, 
				musicOnTexture.getWidth(), musicOnTexture.getHeight());  
		
		backButtonBounds = new Rectangle(7,
				7, backButtonTexture.getWidth(),
				backButtonTexture.getHeight());
		
		soundButtonPressed = false;
		musicButtonPressed = false;
		backButtonPressed = false;
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		
		settingsMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/main_menu.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.settingsMusicBg != null) {
				settingsMusicBg.setLooping(true);
				settingsMusicBg.play();
			} 
		} else if(this.settingsMusicBg != null && this.settingsMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		clickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		controller = new SettingsController(this);
	}
	
		
	@Override
	public void render(float delta) {
		cam.update();

        // set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			
			batcher.draw(background, 0, 0);
			
			batcher.draw(buttonBackground, (VIRTUAL_WIDTH-buttonBackground.getWidth())/2, 100);
			
			if(controller.getSettings().getBoolean("soundOn")) {
				batcher.draw(soundOnTexture, 
						soundButtonBounds.getX(), soundButtonBounds.getY());
			} 
			else {
				batcher.draw(soundOffTexture, 
						soundButtonBounds.getX(), soundButtonBounds.getY());
			}
			
			if(controller.getSettings().getBoolean("musicOn")) {
				batcher.draw(musicOnTexture, 
						musicButtonBounds.getX(), musicButtonBounds.getY());
			} 
			else {
				batcher.draw(musicOffTexture, 
						musicButtonBounds.getX(), musicButtonBounds.getY());
			}
			
			if (backButtonPressed) {
				batcher.draw(backButtonPressedTexture,
						backButtonBounds.getX(), backButtonBounds.getY());
			}
			else{
				batcher.draw(backButtonTexture, 
						backButtonBounds.getX(), backButtonBounds.getY());
			}
			
			font.setColor(1f, 0.0f, 0.0f, 1);
			font.draw(batcher, "Suara", 390, 370);
			font.draw(batcher, "Musik", 390, 230);
			
		batcher.end();
		
		if(debug){
			drawDebug();
		}
		
		controller.processInput();
		
		//--kalo mau ada sfx atau musik nanti di sini aja--
		
		
		
		//----------------end of sfx/musik-----------------
		
		//kalo udah bikin controllernya jangan lupa panggil controller.processInput()
		
	}
	
	public Screen getOriginScreen () {
		return originalScreen;
	}

	public Rectangle getSoundButtonBounds(){
		return soundButtonBounds;
	}
	
	public Rectangle getMusicButtonBounds(){
		return musicButtonBounds;
	}
	
	public Rectangle getBackButtonBounds(){
		return backButtonBounds;
	}
	
	public boolean musicButtonIsPressed() {
		return musicButtonPressed;
	}
	
	public boolean soundButtonIsPressed() {
		return soundButtonPressed;
	}
	
	public boolean backButtonIsPressed() {
		return backButtonPressed;
	}
	
	public void setMusicButtonPressed(boolean b) {
		this.musicButtonPressed = b;
	}
	
	public void setSoundButtonPressed(boolean b) {
		this.soundButtonPressed = b;
	}
	
	public void setBackButtonPressed(boolean b) {
		this.backButtonPressed = b;
	}
	
	public void playSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
		this.clickSfx.play();
	}
	
	public void stopMusic() {
		if(this.settingsMusicBg != null) {
		if (this.settingsMusicBg.isPlaying()) {
				if (this.settingsMusicBg.isLooping()) {
					this.settingsMusicBg.setLooping(false);
				}
				this.settingsMusicBg.stop();
				//this.settingsMusicBg.dispose();
				//this.settingsMusicBg = null;
		}
			//this.settingsMusicBg = null;
		}
	}
	
	public void updateMusic() {
		if(this.settingsMusicBg == null || !this.settingsMusicBg.isPlaying()) {
			if (this.settingsMusicBg != null) {
				settingsMusicBg.setLooping(true);
				settingsMusicBg.play();
			} else {
			this.settingsMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/main_menu.ogg"));
				settingsMusicBg.setLooping(true);
				settingsMusicBg.play();
			}
		} else {
			this.stopMusic();
		}
	}
	
	//rectangle boundsnya digambar kalo mau debug==true
		private void drawDebug(){
			debugRenderer.setProjectionMatrix(cam.combined);
			debugRenderer.begin(ShapeType.Rectangle);
				if(soundButtonPressed){
					debugRenderer.setColor(new Color(1, 1, 0, 1));
				}
				else{
					debugRenderer.setColor(new Color(1, 0, 0, 1));
				}
				debugRenderer.rect(soundButtonBounds.x, soundButtonBounds.y,
						soundButtonBounds.width, soundButtonBounds.height);
				
				if(musicButtonPressed){
					debugRenderer.setColor(new Color(1, 1, 0, 1));
				}
				else{
					debugRenderer.setColor(new Color(1, 0, 0, 1));
				}
				debugRenderer.rect(musicButtonBounds.x, musicButtonBounds.y,
						musicButtonBounds.width, musicButtonBounds.height);
				
				if(backButtonPressed){
					debugRenderer.setColor(new Color(1, 1, 0, 1));
				}
				else{
					debugRenderer.setColor(new Color(1, 0, 0, 1));
				}
				debugRenderer.rect(backButtonBounds.x, backButtonBounds.y,
						backButtonBounds.width, backButtonBounds.height);
			debugRenderer.end();
		}
		
	public void dispose(){
		background.dispose();
		buttonBackground.dispose();
		
		soundOnTexture.dispose();
		soundOffTexture.dispose();
		musicOnTexture.dispose();
		musicOffTexture.dispose();
		
		backButtonTexture.dispose();
		backButtonPressedTexture.dispose();
		
		font.dispose();
		settingsMusicBg.dispose();
		clickSfx.dispose();
		super.dispose();
	}

}
