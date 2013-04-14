package com.a4.ceritanusantara.views;

import java.util.Iterator;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.TapGameController;
import com.a4.ceritanusantara.models.TapGame;
import com.a4.ceritanusantara.models.TapGameButton;
import com.a4.ceritanusantara.models.TapGameTarget;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class TapGameScreen extends AbstractScreen{
	
	private TapGame tapGame;
	private TapGameController controller;
	
	private Texture background;
	private Texture panelBgTexture;
	private Texture[] targetsTexture;
	private Texture[] indicatorsTexture;
	private Texture scoreBgTexture;
	private Texture scoreFrameTexture;
	private TapGameButton[] buttons;
	
	private boolean debug = true;
		
	public TapGameScreen(Aplikasi app, TapGame tapGame){
		super(app);
		
		this.tapGame = tapGame;
		tapGame.reinit();
		controller = new TapGameController(this, app, tapGame);
		background = tapGame.getBackground();
		panelBgTexture = tapGame.getPanelBackground();
		targetsTexture = tapGame.getTargetsTexture();
		indicatorsTexture = tapGame.getIndicators();
		
		scoreBgTexture = new Texture(Gdx.files.internal("parkit-tapgame/score_bg.png"));
		scoreFrameTexture = new Texture(Gdx.files.internal("parkit-tapgame/score_frame.png"));
		
		buttons = tapGame.getButtons();
	}
	
	public void render(float delta){
		
		tapGame.generateTargets(delta);
		tapGame.updateTargets(delta);
		
		cam.update();

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
        batcher.setProjectionMatrix(cam.combined);
        
		batcher.begin();
			batcher.draw(background, 0, 0);
			batcher.draw(panelBgTexture, 
					(VIRTUAL_WIDTH-panelBgTexture.getWidth())/2, 0);
			batcher.draw(scoreBgTexture, 900, 80);
		batcher.end();
		
		debugRenderer.begin(ShapeType.FilledRectangle);
			int hits = tapGame.getHits();
			if(hits>10){
				debugRenderer.setColor(new Color(0, 1, 0, 1));
			}
			else if(hits>4){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.filledRect(904, 84, 35, (hits/25.0f)*480);
		debugRenderer.end();
		
		batcher.begin();
			batcher.draw(scoreFrameTexture, 900, 80);
			if(tapGame.getHits()>10){
				batcher.draw(indicatorsTexture[0], 865, 10);
			}
			else{
				batcher.draw(indicatorsTexture[1], 865, 0);
			}
		
			Iterator<TapGameTarget> itr = tapGame.getTargets().iterator();
			while(itr.hasNext()){
				TapGameTarget target = itr.next();
				batcher.draw(targetsTexture[target.getType()], 
						target.getIndex() == 0 ? 274 : target.getIndex() == 1 ? 449 : 634,
						target.getPos());
			}
			
			
			for(int i=0; i<buttons.length; i++){
				if(buttons[i].isPressed()){
					batcher.draw(buttons[i].getButtonPressedTexture(), 
							buttons[i].getX(), buttons[i].getY());
				}
				else{
					batcher.draw(buttons[i].getButtonTexture(), 
							buttons[i].getX(), buttons[i].getY());
				}
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
			for(int i=0; i<buttons.length; i++){
				debugRenderer.rect(buttons[i].getBounds().x, buttons[i].getBounds().y,
						buttons[i].getBounds().getWidth(), buttons[i].getBounds().getHeight());
			}
			

			Iterator<TapGameTarget> itr = tapGame.getTargets().iterator();
			while(itr.hasNext()){
				Rectangle rect = itr.next().getBounds();
				debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
			}	
			
		debugRenderer.end();
	}
}
