package com.a4.ceritanusantara.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.MainMenuController;


/** 
 * Kelas <code>MainMenuScreen</code> menampilkan halaman menu utama aplikasi
 * Kelas ini menerima input dari pengguna dan meneruskannya ke kelas MainMenuController.
 */

public class MainMenuScreen extends AbstractScreen {
	
	/*di sini taro controller, texture(gambar) apa aja yang 
	  perlu ada, sama bounds buat setiap button/clickable (buat 
	  ngecek posisi ngeklik buttonnya pas atau enggak), sama
	  boolean pressed buat ngecek suatu button lagi dipencet
	  apa enggak.
	*/
	
	private MainMenuController controller;
	
	private Texture background;
	
	private Texture playButtonTexture;
	private Texture settingsButtonTexture;
	private Texture playButtonPressedTexture;
	private Texture settingsButtonPressedTexture;
	
	private Rectangle playButtonBounds;
	private Rectangle settingsButtonBounds;
	
	private boolean playButtonPressed;
	private boolean settingsButtonPressed;
	
	private boolean debug = false;
	
	private Music mainMenuMusicBg;
	
	private Sound clickSfx;
	
	//awalnya samain aja sama supernya
	public MainMenuScreen(Aplikasi app){
		super(app);
		
		//inisialisasi semuanya
		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		
		playButtonTexture = 
				new Texture(Gdx.files.internal("buttons/play.png"));
		
		playButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/play_pressed.png"));
		
		settingsButtonTexture = 
				new Texture(Gdx.files.internal("buttons/settings.png"));
		
		settingsButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/settings_pressed.png"));
		
		playButtonBounds = new Rectangle((VIRTUAL_WIDTH-playButtonTexture.getWidth())/2,
				280, playButtonTexture.getWidth(),
				playButtonTexture.getHeight());
		
		settingsButtonBounds = new Rectangle((VIRTUAL_WIDTH-settingsButtonTexture.getWidth())/2,
				160, settingsButtonTexture.getWidth(),
				settingsButtonTexture.getHeight());
		
		playButtonPressed = false;
		settingsButtonPressed = false;
		
		mainMenuMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/Happy_Alley.wav"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.mainMenuMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				mainMenuMusicBg.setLooping(true);
				mainMenuMusicBg.play();
			} else {
				this.mainMenuMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/Happy_Alley.wav"));
				System.out.println("play music after null");
				mainMenuMusicBg.setLooping(true);
				mainMenuMusicBg.play();
			}
		} else if(this.mainMenuMusicBg != null && this.mainMenuMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		clickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		controller = new MainMenuController(this);
		
	}
	
	/* render(float delta) dipanggil terus-terusan setiap
	 * delta detik sekali setelah screen muncul (setelah 
	 * show() dipanggil). Ini method gambar utamanya.
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        cam.update();

        // set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        /*
		 * kalo ngga salah ini supaya background VIRTUALnya
		 * warna hitam, tapi ngga tau juga sih ._.
		 */
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		/*
		 * Setiap mau gambar set dulu projection matrixnya, terus
		 * diawali batcher.begin(), diakhiri batcher.end()
		 */
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			
			/* 
			 * gambar background di sini
			 */
			batcher.draw(background, 0, 0);
			
			/*
			 * gambar button2 di sini.
			 * Kalo buttonnya lagi dipencet gambarnya beda,
			 * nge-glow atau kayak masuk gitu buttonnya
			 * atau gimana.
			 */
			
			if (playButtonPressed) {
				batcher.draw(playButtonPressedTexture, 
						(VIRTUAL_WIDTH-playButtonPressedTexture.getWidth())/2, 280);
				
			}
			else {
				batcher.draw(playButtonTexture, 
						(VIRTUAL_WIDTH-playButtonTexture.getWidth())/2, 280);
			}
			
			if (settingsButtonPressed) {
				batcher.draw(settingsButtonPressedTexture, 
						(VIRTUAL_WIDTH-settingsButtonPressedTexture.getWidth())/2, 160);
			}
			else{
				batcher.draw(settingsButtonTexture, 
						(VIRTUAL_WIDTH-settingsButtonTexture.getWidth())/2, 160);
			}
			
		batcher.end();
		
		/*
		 * kalo mau ngeliat boundnya ada di mana, ubah debug=true 
		 * (line 35 kalo ngga salah)
		 */
		if(debug){
			drawDebug();
		}
		
		//--kalo mau ada sfx atau musik nanti di sini aja--
		//System.out.println(Gdx.app.getPreferences("preferences").getBoolean("musicOn"));
		
		//System.out.println(Gdx.app.getPreferences("preferences").getBoolean("musicON")+"");
		
		//----------------end of sfx/musik-----------------
		
		/*
		 * Selesai gambar panggil method processInput() nya
		 * controller, supaya setiap kali selesai gambar
		 * controller ngecek ada yang terjadi apa enggak.
		 */
		controller.processInput();
		
	}
	
	//rectangle boundsnya digambar kalo mau debug==true
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			if(playButtonPressed){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.rect(playButtonBounds.x, playButtonBounds.y,
					playButtonBounds.width, playButtonBounds.height);
			
			if(settingsButtonPressed){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.rect(settingsButtonBounds.x, settingsButtonBounds.y,
					settingsButtonBounds.width, settingsButtonBounds.height);
		debugRenderer.end();
	}
	
	
	public Rectangle getPlayButtonBounds(){
		return playButtonBounds;
	}
	
	public Rectangle getSettingsButtonBounds(){
		return settingsButtonBounds;
	}
	
	public void setPlayButtonPressed (boolean pressed){
		playButtonPressed = pressed;
	}
	
	public boolean playButtonIsPressed() {
		return playButtonPressed;
	}
	
	public void setSettingsButtonPressed (boolean pressed){
		settingsButtonPressed = pressed;
	}
	
	public boolean settingsButtonIsPressed() {
		return settingsButtonPressed;
	}
	
	public void playSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
		this.clickSfx.play();
	}
	
	
	public void stopMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.mainMenuMusicBg.getPosition());
		System.out.println("stop");
		if(this.mainMenuMusicBg != null) {
			if (this.mainMenuMusicBg.isPlaying()) {
				if (this.mainMenuMusicBg.isLooping()) {
					this.mainMenuMusicBg.setLooping(false);
				}
				this.mainMenuMusicBg.stop();
				this.mainMenuMusicBg.dispose();
				this.mainMenuMusicBg = null;
			}
			this.mainMenuMusicBg = null;
		}
	}
	
	/* 
	 * --ignore dulu deh yak, bingung sebenernya buat apa ._.--
	 * 
	 * show() dipanggil saat screennya ini pertama kali
	 * dibuat, di sini objek2 yang diperluin sama
	 * screen, kayak controller, gambar2, dll dibuat
	 * atau diinisialisasi. 
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		
	}
	
	
}
