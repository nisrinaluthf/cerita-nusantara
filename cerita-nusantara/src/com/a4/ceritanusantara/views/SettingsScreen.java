package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.SettingsController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	
	private Texture backButtonTexture;
	private Texture backButtonPressedTexture;
	
	private Rectangle soundButtonBounds;
	private Rectangle musicButtonBounds;
	private Rectangle backButtonBounds;
	
	private boolean soundButtonPressed;
	private boolean musicButtonPressed;
	private boolean backButtonPressed;
	
	private boolean debug = true;
	private BitmapFont font;
	
	public SettingsScreen(Aplikasi app) {
		super(app);
		// TODO Auto-generated constructor stub

		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		
		buttonBackground = new Texture(Gdx.files.internal("backgrounds/settings_bg.png"));
		
		//if(controller.getSettings().getBoolean("soundOn")) {
			soundOnTexture = 
				new Texture(Gdx.files.internal("buttons/toggle_on.png"));
		
		//} else {
			soundOffTexture =
					new Texture(Gdx.files.internal("buttons/toggle_off.png"));
		//}
				
		//if(controller.getSettings().getBoolean("musicOn")) {
		musicOnTexture = 
				new Texture(Gdx.files.internal("buttons/toggle_on.png"));
		
		//} else {
			musicOffTexture =
					new Texture(Gdx.files.internal("buttons/toggle_off.png"));
		//}
		backButtonTexture = new Texture(Gdx.files.internal("buttons/back.png"));
		backButtonPressedTexture = new Texture(Gdx.files.internal("buttons/back_pressed.png"));
				
		
		soundButtonBounds = new Rectangle((VIRTUAL_WIDTH-soundOnTexture.getWidth())/2,
				280, soundOnTexture.getWidth(),
				soundOnTexture.getHeight()); 
				
		musicButtonBounds = new Rectangle((VIRTUAL_WIDTH-musicOnTexture.getWidth())/2,
				160, musicOnTexture.getWidth(),
				musicOnTexture.getHeight());  
		
		backButtonBounds = new Rectangle(0,
				0, backButtonTexture.getWidth(),
				backButtonTexture.getHeight());
		
		soundButtonPressed = false;
		musicButtonPressed = false;
		backButtonPressed = false;
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		controller = new SettingsController(this);
	}
	
		
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		cam.update();

        // set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			
			batcher.draw(background, 0, 0);
			
			batcher.draw(buttonBackground, (VIRTUAL_WIDTH-buttonBackground.getWidth())/2, 145);
			
			if(controller.getSettings().getBoolean("soundOn")) {
				batcher.draw(soundOnTexture, 
						(VIRTUAL_WIDTH-soundOnTexture.getWidth())/2, 280);
				
			} else {
				batcher.draw(soundOffTexture, 
						(VIRTUAL_WIDTH-soundOffTexture.getWidth())/2, 280);
				
			}
			
			if(controller.getSettings().getBoolean("musicOn")) {
				batcher.draw(musicOnTexture, 
						(VIRTUAL_WIDTH-musicOnTexture.getWidth())/2, 160);
						
			} else {
				batcher.draw(musicOffTexture, 
						(VIRTUAL_WIDTH-musicOffTexture.getWidth())/2, 160);
					
			}
			
			if (backButtonPressed) {
				batcher.draw(backButtonPressedTexture,0, 0);
			}
			else{
				batcher.draw(backButtonTexture, 0, 0);
			}
				
				
			//batcher.draw(soundOnTexture, 
				//	(VIRTUAL_WIDTH-soundOnTexture.getWidth())/2, 440);
			
			//batcher.draw(musicOnTexture, 
			//	(VIRTUAL_WIDTH-musicOnTexture.getWidth())/2, 160);
			font.setColor(3);
			font.draw(batcher, "Suara", (VIRTUAL_WIDTH-soundOnTexture.getWidth())/2, 350);
			font.draw(batcher, "Musik", (VIRTUAL_WIDTH-soundOnTexture.getWidth())/2, 230);
			
			
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

}
