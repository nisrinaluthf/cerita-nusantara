package com.a4.ceritanusantara.views;

import com.badlogic.gdx.graphics.Texture;
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
	
	Rectangle playButttonBounds;
	Rectangle settingsButttonBounds;
	
	boolean playButtonPressed;
	boolean settingsButtonPressed;
	
	//constructornya samain aja sama supernya
	public MainMenuScreen(Aplikasi app){
		super(app);
	}
	
	/* render(float delta) dipanggil terus-terusan setiap
	 * delta detik sekali setelah screen muncul (setelah 
	 * show() dipanggil). Ini method gambar utamanya.
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

		/* 
		 * gambar background di sini
		 */
		
		
		/*
		 * gambar button di sini.
		 * Kalo buttonnya lagi dipencet gambarnya beda,
		 * nge-glow atau kayak masuk gitu buttonnya
		 * atau gimana.
		 */
		if (playButtonPressed) {
			
		}
		else {
			
		}
		
		if (settingsButtonPressed) {
			
		}
		else{
			
		}
		
		/*
		 * Selesai gambar panggil method processInput() nya
		 * controller, supaya setiap kali selesai gambar
		 * controller ngecek ada yang terjadi apa enggak.
		 */
		controller.processInput();
		
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
		
		//inisialisasi segala-galanya
		controller = new MainMenuController(this);
		
		//background
		
		//button semuanya
		
		playButtonPressed = false;
		settingsButtonPressed = false;	
	}
	
}
