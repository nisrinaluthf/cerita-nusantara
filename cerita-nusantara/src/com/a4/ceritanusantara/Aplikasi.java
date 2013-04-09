package com.a4.ceritanusantara;

import com.badlogic.gdx.Game;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;

public class Aplikasi extends Game {
	
	CeritaNusantara ceritaNusantara;

	@Override
	public void create() {
		ceritaNusantara = new CeritaNusantara();
		setScreen(new KuisScreen(this, 
				(Kuis)ceritaNusantara.getCerita(CeritaNusantara.SUMATERA).getSubCerita(9)));
	}
	
	public CeritaNusantara getCeritaNusantara(){
		return ceritaNusantara;
	}
	
}