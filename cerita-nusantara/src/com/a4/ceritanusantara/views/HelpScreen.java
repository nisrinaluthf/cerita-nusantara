package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.HelpController;
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
 * Kelas <code>HelpScreen</code> menampilkan halaman menu saat jalannya subcerita (adegan, permainan atau kuis) dihentikan sementara.
 * Menu yang ada yaitu restart (mengulang subcerita), resume(menjalankan subcerita), dan exit (kembali ke menu utama)
 * Kelas ini menerima input dari pengguna dan meneruskannya ke controller.
 */

public class HelpScreen extends AbstractScreen {
	private HelpController controller;
	private Screen originScreen;
	
	private Texture background;
	
	private int type;
	private SubCerita subcerita;
	
	private Texture resumeButtonTexture;
	private Texture resumeButtonPressedTexture;
	
	private Rectangle resumeButtonBounds;
	
	private boolean resumeButtonPressed;
	
	private boolean debug = false;
	
	private Music helpMusicBg;
	
	private Sound clickSfx;
	
	
	private Texture help;
	
	public HelpScreen(Aplikasi app, Screen originScreen, Labirin labirin) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.LABIRIN;
		subcerita = labirin;
		help = new Texture(Gdx.files.internal("help/labirin.png"));
		init();
	}
	
	public HelpScreen(Aplikasi app, Screen originScreen, TapGame tapGame) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.TAP_GAME;
		subcerita = tapGame;
		help = new Texture(Gdx.files.internal("help/tapgame.png"));
		init();
	}
	
	public HelpScreen(Aplikasi app, Screen originScreen,
			Puzzle puzzle) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.PUZZLE;
		subcerita = puzzle;
		help = new Texture(Gdx.files.internal("help/puzzle.png"));
		init();
	}
	
	public HelpScreen(Aplikasi app, Screen originScreen,
			RunningGame runningGame) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.RUNNING_GAME;
		subcerita = runningGame;
		help = new Texture(Gdx.files.internal("help/runninggame.png"));
		init();
	}

	private void init(){
		background = new Texture(Gdx.files.internal("help/bg.png"));
		
		resumeButtonTexture = 
				new Texture(Gdx.files.internal("buttons/resume_80.png"));
		
		resumeButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/resume_pressed_80.png"));
		
		resumeButtonBounds = new Rectangle((VIRTUAL_WIDTH-resumeButtonTexture.getWidth())/2+185, 
				140, resumeButtonTexture.getWidth(), resumeButtonTexture.getHeight());
		
		resumeButtonPressed = false;
		
		helpMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_adegan.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.helpMusicBg != null) {
				helpMusicBg.setLooping(true);
				helpMusicBg.play();
			} else {
				this.helpMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_adegan.ogg"));
				helpMusicBg.setLooping(true);
				helpMusicBg.play();
			}
		} else if(this.helpMusicBg != null && this.helpMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		
		clickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		
		controller = new HelpController(this);
	}

	/* render(float delta) dipanggil terus-terusan setiap
	 * delta detik sekali setelah screen muncul (setelah 
	 * show() dipanggil). Ini method gambar utamanya.
	 */
	@Override
	public void render(float delta) {
		cam.update();
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			
			/* 
			 * gambar background di sini
			 */
			batcher.draw(background, 0, 0);
			batcher.draw(help, (VIRTUAL_WIDTH-help.getWidth())/2, 100);
			
			if (resumeButtonPressed) {
				batcher.draw(resumeButtonPressedTexture, 
						(VIRTUAL_WIDTH-resumeButtonPressedTexture.getWidth())/2+185, 140);
				
			}
			else {
				batcher.draw(resumeButtonTexture, 
						(VIRTUAL_WIDTH-resumeButtonTexture.getWidth())/2+185, 140);
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
	
	
	public Rectangle getResumeButtonBounds(){
		return resumeButtonBounds;
	}

	
	public void setResumeButtonPressed (boolean pressed){
		resumeButtonPressed = pressed;
	}
	
	public boolean resumeButtonIsPressed() {
		return resumeButtonPressed;
	}


	
	public void playSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
		this.clickSfx.play();
	}
	
	
	public void stopMusic() {
		if(this.helpMusicBg != null) {
			if (this.helpMusicBg.isPlaying()) {
				if (this.helpMusicBg.isLooping()) {
					this.helpMusicBg.setLooping(false);
				}
				this.helpMusicBg.stop();
				this.helpMusicBg.dispose();
				this.helpMusicBg = null;
			}
			this.helpMusicBg = null;
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
