package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class AbstractScreen implements Screen{
	
	private Aplikasi app;
	
	public final float DEFAULT_WIDTH = 1280.0f;
	public final float DEFAULT_HEIGHT = 800.0f;
	public int width, height;
	public float ppuX, ppuY;
	
	protected OrthographicCamera cam;
	protected SpriteBatch batcher;
	
	/* 
	 * kalo mau ngeliat posisi boundsnya, kalo ini true
	 * rectangle bounds-nya digambar.
	 */
	protected ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public AbstractScreen(Aplikasi app){
		this.app = app;
		
		width = (int)DEFAULT_WIDTH;
		height = (int)DEFAULT_HEIGHT;
		
		ppuX = (float)width/DEFAULT_WIDTH;
		ppuY = (float)height/DEFAULT_HEIGHT;
		
		cam = new OrthographicCamera(width, height);
		cam.position.set(width / 2, height / 2, 0);
		
		batcher = new SpriteBatch();
	}
	
	public Aplikasi getAplikasi(){
		return app;
	}
	
	public OrthographicCamera getCam(){
		return cam;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width; 
		this.height = height;
		ppuX = (float)this.width/DEFAULT_WIDTH;
		ppuY = (float)this.height/DEFAULT_HEIGHT;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
