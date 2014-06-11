package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.AboutController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/**
 * Kelas <code>AboutScreen</code> adalah kelas yang mengatur tampilan
 * dari halaman about.
 */
public class AboutScreen extends AbstractScreen {
	
	/**
	 * Controller yang memproses masukan dari kelas ini.
	 */
	private AboutController controller;
	
	/**
	 * Background dari tampilan about.
	 * 
	 */
	private Texture background;
	
	/**
	 * Panel background dari tampilan about.
	 */
	private Texture panel;
	
	/**
	 * Gambar tombol kembali.
	 */
	private Texture backButtonTexture;
	
	/**
	 * Gambar tombol kembali saat ditekan.
	 */
	private Texture backButtonPressedTexture;
	
	/**
	 * Boolean yang menyimpan debugging state.
	 */
	private boolean debug = false;
	
	/**
	 * Musik yang dimainkan pada saat about screen ditampilkan.
	 */
	private Music settingsMusicBg;
	
	/**
	 * Sound FX yang dimainkan pada saat tombol kembali ditekan.
	 */
	private Sound clickSfx;
	
	/**
	 * Boolean yang menyimpan apakah tombol kembali sedang ditekan
	 * atau tidak.
	 */
	private boolean backButtonPressed;
	
	/**
	 * Touch boundary dari tombol kembali ke menu utama.
	 */
	private Rectangle backButtonBounds;
	
	/**
	 * Constructor dengan parameter aplikasi yang memanggil screen ini.
	 * @param app
	 */
	public AboutScreen(Aplikasi app) {
		super(app);
		background = new Texture(Gdx.files.internal("backgrounds/bg.png"));
		
		panel = new Texture(Gdx.files.internal("backgrounds/about.png"));
		
		backButtonTexture = new Texture(Gdx.files.internal("buttons/back.png"));
		backButtonPressedTexture = new Texture(Gdx.files.internal("buttons/back_pressed.png"));
		
		backButtonBounds = new Rectangle(7,
				7, backButtonTexture.getWidth(),
				backButtonTexture.getHeight());

		backButtonPressed = false;
		
		settingsMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/main_menu.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.settingsMusicBg != null) {
				settingsMusicBg.setLooping(true);
				settingsMusicBg.play();
			} 
		} else if(this.settingsMusicBg != null && this.settingsMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		clickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));	
		controller = new AboutController(this);
	}
	
	/**
	 * Method render adalah method utama yang menggambar tampilan layar.	
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
			
			batcher.draw(background, 0, 0);
			
			batcher.draw(panel, (VIRTUAL_WIDTH-panel.getWidth())/2, 
					(VIRTUAL_HEIGHT-panel.getHeight())/2);
			
			
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
		
		controller.processInput();	
	}
	
	/**
	 * Mengembalikan touch boundary dari tombol kembali ke menu utama.
	 * @return
	 */
	public Rectangle getBackButtonBounds(){
		return backButtonBounds;
	}
	
	/**
	 * Mengembalikan state dari tombol kembali ke menu utama.
	 * @return
	 */
	public boolean backButtonIsPressed() {
		return backButtonPressed;
	}
	
	/**
	 * Set state dari tombol kembali ke menu utama.
	 * @param b
	 */
	public void setBackButtonPressed(boolean b) {
		this.backButtonPressed = b;
	}
	
	/**
	 * Memainkan sound FX suara tombol yang ditekan.
	 */
	public void playSoundFx() {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
		this.clickSfx.play();
	}
	
	/**
	 * Menghentikan musik yang dimainkan.
	 */
	public void stopMusic() {
		if(this.settingsMusicBg != null) {
			if (this.settingsMusicBg.isPlaying()) {
					if (this.settingsMusicBg.isLooping()) {
						this.settingsMusicBg.setLooping(false);
					}
					this.settingsMusicBg.stop();
			}
		}
	}
	
	/**
	 * Melakukan update musik yang sedang dimainkan.
	 */
	public void updateMusic() {
		if(this.settingsMusicBg == null || !this.settingsMusicBg.isPlaying()) {
			if (this.settingsMusicBg != null) {
				settingsMusicBg.setLooping(true);
				settingsMusicBg.play();
			} else {
			this.settingsMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/main_menu.ogg"));
				settingsMusicBg.setLooping(true);
				settingsMusicBg.play();
			}
		} else {
			this.stopMusic();
		}
	}
	
	/**
	 * Menggambar touch boundary.
	 */
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
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
	
	/**
	 * Mengeluarkan aset-aset berupa gambar dan musik yang digunakan dalam
	 * screen ini dari memori.
	 */
	public void dispose(){
		super.dispose();
		background.dispose();
		panel.dispose();
		backButtonTexture.dispose();
		backButtonPressedTexture.dispose();
		settingsMusicBg.dispose();
		clickSfx.dispose();
	}
}
