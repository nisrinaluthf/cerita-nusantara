package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.PilihSubCeritaController;
import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.models.SubCerita;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/** 
 * Kelas <code>PilihSubCeritaScreen</code> menampilkan halaman menu untuk memilih subcerita dari sebuah cerita.
 * Kelas ini menerima input dari pengguna dan meneruskannya ke PilihSubCeritaontroller.
 */

public class PilihSubCeritaScreen extends AbstractScreen{
	
	
	PilihSubCeritaController controller;
	
	private SubCerita[] subcerita;
	
	private Vector2[] buttonPos;
	private Rectangle[] subCeritaButtonBounds;
	private Rectangle backButtonBounds;
	
	private Texture background;
	private Texture circle;
	private Texture circlePressed;
	private Texture[] icons;
	private Texture backButtonTexture;
	private Texture backButtonPressedTexture;
	private Texture lockTexture;
	
	private boolean[] subCeritaButtonPressed;
	private boolean backButtonPressed;
	private boolean debug = false;
	
	private Sound clickSfx;
	private Sound backClickSfx;
	private Music pilihSubCeritaMusicBg;
	
	public PilihSubCeritaScreen(Aplikasi app, Cerita cerita) {
		super(app);
		subcerita = cerita.getSubCerita();
		background = cerita.getBgSelectScene();
	
		circle = new Texture(Gdx.files.internal("select_scene/circle.png"));	
		circlePressed = new Texture(
				Gdx.files.internal("select_scene/circle_clicked.png"));
		icons = cerita.getSubCeritaIcons();
		lockTexture = new Texture(Gdx.files.internal("select_scene/lock.png"));
		
		buttonPos = new Vector2[]{
				new Vector2(710, 360),
				new Vector2(540, 361),
				new Vector2(375, 361),
				new Vector2(190, 350),
				
				new Vector2(30, 265),
				
				new Vector2(200, 200),
				new Vector2(370, 180),
				new Vector2(535, 180),
				new Vector2(725, 175),
				
				new Vector2(870, 100),
				
				new Vector2(720, 15),
				new Vector2(550, 15)
		};
		
		subCeritaButtonBounds = new Rectangle[buttonPos.length];
		for(int i=0; i<subCeritaButtonBounds.length; i++){
			subCeritaButtonBounds[i] = new Rectangle(buttonPos[i].x, buttonPos[i].y,
					circle.getWidth(), circle.getHeight());
		}
		
		subCeritaButtonPressed = new boolean[buttonPos.length];
		for(int i=0; i<subCeritaButtonPressed.length; i++){
			subCeritaButtonPressed[i] = false;
		}
		
		backButtonTexture = new Texture(Gdx.files.internal("buttons/back.png"));
		backButtonPressedTexture = new Texture(Gdx.files.internal("buttons/back_pressed.png"));
		backButtonBounds = new Rectangle(0, 0, backButtonTexture.getWidth(),
				backButtonTexture.getHeight());
		backButtonPressed = false;
		
		pilihSubCeritaMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_adegan.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.pilihSubCeritaMusicBg != null) {
				pilihSubCeritaMusicBg.setLooping(true);
				pilihSubCeritaMusicBg.play();
			} else {
				this.pilihSubCeritaMusicBg = 
						Gdx.audio.newMusic(Gdx.files.internal("music/pilih_adegan.ogg"));
				pilihSubCeritaMusicBg.setLooping(true);
				pilihSubCeritaMusicBg.play();
			}
		} 
		
		else if(this.pilihSubCeritaMusicBg != null && this.pilihSubCeritaMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		clickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click2.wav"));
		backClickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		controller = new PilihSubCeritaController(this, subcerita);
		
	}
	
	public void render(float delta) {
		cam.update();

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			batcher.draw(background, 0, 0);
			
			for (int i=0; i<subcerita.length; i++){
				
			}
			
			for (int i=0; i<subcerita.length; i++){
				if(subCeritaButtonPressed[i]){
					batcher.draw(circlePressed, buttonPos[i].x, buttonPos[i].y);
				}
				else{
					batcher.draw(circle, buttonPos[i].x, buttonPos[i].y);
				}
				
				if(subcerita[i].isUnlocked()){
					batcher.draw(icons[i], buttonPos[i].x, buttonPos[i].y);
				}
				else{
					batcher.draw(lockTexture, buttonPos[i].x, buttonPos[i].y);
				}
			}
			
			if (backButtonPressed) {
				batcher.draw(backButtonPressedTexture, 20, 10);
			}
			else{
				batcher.draw(backButtonTexture, 20, 10);
			}
				
		batcher.end();
		
		if(debug){
			drawDebug();
		}
		
		controller.processInput();
	}
	
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			for(int i=0; i<subCeritaButtonBounds.length; i++){
				debugRenderer.rect(subCeritaButtonBounds[i].x, subCeritaButtonBounds[i].y,
						subCeritaButtonBounds[i].width, subCeritaButtonBounds[i].height);
			}
		debugRenderer.end();
	}
	
	public Rectangle[] getSubCeritaButtonBounds(){
		return subCeritaButtonBounds;
	}
	
	public boolean isSubCeritaButtonPressed(int i){
		return subCeritaButtonPressed[i];
	}
	
	public void setSubCeritaButtonPressed(int i, boolean pressed){
		subCeritaButtonPressed[i] = pressed;
	}
	
	public SubCerita getSubCerita(int i){
		return subcerita[i];
	}
	
	public void playSoundFx(String key) {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			if (key.equals("back"))
				this.backClickSfx.play();
			else
			this.clickSfx.play();
	}
	
	public void stopMusic() {
		if(this.pilihSubCeritaMusicBg != null) {
			if (this.pilihSubCeritaMusicBg.isPlaying()) {
				if (this.pilihSubCeritaMusicBg.isLooping()) {
					this.pilihSubCeritaMusicBg.setLooping(false);
				}
				this.pilihSubCeritaMusicBg.stop();
				this.pilihSubCeritaMusicBg.dispose();
				this.pilihSubCeritaMusicBg = null;
			}
			this.pilihSubCeritaMusicBg = null;
		}
	}
	
	public Rectangle getBackButtonBounds(){
		return backButtonBounds;
	}
	
	public boolean backButtonIsPressed() {
		return backButtonPressed;
	}
	
	public void setBackButtonPressed(boolean b) {
		this.backButtonPressed = b;
	}	
}