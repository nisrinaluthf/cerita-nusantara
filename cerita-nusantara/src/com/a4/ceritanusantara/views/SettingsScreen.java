package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.MainMenuController;
import com.a4.ceritanusantara.controllers.SettingsController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class SettingsScreen extends AbstractScreen {

	private SettingsController controller;
	private Texture background;
	private Texture buttonBackground;
	
	private Texture soundOnTexture;
	private Texture soundOffTexture;
	private Texture musicOnTexture;
	private Texture musicOffTexture;
	
	private Rectangle soundButtonBounds;
	private Rectangle musicButtonBounds;
	
	private boolean soundButtonPressed;
	private boolean musicButtonPressed;
	
	private boolean debug = true;
	
	public SettingsScreen(Aplikasi app, int width, int height) {
		super(app, width, height);
		// TODO Auto-generated constructor stub
		
		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		
		buttonBackground = new Texture(Gdx.files.internal("backgrounds/settings_bg.png"));
		
		soundOnTexture = 
				new Texture(Gdx.files.internal("buttons/toggle_sound_on.png"));
		
		soundOffTexture = 
				new Texture(Gdx.files.internal("buttons/toggle_sound_off.png"));
		
		musicOnTexture = 
				new Texture(Gdx.files.internal("buttons/toggle_music_on.png"));
		
		musicOffTexture = 
				new Texture(Gdx.files.internal("buttons/toggle_music_off.png"));
		
		soundButtonBounds = new Rectangle((VIRTUAL_WIDTH-soundOnTexture.getWidth())/2,
				440, soundOnTexture.getWidth(),
				soundOnTexture.getHeight()); 
				
		musicButtonBounds = new Rectangle((VIRTUAL_WIDTH-musicOnTexture.getWidth())/2,
				440, musicOnTexture.getWidth(),
				musicOnTexture.getHeight());  
		
		soundButtonPressed = false;
		musicButtonPressed = false;
		
		controller = new SettingsController(this);
	}
	
		
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		
		batcher.begin();
			
			batcher.draw(background, 0, 0);
			
			batcher.draw(buttonBackground, (VIRTUAL_WIDTH-buttonBackground.getWidth())/2, 300);
			
			//batcher.draw(soundOnTexture, 
			//		(DEFAULT_WIDTH-soundOnTexture.getWidth())/2, 440);
			
			//batcher.draw(musicOnTexture, 
			//		(DEFAULT_WIDTH-musicOnTexture.getWidth())/2, 160);
			
			
			
		batcher.end();
		
		//--kalo mau ada sfx atau musik nanti di sini aja--
		
		//----------------end of sfx/musik-----------------
		
		//kalo udah bikin controllernya jangan lupa panggil controller.processInput()
		
	}
	
	public Rectangle getSoundButtonBounds(){
		return soundButtonBounds;
	}
	
	public Rectangle getMusicButtonBounds(){
		return musicButtonBounds;
	}
	

}
