package com.a4.ceritanusantara.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.PilihCeritaController;

/** Kelas <code>PilihCeritaScreen</code> menampilkan halaman untuk memilih daerah cerita, yang berupa pulau-pulau.
Kelas ini juga menerima input dari pengguna dan meneruskannya ke controller */

public class PilihCeritaScreen extends AbstractScreen {

	private PilihCeritaController controller;

	private Texture background;
	private Texture sumateraTexture;
	private Texture sumateraPressedTexture;
	private Texture kalimantanTexture;
	private Texture kalimantanPressedTexture;
	private Texture jawaTexture;
	private Texture jawaPressedTexture;
	private Texture baliTexture;
	private Texture baliPressedTexture;
	
	private Texture backButtonTexture;
	private Texture backButtonPressedTexture;

	private Rectangle[] sumateraBounds;
	private Rectangle[] kalimantanBounds;
	private Rectangle[] jawaBounds;
	private Rectangle[] baliBounds;

	private Rectangle backButtonBounds;

	private boolean sumateraPressed;
	private boolean kalimantanPressed;
	private boolean jawaPressed;
	private boolean baliPressed;
	
	private boolean backButtonPressed;

	private Sound clickSfx;
	
	private Sound backClickSfx;
	
	private Music pilihCeritaMusicBg;

	private boolean debug = false;

