package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.LabirinController;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.LabirinWall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class LabirinScreen extends AbstractScreen{
	
	private Labirin labirin;
	private LabirinController controller;
	
	private Texture background;
	private Texture wallTexture;
	private Texture[] playerTexture;
	
	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;
	
	private boolean pauseButtonPressed;
	
	private LabirinWall[] walls;
	
	private boolean debug = true;
	private Texture itemTexture;
	
	public LabirinScreen(Aplikasi app, Labirin labirin) {
		super(app);
		// TODO Auto-generated constructor stub
		this.labirin = labirin;
		labirin.reinit();
		
		walls = labirin.getWalls();
		
		background = labirin.getBackground();
		wallTexture = labirin.getWallTexture();
		playerTexture = labirin.getPlayerTexture();
		itemTexture = labirin.getItemTexture();
		
		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));

		pauseButtonBounds = new Rectangle(950, 526, 60, 60);
		
		pauseButtonPressed = false;
		
		controller = new LabirinController(this, app, labirin);
		
	}
	
	public void render(float delta){
		cam.update();

	      
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			batcher.draw(background, 0, 0);
			for(int i=0; i<walls.length; i++){
				batcher.draw(wallTexture, walls[i].getX(), walls[i].getY());
			}
		batcher.end();
	}

}
