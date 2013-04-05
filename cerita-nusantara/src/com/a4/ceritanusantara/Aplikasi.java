package com.a4.ceritanusantara;

import com.badlogic.gdx.Game;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.views.MainMenuScreen;

public class Aplikasi extends Game {
	
	CeritaNusantara ceritaNusantara;

	@Override
	public void create() {
		ceritaNusantara = new CeritaNusantara();
		setScreen(new MainMenuScreen(this));
	}
	
	public CeritaNusantara getCeritaNusantara(){
		return ceritaNusantara;
	}
	
}