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
	
	private Texture backButtonTexture;
	private Texture backButtonPressedTexture;

	private Rectangle[] sumateraBounds;
	private Rectangle[] kalimantanBounds;

	private Rectangle backButtonBounds;

	private boolean sumateraPressed;
	private boolean kalimantanPressed;
	private boolean backButtonPressed;

	private Sound clickSfx;
	
	private Sound backClickSfx;
	
	private Music pilihCeritaMusicBg;

	private boolean debug = false;

	public PilihCeritaScreen(Aplikasi app){
		super(app);

		background = new Texture(Gdx.files.internal("backgrounds/pilihcerita_bg.png"));

		sumateraTexture = new Texture(Gdx.files.internal("buttons/sumatera.png"));
		sumateraPressedTexture = new Texture(Gdx.files.internal("buttons/sumatera_pressed.png"));
		kalimantanTexture = new Texture(Gdx.files.internal("buttons/kalimantan.png"));
		kalimantanPressedTexture = new Texture(Gdx.files.internal("buttons/kalimantan_pressed.png"));

		sumateraBounds = new Rectangle[]{
			new Rectangle(20, 400, 60, 60),
			new Rectangle(65, 370, 60, 60),
			new Rectangle(100, 330, 60, 60),
			new Rectangle(130, 280, 80, 80),
			new Rectangle(165, 235, 70, 70),
			new Rectangle(200, 210, 60, 60)
		};

		kalimantanBounds = new Rectangle[]{
			new Rectangle(320, 250, 200, 120),
			new Rectangle(455, 365, 65, 70)
		};
		
		backButtonTexture = new Texture(Gdx.files.internal("buttons/back.png"));
		backButtonPressedTexture = new Texture(Gdx.files.internal("buttons/back_pressed.png"));
		
		backButtonBounds = new Rectangle(0, 0, backButtonTexture.getWidth(),
				backButtonTexture.getHeight());
		
		sumateraPressed = false;
		kalimantanPressed = false;
		backButtonPressed = false;
		
		pilihCeritaMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_cerita.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.pilihCeritaMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				pilihCeritaMusicBg.setLooping(true);
				pilihCeritaMusicBg.play();
			} else {
				this.pilihCeritaMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/pilih_cerita.ogg"));
				System.out.println("play music after null");
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
				batcher.draw(sumateraPressedTexture, 0, 158);
			}
			else{
				batcher.draw(sumateraTexture, 0, 163);
			}

			if(kalimantanPressed){
				batcher.draw(kalimantanPressedTexture, 270, 190);
			}
			else{
				batcher.draw(kalimantanTexture, 275, 200);
			}
			
			if (backButtonPressed) {
				batcher.draw(backButtonPressedTexture, 0, 0);
			}
			else{
				batcher.draw(backButtonTexture, 0, 0);
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
		System.out.println("stop");
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

	public boolean backButtonIsPressed() {
		return backButtonPressed;
	}
	
	public void setBackButtonPressed(boolean b) {
		this.backButtonPressed = b;
	}
}
