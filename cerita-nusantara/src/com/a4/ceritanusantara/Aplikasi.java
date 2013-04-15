package com.a4.ceritanusantara;

import com.badlogic.gdx.Game;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.a4.ceritanusantara.views.PilihSubCeritaScreen;
import com.a4.ceritanusantara.views.TapGameScreen;

public class Aplikasi extends Game {
	
	CeritaNusantara ceritaNusantara;

	@Override
	public void create() {
		ceritaNusantara = new CeritaNusantara();
		System.out.println("ceritaNusantara constructed");
		setScreen(new MainMenuScreen(this));
		
		/*
		setScreen(new PilihSubCeritaScreen(this, 
				getCeritaNusantara().getCerita(CeritaNusantara.SUMATERA)));
				*/
		/*
		setScreen(new KuisScreen(this, 
				(Kuis)ceritaNusantara.getCerita(CeritaNusantara.SUMATERA).getSubCerita(10)));
		*/		
		/*
		setScreen(new TapGameScreen(this, 
				(TapGame)ceritaNusantara.getCerita(CeritaNusantara.SUMATERA).getSubCerita(3)));
			*/	
		/*
		setScreen(new TapGameScreen(this, 
				(TapGame)ceritaNusantara.getCerita(CeritaNusantara.KALIMANTAN).getSubCerita(7)));*/
	}
	
	public CeritaNusantara getCeritaNusantara(){
		return ceritaNusantara;
	}
	
}