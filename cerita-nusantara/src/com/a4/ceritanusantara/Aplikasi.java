package com.a4.ceritanusantara;

import com.badlogic.gdx.Game;
import com.a4.ceritanusantara.models.CeritaNusantara;
import com.a4.ceritanusantara.utils.ActionResolver;
import com.a4.ceritanusantara.views.MainMenuScreen;

/**
 * Kelas <code>Aplikasi</code> adalah kelas yang merepresentasikan
 * aplikasi CeritaNusantara secara keseluruhan.
 */
public class Aplikasi extends Game {
	
	private CeritaNusantara ceritaNusantara;
	private ActionResolver actionResolver;
	
	Aplikasi(ActionResolver actionResolver){
		this.actionResolver = actionResolver;
	}
	
	Aplikasi(){
		
	}


	@Override
	public void create() {
		ceritaNusantara = new CeritaNusantara();
		setScreen(new MainMenuScreen(this));

	}
	
	public CeritaNusantara getCeritaNusantara(){
		return ceritaNusantara;
	}
	
	public ActionResolver getActionResolver(){
		return actionResolver;
	}
}