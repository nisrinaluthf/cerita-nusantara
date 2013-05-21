package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.LabirinController;
import com.a4.ceritanusantara.controllers.PuzzleController;
import com.a4.ceritanusantara.models.Puzzle;
import com.a4.ceritanusantara.models.PuzzlePiece;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class PuzzleScreen extends AbstractScreen{
	
	private Puzzle puzzle;
	private PuzzleController controller;
	
	private PuzzlePiece[][] pieces;
	
	private Texture background;
	private Texture[][] piecesTexture;
			
	private boolean debug = true;

	public PuzzleScreen(Aplikasi app, Puzzle puzzle) {
		super(app);
		
		this.puzzle = puzzle;
		puzzle.reinit();
		
		pieces = puzzle.getPieces();
		
		background = puzzle.getBackground();
		
		piecesTexture = new Texture[4][4];
		for (int i=0; i<piecesTexture.length; i++){
			for (int j=0; j<piecesTexture[i].length; j++){
				piecesTexture[i][j] = pieces[i][j].getTexture();
			}
		}
		
		
		controller = new PuzzleController(this);
		
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
				for (int i=0; i<pieces.length; i++){
					for (int j=0; j<pieces[i].length; j++){
						batcher.draw(piecesTexture[i][j], 
								pieces[i][j].getX(), pieces[i][j].getY());
						//System.out.println(pieces[i][j].getX()+" "+pieces[i][j].getY());
					}
				}
			}
		batcher.end();
		
		if(debug ){
			drawDebug();
		}
	}

	private void drawDebug() {
		// TODO Auto-generated method stub
		
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			debugRenderer.setColor(new Color(1, 1, 0, 1));
			for (int i=0; i<pieces.length; i++){
				for (int j=0; j<pieces[i].length; j++){
					Rectangle bounds = pieces[i][j].getTarget().getBounds();
							debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
			
				}
			}
			
		debugRenderer.end();
	}
	
}
