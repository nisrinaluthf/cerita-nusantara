package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.SettingsController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	public SettingsScreen(Aplikasi app) {
		super(app);
		// TODO Auto-generated constructor stub

		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		
		buttonBackground = new Texture(Gdx.files.internal("backgrounds/settings_bg.png"));
		
		//if(controller.getSettings().getBoolean("soundOn")) {
			soundOnTexture = 
				new Texture(Gdx.files.internal("buttons/toggle_sound_on.png"));
		
		//} else {
			soundOffTexture =
					new Texture(Gdx.files.internal("buttons/toggle_sound_off.png"));
		//}
				
		//if(controller.getSettings().getBoolean("musicOn")) {
		musicOnTexture = 
				new Texture(Gdx.files.internal("buttons/toggle_music_on.png"));
		
		//} else {
			musicOffTexture =
					new Texture(Gdx.files.internal("buttons/toggle_music_off.png"));
		//}
				
		
		soundButtonBounds = new Rectangle((VIRTUAL_WIDTH-soundOnTexture.getWidth())/2,
				280, soundOnTexture.getWidth(),
				soundOnTexture.getHeight()); 
				
		musicButtonBounds = new Rectangle((VIRTUAL_WIDTH-musicOnTexture.getWidth())/2,
				160, musicOnTexture.getWidth(),
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
			
			if(controller.getSettings().getBoolean("soundOn")) {
				batcher.draw(soundOnTexture, 
						(VIRTUAL_WIDTH-soundOnTexture.getWidth())/2+130, 380);
				
			} else {
				batcher.draw(soundOffTexture, 
						(VIRTUAL_WIDTH-soundOffTexture.getWidth())/2+130, 380);
				
			}
			
			if(controller.getSettings().getBoolean("musicOn")) {
				batcher.draw(musicOnTexture, 
						(VIRTUAL_WIDTH-musicOnTexture.getWidth())/2+130, 220);
						
			} else {
				batcher.draw(musicOffTexture, 
						(VIRTUAL_WIDTH-musicOffTexture.getWidth())/2+130, 220);
					
			}
				
				
			//batcher.draw(soundOnTexture, 
				//	(VIRTUAL_WIDTH-soundOnTexture.getWidth())/2, 440);
			
			//batcher.draw(musicOnTexture, 
			//	(VIRTUAL_WIDTH-musicOnTexture.getWidth())/2, 160);
			
			
			
		batcher.end();
		
		if(debug){
			drawDebug();
		}
		
		controller.processInput();
		
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
	
	public boolean musicButtonIsPressed() {
		return musicButtonPressed;
	}
	
	public boolean soundButtonIsPressed() {
		return soundButtonPressed;
	}
	
	public void setMusicButtonPressed(boolean b) {
		this.musicButtonPressed = b;
	}
	
	public void setSoundButtonPressed(boolean b) {
		this.soundButtonPressed = b;
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
			debugRenderer.end();
		}

}
