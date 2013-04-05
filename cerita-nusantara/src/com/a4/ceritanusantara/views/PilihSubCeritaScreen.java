package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Cerita;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class PilihSubCeritaScreen extends AbstractScreen{
	
	Cerita cerita;
	
	private Texture background;
	
	public PilihSubCeritaScreen(Aplikasi app, Cerita cerita) {
		super(app);
		// TODO Auto-generated constructor stub
		
		background = new Texture(Gdx.files.internal("backgrounds/pilihsubcerita_bg.png"));
	}
	
	public void render(float delta) {
		 cam.update();

	        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
	                          (int) viewport.width, (int) viewport.height);
	 
	        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
	        batcher.setProjectionMatrix(cam.combined);
			batcher.begin();
				batcher.draw(background, 0, 0);
			batcher.end();
	}

}
