package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.PauseController;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.Puzzle;
import com.a4.ceritanusantara.models.RunningGame;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/** 
 * Kelas <code>PauseScreen</code> menampilkan halaman menu saat jalannya subcerita (adegan, permainan atau kuis) dihentikan sementara.
 * Menu yang ada yaitu restart (mengulang subcerita), resume(menjalankan subcerita), dan exit (kembali ke menu utama)
 * Kelas ini menerima input dari pengguna dan meneruskannya ke controller.
 */

public class PauseScreen extends AbstractScreen {
	private PauseController controller;
	private Screen originScreen;
	
	private Texture background;
	private Texture panel;
	
	private int type;
	private SubCerita subcerita;
	
	private Texture resumeButtonTexture;
	private Texture restartButtonTexture;
	private Texture exitButtonTexture;
	private Texture exitButtonPressedTexture;
	private Texture resumeButtonPressedTexture;
	private Texture restartButtonPressedTexture;
	private Texture settingButtonTexture;
	private Texture settingButtonPressedTexture;

	
	private Rectangle resumeButtonBounds;
	private Rectangle restartButtonBounds;
	private Rectangle exitButtonBounds;
	private Rectangle settingButtonBounds;
	
	private boolean resumeButtonPressed;
	private boolean restartButtonPressed;
	private boolean exitButtonPressed;
	private boolean settingButtonPressed;
	
	private boolean debug = false;
	
	private Music pauseMusicBg;
	
	private Sound clickSfx;
	