	public PilihCeritaScreen(Aplikasi app){
		super(app);

		background = new Texture(Gdx.files.internal("select_island/bg.png"));

		sumateraTexture = 
				new Texture(Gdx.files.internal("select_island/sumatera.png"));
		sumateraPressedTexture = 
				new Texture(Gdx.files.internal("select_island/sumatera_pressed.png"));
		kalimantanTexture = 
				new Texture(Gdx.files.internal("select_island/kalimantan.png"));
		kalimantanPressedTexture = 
				new Texture(Gdx.files.internal("select_island/kalimantan_pressed.png"));
		jawaTexture = 
				new Texture(Gdx.files.internal("select_island/jawa.png"));
		jawaPressedTexture = 
				new Texture(Gdx.files.internal("select_island/jawa_pressed.png"));
		baliTexture = 
				new Texture(Gdx.files.internal("select_island/bali.png"));
		baliPressedTexture = 
				new Texture(Gdx.files.internal("select_island/bali_pressed.png"));

		sumateraBounds = new Rectangle[]{
			new Rectangle(95, 442, 60, 60),
			new Rectangle(150, 410, 60, 80),
			new Rectangle(170, 370, 80, 60),
			new Rectangle(220, 320, 80, 80),
			new Rectangle(255, 290, 120, 80),
			new Rectangle(270, 215, 140, 80),
			new Rectangle(315, 180, 140, 80),
			new Rectangle(360, 110, 100, 80)
		};

		kalimantanBounds = new Rectangle[]{
			new Rectangle(620, 165, 160, 40),
			new Rectangle(560, 200, 230, 135),
			new Rectangle(525, 260, 65, 115),
			new Rectangle(730, 275, 105, 180),
			new Rectangle(670, 325, 200, 40),
		};
		
		jawaBounds = new Rectangle[]{
			new Rectangle(480, 60, 120, 60),
			new Rectangle(600, 35, 180, 70),
		};
		
		baliBounds = new Rectangle[]{
				new Rectangle(790, 32, 55, 40)
		};
		
		backButtonTexture = new Texture(Gdx.files.internal("buttons/back.png"));
		backButtonPressedTexture = new Texture(Gdx.files.internal("buttons/back_pressed.png"));
		
		backButtonBounds = new Rectangle(VIRTUAL_WIDTH-backButtonTexture.getWidth()-7,
				7, backButtonTexture.getWidth(), backButtonTexture.getHeight());
		
		sumateraPressed = false;
		kalimantanPressed = false;
		jawaPressed = false;
		baliPressed = false;
		backButtonPressed = false;
		
		pilihCeritaMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_cerita.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.pilihCeritaMusicBg != null) {
				pilihCeritaMusicBg.setLooping(true);
				pilihCeritaMusicBg.play();
			} else {
				this.pilihCeritaMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_cerita.ogg"));
				pilihCeritaMusicBg.setLooping(true);
				pilihCeritaMusicBg.play();
			}
		} else if(this.pilihCeritaMusicBg != null && this.pilihCeritaMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		clickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click2.wav"));
		backClickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		controller = new PilihCeritaController(this);

	}

	/* render(float delta) dipanggil terus-terusan setiap
	 * delta detik sekali setelah screen muncul (setelah 
	 * show() dipanggil). Ini method gambar utamanya.
	 */
	@Override
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

			if(sumateraPressed){
				batcher.draw(sumateraPressedTexture, 80, 100);
			}
			else{
				batcher.draw(sumateraTexture, 80, 100);
			}

			if(kalimantanPressed){
				batcher.draw(kalimantanPressedTexture, 505, 150);
			}
			else{
				batcher.draw(kalimantanTexture, 505, 150);
			}
			
			if(jawaPressed){
				batcher.draw(jawaPressedTexture, 460, 10);
			}
			else{
				batcher.draw(jawaTexture, 460, 10);
			}
			
			if(baliPressed){
				batcher.draw(baliPressedTexture, 790, 15);
			}
			else{
				batcher.draw(baliTexture, 790, 15);
			}
			
			if (backButtonPressed) {
				batcher.draw(backButtonPressedTexture, 
						backButtonBounds.getX(), backButtonBounds.getY());
			}
			else{
				batcher.draw(backButtonTexture, 
						backButtonBounds.getX(), backButtonBounds.getY());
			}

		batcher.end();


		if(debug){
			drawDebug();
		}

		//--kalo mau ada sfx atau musik nanti di sini aja--

		//----------------end of sfx/musik-----------------


		controller.processInput();

	}

	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			for(int i=0; i<sumateraBounds.length; i++){
				debugRenderer.rect(sumateraBounds[i].x, sumateraBounds[i].y,
						sumateraBounds[i].width, sumateraBounds[i].height);
			}

			for(int i=0; i<kalimantanBounds.length; i++){
				debugRenderer.rect(kalimantanBounds[i].x, kalimantanBounds[i].y,
						kalimantanBounds[i].width, kalimantanBounds[i].height);
			}
			
			for(int i=0; i<jawaBounds.length; i++){
				debugRenderer.rect(jawaBounds[i].x, jawaBounds[i].y,
						jawaBounds[i].width, jawaBounds[i].height);
			}
			
			for(int i=0; i<baliBounds.length; i++){
				debugRenderer.rect(baliBounds[i].x, baliBounds[i].y,
						baliBounds[i].width, baliBounds[i].height);
			}

		debugRenderer.end();
	}
	
	public void playSoundFx(String key) {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			if (key.equals("back"))
				this.backClickSfx.play();
			else
			this.clickSfx.play();
	}
	
	
	public void stopMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.pilihCeritaMusicBg.getPosition());
		if(this.pilihCeritaMusicBg != null) {
			if (this.pilihCeritaMusicBg.isPlaying()) {
				if (this.pilihCeritaMusicBg.isLooping()) {
					this.pilihCeritaMusicBg.setLooping(false);
				}
				this.pilihCeritaMusicBg.stop();
				this.pilihCeritaMusicBg.dispose();
				this.pilihCeritaMusicBg = null;
			}
			this.pilihCeritaMusicBg = null;
		}
	}

	public Rectangle[] getSumateraBounds(){
		return sumateraBounds;
	}

	public Rectangle[] getKalimantanBounds(){
		return kalimantanBounds;
	}
	
	public Rectangle[] getJawaBounds(){
		return jawaBounds;
	}
	
	public Rectangle[] getBaliBounds(){
		return baliBounds;
	}
	
	public Rectangle getBackButtonBounds(){
		return backButtonBounds;
	}

	public void setSumateraPressed (boolean pressed){
		sumateraPressed = pressed;
	}

	public boolean sumateraIsPressed() {
		return sumateraPressed;
	}

	public void setKalimantanPressed (boolean pressed){
		kalimantanPressed = pressed;
	}

	public boolean kalimantanIsPressed() {
		return kalimantanPressed;
	}
	
	public void setJawaPressed (boolean pressed){
		jawaPressed = pressed;
	}

	public boolean jawaIsPressed() {
		return jawaPressed;
	}
	
	public void setBaliPressed (boolean pressed){
		baliPressed = pressed;
	}

	public boolean baliIsPressed() {
		return baliPressed;
	}

	public boolean backButtonIsPressed() {
		return backButtonPressed;
	}
	
	public void setBackButtonPressed(boolean b) {
		this.backButtonPressed = b;
	}
}
