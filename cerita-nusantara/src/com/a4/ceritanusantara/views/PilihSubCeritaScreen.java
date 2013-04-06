package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.PilihSubCeritaController;
import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.models.SubCerita;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class PilihSubCeritaScreen extends AbstractScreen{
	
	
	PilihSubCeritaController controller;
	
	Cerita cerita;
	SubCerita[] subcerita;
	
	private Texture background;
	private Texture timelineTexture;
	
	private Texture lockTexture;
	
	public PilihSubCeritaScreen(Aplikasi app, Cerita cerita) {
		super(app);
		// TODO Auto-generated constructor stub
		controller = new PilihSubCeritaController(app);
		
		subcerita = cerita.getSubCerita();

		background = new Texture(Gdx.files.internal("backgrounds/pilihsubcerita_bg.png"));
		
		timelineTexture = new Texture(Gdx.files.internal(
				"backgrounds/timeline_"+subcerita.length+".png"));
		
		lockTexture = new Texture(Gdx.files.internal("buttons/lock.png"));
		
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
			
			batcher.draw(timelineTexture, 
					(VIRTUAL_WIDTH-timelineTexture.getWidth())/2, 80);
			
			
			batcher.draw(lockTexture, 497, 465);
			batcher.draw(lockTexture, 347, 465);
			
			batcher.draw(lockTexture, 261, 379);
			
			batcher.draw(lockTexture, 347, 281);
			batcher.draw(lockTexture, 497, 281);
			batcher.draw(lockTexture, 644, 281);
			
			batcher.draw(lockTexture, 733, 207);
			
			batcher.draw(lockTexture, 644, 126);
			batcher.draw(lockTexture, 497, 126);
			
			
			
			
		batcher.end();
		
		controller.processInput();
	}

}
