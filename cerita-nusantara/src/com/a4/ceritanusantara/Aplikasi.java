package com.a4.ceritanusantara;

import com.badlogic.gdx.Game;
import com.a4.ceritanusantara.views.MainMenuScreen;

public class Aplikasi extends Game {

	@Override
	public void create() {
		setScreen(new MainMenuScreen(this, 1280, 800));
	}
}