package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.views.SettingsScreen;
import com.a4.ceritanusantara.views.SubCeritaScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class SubCeritaController {
	private Aplikasi app;
	
	private SubCeritaScreen screen;
	private Rectangle pauseButtonBounds;
	private Preferences prefs;

	private OrthographicCamera cam;
	private Rectangle viewport;
	
	public SubCeritaController(SubCeritaScreen screen){
		Gdx.input.setCatchBackKey(true);
		this.screen = screen;
		this.app = screen.getAplikasi();
		pauseButtonBounds = this.screen.getPauseButtonBounds();
		prefs = Gdx.app.getPreferences("preferences");
		System.out.println("sub cerita controller di create");
		cam = this.screen.getCam();
		viewport = this.screen.getViewport();

	}
	

}
