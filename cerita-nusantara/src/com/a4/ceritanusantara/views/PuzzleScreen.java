package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.models.Puzzle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class PuzzleScreen extends AbstractScreen{
	
	private Puzzle puzzle;
	private Texture background;
	private boolean debug = false;

	public PuzzleScreen(Aplikasi app, Puzzle puzzle) {
		super(app);
		
		this.puzzle = puzzle;
		puzzle.reinit();
		
		background = puzzle.getBackground();
		
		
	}
	
	public void render(float delta){
		cam.update();

	      
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			if(puzzle.isGameOver()){
				
			}
			else{
				batcher.draw(background, 0, 0);
			}
		batcher.end();
		
		if(debug ){
			drawDebug();
		}
	}

	private void drawDebug() {
		
		
	}
	
}
