package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.AboutScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Kelas <code>AboutController</code> adalah controller yang memproses input
 * untuk screen About.
 */
public class AboutController {
	
	/**
	 * Objek <code>aplikasi</code> yang menjalankan controller ini.
	 */
	private Aplikasi app;
	
	/**
	 * Objek <code>AboutScreen</code> yang dikontrol oleh <code>AboutController</code>
	 * ini. Sebuah objek <code>AboutScreen</code> merepresentasikan tampilan halaman
	 * about.
	 */
	private AboutScreen screen;
	
	/**
	 * Touch boundary untuk tombol kembali ke menu utama.
	 */
	private Rectangle backButtonBounds;

	/**
	 * Objek <code>OrthographicCamera</code> milik objek <code>AboutScreen</code> yang
	 * dikontrol oleh kelas ini.
	 */
	private OrthographicCamera cam;
	
	/**
	 * Jendela tampilan milik objek <code>AboutScreen</code> yang
	 * dikontrol oleh kelas ini.
	 */
	private Rectangle viewport;
	
	/**
	 * Constructor dengan parameter sebuah objek <code>AboutScreen</code> 
	 * yang dikontrol oleh <code>AboutController</code> ini. 
	 * @param screen
	 */
	public AboutController(AboutScreen screen){
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = screen.getAplikasi();
		backButtonBounds = this.screen.getBackButtonBounds();
		cam = this.screen.getCam();
		viewport = this.screen.getViewport();
	}
	
	/**
	 * Method yang melakukan pemrosesan input. Dipanggil pada method
	 * <code>render(float delta)</code> milik objek <code>AboutScreen</code>
	 * yang dikontrol kelas ini.
	 */
	public void processInput(){
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			if(OverlapTester.pointInRectangle( backButtonBounds,pos.x, pos.y)){
				screen.stopMusic();
				screen.playSoundFx();
				screen.setBackButtonPressed(true);
			}
		}
		
		if(Gdx.input.isTouched()){
			
		}
		else{
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			if(screen.backButtonIsPressed()){
				screen.setBackButtonPressed(false);
				if(OverlapTester.pointInRectangle( backButtonBounds, pos.x, pos.y)){
					app.getScreen().dispose();
					app.setScreen(new MainMenuScreen(app));

				}
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			app.getScreen().dispose();
			app.setScreen(new MainMenuScreen(app));
		}
	}
}
