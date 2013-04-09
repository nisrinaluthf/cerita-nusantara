package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.PilihSubCeritaController;
import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.models.SubCerita;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PilihSubCeritaScreen extends AbstractScreen{
	
	
	PilihSubCeritaController controller;
	
	private Cerita cerita;
	private SubCerita[] subcerita;
	
	private Vector2[] buttonPos;
	private Rectangle[] buttonBounds;
	
	private Texture background;
	private Texture timelineTexture;
	
	private Texture lockTexture;
	
	private boolean debug = true;
	
	public PilihSubCeritaScreen(Aplikasi app, Cerita cerita) {
		super(app);
		// TODO Auto-generated constructor stub
		
		
		subcerita = cerita.getSubCerita();

		background = new Texture(Gdx.files.internal("backgrounds/pilihsubcerita_bg.png"));
		
		timelineTexture = new Texture(Gdx.files.internal(
				"backgrounds/timeline_"+subcerita.length+".png"));
		
		lockTexture = new Texture(Gdx.files.internal("buttons/lock.png"));
		
		buttonPos = new Vector2[]{
				new Vector2(644, 465),
				new Vector2(497, 465),
				new Vector2(347, 465),
				
				new Vector2(261, 379),
				
				new Vector2(347, 281),
				new Vector2(497, 281),
				new Vector2(644, 281),
				
				new Vector2(733, 207),
				
				new Vector2(644, 126),
				new Vector2(497, 126)
		};
		
		buttonBounds = new Rectangle[buttonPos.length];
		for(int i=0; i<buttonBounds.length; i++){
			buttonBounds[i] = new Rectangle(buttonPos[i].x, buttonPos[i].y,
					lockTexture.getWidth(), lockTexture.getHeight());
		}
		
		controller = new PilihSubCeritaController(this);
		
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
			
			for(int i=0; i<subcerita.length; i++){
				batcher.draw(lockTexture, buttonPos[i].x, buttonPos[i].y);
			}
				
		batcher.end();
		
		if(debug){
			drawDebug();
		}
		
		controller.processInput();
	}
	
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			for(int i=0; i<buttonBounds.length; i++){
				debugRenderer.rect(buttonBounds[i].x, buttonBounds[i].y,
						buttonBounds[i].width, buttonBounds[i].height);
			}
		debugRenderer.end();
	}
	
}
