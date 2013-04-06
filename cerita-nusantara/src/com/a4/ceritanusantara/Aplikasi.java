package com.a4.ceritanusantara;

import com.badlogic.gdx.Game;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;

public class Aplikasi extends Game {
	
	CeritaNusantara ceritaNusantara;

	@Override
	public void create() {
		ceritaNusantara = new CeritaNusantara();
		setScreen(new PilihSubCeritaScreen(this, ceritaNusantara.getCerita(CeritaNusantara.SUMATERA)));
	}
	
	public CeritaNusantara getCeritaNusantara(){
		return ceritaNusantara;
	}
	
}