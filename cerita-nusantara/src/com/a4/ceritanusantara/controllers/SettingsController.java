package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.SettingsScreen;
import com.badlogic.gdx.math.Rectangle;

public class SettingsController {
	
	private Aplikasi app;
	
	/* 
	 * ambil screen yang dia control, terus ambil bounds2 dari
	 * screen itu (biar kalo manggil ngga pake get-get lagi).
	 * 
	 */
	private SettingsScreen screen;
	private Rectangle soundButtonBounds;
	private Rectangle musicButtonBounds;
	
	public SettingsController(SettingsScreen screen){
		this.screen = screen;
		app = screen.getAplikasi();
		soundButtonBounds = screen.getSoundButtonBounds();
		musicButtonBounds = screen.getMusicButtonBounds();
		
	}
}
