package com.a4.ceritanusantara.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.MainMenuController;

public class MainMenuScreen extends AbstractScreen {
	
	/*di sini taro controller, texture(gambar) apa aja yang 
	  perlu ada, sama bounds buat setiap button/clickable (buat 
	  ngecek posisi ngeklik buttonnya pas atau enggak), sama
	  boolean pressed buat ngecek suatu button lagi dipencet
	  apa enggak.
	*/
	MainMenuController controller;
	
	Texture background;
	
	Texture playButtonTexture;
	Texture settingsButtonTexture;
	Texture playButtonPressedTexture;
	Texture settingsButtonPressedTexture;
	
	Rectangle playButtonBounds;
	Rectangle settingsButtonBounds;
	
	boolean playButtonPressed;
	boolean settingsButtonPressed;
	
	//awalnya samain aja sama supernya
	public MainMenuScreen(Aplikasi app){
		super(app);
		
		//setiap kali di-show dia inisialisasi
		controller = new MainMenuController(this);
		
		background = new Texture(Gdx.files.internal("backgrounds/mainmenu_bg.png"));
		
		playButtonTexture = 
				new Texture(Gdx.files.internal("buttons/play.png"));
		
		playButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/play_pressed.png"));
		
		settingsButtonTexture = 
				new Texture(Gdx.files.internal("buttons/settings.png"));
		
		settingsButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/settings_pressed.png"));
		
		playButtonBounds = new Rectangle((DEFAULT_WIDTH-playButtonTexture.getWidth())/2,
				(DEFAULT_HEIGHT-playButtonTexture.getHeight())/2, playButtonTexture.getWidth(),
				playButtonTexture.getHeight());
		
		playButtonBounds = new Rectangle((DEFAULT_WIDTH-settingsButtonTexture.getWidth())/2,
				(DEFAULT_HEIGHT-settingsButtonTexture.getHeight())/2, settingsButtonTexture.getWidth(),
				settingsButtonTexture.getHeight());
		
		playButtonPressed = false;
		settingsButtonPressed = false;
	}
	
	/* render(float delta) dipanggil terus-terusan setiap
	 * delta detik sekali setelah screen muncul (setelah 
	 * show() dipanggil). Ini method gambar utamanya.
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		/*
		 * kalo ngga salah ini supaya background defaultnya
		 * warna hitam, tapi ngga tau juga sih ._.
		 */
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		
		/*
		 * Setiap gambar diawali batcher.begin()
		 * ,diakhiri batcher.end()
		 */
		batcher.begin();
			/* 
			 * gambar background di sini
			 */
			batcher.draw(background, 0, 0);
			
			/*
			 * gambar button di sini.
			 * Kalo buttonnya lagi dipencet gambarnya beda,
			 * nge-glow atau kayak masuk gitu buttonnya
			 * atau gimana.
			 */
			if (playButtonPressed) {
				batcher.draw(playButtonTexture, (width-playButtonTexture.getWidth())/2, 600);
			}
			else {
				batcher.draw(playButtonPressedTexture, 
						(width-playButtonPressedTexture.getWidth())/2, 600);
			}
			
			if (settingsButtonPressed) {
				batcher.draw(settingsButtonTexture, (width-settingsButtonTexture.getWidth())/2, 200);
			}
			else{
				batcher.draw(settingsButtonPressedTexture, 
						(width-settingsButtonPressedTexture.getWidth())/2, 200);
			}
			
			if(debug){
				drawDebug();
			}
			
		batcher.end();
		
		/*
		 * Selesai gambar panggil method processInput() nya
		 * controller, supaya setiap kali selesai gambar
		 * controller ngecek ada yang terjadi apa enggak.
		 */
		controller.processInput();
		
	}
	
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(playButtonBounds.x, playButtonBounds.y,
					playButtonBounds.width, playButtonBounds.height);
			debugRenderer.rect(settingsButtonBounds.x, settingsButtonBounds.y,
					settingsButtonBounds.width, settingsButtonBounds.height);
		debugRenderer.end();
	}
	
	public void setPlayButtonPressed (boolean pressed){
		playButtonPressed = pressed;
	}
	
	public void setSettingsButtonPressed (boolean pressed){
		settingsButtonPressed = pressed;
	}
	
	/* show() dipanggil saat screennya ini pertama kali
	 * dibuat, di sini objek2 yang diperluin sama
	 * screen, kayak controller, gambar2, dll dibuat
	 * atau diinisialisasi. 
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		
	}
	
}
