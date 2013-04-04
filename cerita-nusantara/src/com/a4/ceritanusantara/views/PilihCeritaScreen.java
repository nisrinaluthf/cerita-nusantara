package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class PilihCeritaScreen extends AbstractScreen {

Texture background;
	
	public PilihCeritaScreen(Aplikasi app) {
		super(app);
		// TODO Auto-generated constructor stub
		
		background = new Texture(Gdx.files.internal("backgrounds/pilihcerita_bg.png"));
	}
	
		
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		
		batcher.begin();
			
			//background
			batcher.draw(background, 0, 0);
			
		batcher.end();
		
		//--kalo mau ada sfx atau musik nanti di sini aja--
		
		//----------------end of sfx/musik-----------------
		
		//kalo udah bikin controllernya jangan lupa panggil controller.processInput()
		
	}
	
}