	public PauseScreen(Aplikasi app, Screen originScreen, Adegan adegan) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.ADEGAN;
		subcerita = adegan;
		init();
	}
	
	public PauseScreen(Aplikasi app, Screen originScreen, Labirin labirin) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.LABIRIN;
		subcerita = labirin;
		init();
	}
	
	public PauseScreen(Aplikasi app, Screen originScreen, TapGame tapGame) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.TAP_GAME;
		subcerita = tapGame;
		init();
	}
	
	public PauseScreen(Aplikasi app, Screen originScreen, Puzzle puzzle) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.PUZZLE;
		subcerita = puzzle;
		init();
	}
	
	public PauseScreen(Aplikasi app, Screen originScreen, Kuis kuis) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.KUIS;
		subcerita = kuis;
		init();
	}
	
	public PauseScreen(Aplikasi app, Screen originScreen, RunningGame runningGame) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.RUNNING_GAME;
		subcerita = runningGame;
		init();
		// TODO Auto-generated constructor stub
	}
	
	private void init(){
		setScreenType(1);
		background = new Texture(Gdx.files.internal("backgrounds/bg.png"));
		panel = new Texture(Gdx.files.internal("backgrounds/pause_bg.png"));
		
		resumeButtonTexture = 
				new Texture(Gdx.files.internal("buttons/resume_140.png"));
		
		resumeButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/resume_pressed_140.png"));
		
		restartButtonTexture = 
				new Texture(Gdx.files.internal("buttons/restart_120.png"));
		
		restartButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/restart_pressed_120.png"));
		
		exitButtonTexture = 
				new Texture(Gdx.files.internal("buttons/mainmenu_120.png"));
		
		exitButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/mainmenu_pressed_120.png"));
		
		resumeButtonBounds = new Rectangle((VIRTUAL_WIDTH-resumeButtonTexture.getWidth())/2,
				230, resumeButtonTexture.getWidth(),
				resumeButtonTexture.getHeight());
		
		restartButtonBounds = new Rectangle((VIRTUAL_WIDTH-restartButtonTexture.getWidth())/2+150,
				80, restartButtonTexture.getWidth(),
				restartButtonTexture.getHeight());
		
		exitButtonBounds = new Rectangle((VIRTUAL_WIDTH-exitButtonTexture.getWidth())/2-150,
				80, exitButtonTexture.getWidth(),
				exitButtonTexture.getHeight());
						
		settingButtonTexture = 
				new Texture(Gdx.files.internal("buttons/settings_button.png"));
		
		settingButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/settings_button_pressed.png"));

		settingButtonBounds = new Rectangle((VIRTUAL_WIDTH-settingButtonTexture.getWidth())/2,
				80, settingButtonTexture.getWidth(),
				settingButtonTexture.getHeight());

		
		
		resumeButtonPressed = false;
		restartButtonPressed = false;
		exitButtonPressed = false;
		settingButtonPressed = false;
 		
		pauseMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_adegan.ogg"));

		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.pauseMusicBg != null) {
				pauseMusicBg.setLooping(true);
				pauseMusicBg.play();
			} else {
				this.pauseMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_adegan.mp3"));
				pauseMusicBg.setLooping(true);
				pauseMusicBg.play();
			}
		} else if(this.pauseMusicBg != null && this.pauseMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		clickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		
		controller = new PauseController(this);
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
			batcher.draw(panel, (VIRTUAL_WIDTH-panel.getWidth())/2, 
					(VIRTUAL_HEIGHT-panel.getHeight())/2);
			
			/*
			 * gambar button2 di sini.
			 * Kalo buttonnya lagi dipencet gambarnya beda,
			 * nge-glow atau kayak masuk gitu buttonnya
			 * atau gimana.
			 */
			
			if (resumeButtonPressed) {
				batcher.draw(resumeButtonPressedTexture, 
						resumeButtonBounds.x, resumeButtonBounds.y);
				
			}
			else {
				batcher.draw(resumeButtonTexture, 
						resumeButtonBounds.x, resumeButtonBounds.y);
			}
			
			if (restartButtonPressed) {
				batcher.draw(restartButtonPressedTexture, 
						restartButtonBounds.x, restartButtonBounds.y);
			}
			else{
				batcher.draw(restartButtonTexture, 
						restartButtonBounds.x, restartButtonBounds.y);
			}
			
			if (exitButtonPressed) {
				batcher.draw(exitButtonPressedTexture, 
						exitButtonBounds.x, exitButtonBounds.y);
			}
			else{
				batcher.draw(exitButtonTexture, 
						exitButtonBounds.x, exitButtonBounds.y);
			}
			
			if (settingButtonPressed) {
				batcher.draw(settingButtonPressedTexture, 
						settingButtonBounds.x, settingButtonBounds.y);
			}
			else{
				batcher.draw(settingButtonTexture, 
						settingButtonBounds.x, settingButtonBounds.y);
 			}

			
			
		batcher.end();
		
		/*
		 * kalo mau ngeliat boundnya ada di mana, ubah debug=true 
		 */
		if(debug){
			drawDebug();
		}
		
		//--kalo mau ada sfx atau musik nanti di sini aja--
		
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
			if(resumeButtonPressed){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.rect(resumeButtonBounds.x, resumeButtonBounds.y,
					resumeButtonBounds.width, resumeButtonBounds.height);
			
			if(restartButtonPressed){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.rect(restartButtonBounds.x, restartButtonBounds.y,
					restartButtonBounds.width, restartButtonBounds.height);
			
			if(exitButtonPressed){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.rect(exitButtonBounds.x, exitButtonBounds.y,
					exitButtonBounds.width, exitButtonBounds.height);
		debugRenderer.end();
	}
	
	public int getType(){
		return type;
	}
	
	public SubCerita getSubCerita(){
		return subcerita;
	}
	
	public Screen getOriginScreen(){
		return originScreen;
	}
	
	public Rectangle getSettingButtonBounds(){
		return settingButtonBounds;
	}
	
	public void setSettingButtonPressed (boolean pressed){
		settingButtonPressed = pressed;
	}
	
	public boolean settingButtonIsPressed() {
		return settingButtonPressed;
	}

	
	public Rectangle getResumeButtonBounds(){
		return resumeButtonBounds;
	}
	
	public Rectangle getRestartButtonBounds(){
		return restartButtonBounds;
	}
	
	public Rectangle getExitButtonBounds(){
		return exitButtonBounds;
	}
	
	public void setResumeButtonPressed (boolean pressed){
		resumeButtonPressed = pressed;
	}
	
	public boolean resumeButtonIsPressed() {
		return resumeButtonPressed;
	}
	
	public void setRestartButtonPressed (boolean pressed){
		restartButtonPressed = pressed;
	}
	
	public boolean restartButtonIsPressed() {
		return restartButtonPressed;
	}
	
	public void setExitButtonPressed (boolean pressed){
		exitButtonPressed = pressed;
	}
	
	public boolean exitButtonIsPressed() {
		return exitButtonPressed;
	}

	
	public void playSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
		this.clickSfx.play();
	}
	
	
	public void stopMusic() {
		if(this.pauseMusicBg != null) {
			if (this.pauseMusicBg.isPlaying()) {
				if (this.pauseMusicBg.isLooping()) {
					this.pauseMusicBg.setLooping(false);
				}
				this.pauseMusicBg.stop();
				//this.pauseMusicBg.dispose();
				//this.pauseMusicBg = null;
			}
			//this.pauseMusicBg = null;
		}
	}
	
	 public void pauseMusic() {
		if(this.pauseMusicBg != null) {
			if (this.pauseMusicBg.isPlaying()) {
				if (this.pauseMusicBg.isLooping()) {
					this.pauseMusicBg.setLooping(false);
				}
				this.pauseMusicBg.pause();
				//this.adeganMusicBg.dispose();
				//this.adeganMusicBg = null;
			}
			//this.adeganMusicBg = null;
		}
	}
	
	public void resume() {
		super.resume();
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.pauseMusicBg != null) {
				pauseMusicBg.play();
			} else {
				this.pauseMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_adegan.ogg"));
				pauseMusicBg.setLooping(true);
				pauseMusicBg.setVolume(1.0f);
				pauseMusicBg.play();
			}
		} else if(this.pauseMusicBg != null && this.pauseMusicBg.isPlaying()) {
			this.stopMusic();
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
	
	public void dispose() {
		super.dispose();
		background.dispose();
		panel.dispose();
		resumeButtonTexture.dispose();
		restartButtonTexture.dispose();
		exitButtonTexture.dispose();
		exitButtonPressedTexture.dispose();
		resumeButtonPressedTexture.dispose();
		restartButtonPressedTexture.dispose();
		settingButtonTexture.dispose();
		settingButtonPressedTexture.dispose();
		pauseMusicBg.dispose();
		clickSfx.dispose();
	}
	
	
}
