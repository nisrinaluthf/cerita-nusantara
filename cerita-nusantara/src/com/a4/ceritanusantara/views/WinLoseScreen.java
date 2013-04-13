package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.KuisController;
import com.a4.ceritanusantara.controllers.MainMenuController;
import com.a4.ceritanusantara.controllers.WinLoseController;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WinLoseScreen extends AbstractScreen{
	
	private WinLoseController controller;
	private Texture background;
	
	private BitmapFont font;
	
	private boolean win;
	private int score;
	
	private boolean debug;

	
	public WinLoseScreen(Aplikasi app, boolean win, int score) {
		// TODO Auto-generated constructor stub
		super(app);
		
		this.win = win;
		this.score = score;
		// TODO Auto-generated constructor stub
		
		
		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		
		
		debug = true;
		
		controller = new WinLoseController(this);
		
		
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        cam.update();

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			batcher.draw(background, 0, 0);
			
			
			
			if(win){
				font.draw(batcher, "Score = "+score, 350, 570);
			}
			else{
				font.drawWrapped(batcher, 
						"Kamu gagal menyelesaikan permainan kali ini :( Coba lagi ya :)", 
						150, 570, 800);
			}
			
			
			
			
			
		batcher.end();
		
		
		if(debug){
			drawDebug();
		}
		
		
		controller.processInput();
	}
	
	private void drawDebug(){
		
		
	}
	
}
