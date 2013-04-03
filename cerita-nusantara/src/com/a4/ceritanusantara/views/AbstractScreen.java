package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class AbstractScreen implements Screen{
	
	private Aplikasi app;
	
	public final int DEFAULT_WIDTH = 1280;
	public final int DEFAULT_HEIGHT = 800;
	protected int width, height;
	protected OrthographicCamera cam;
	protected SpriteBatch batcher;
	
	/* 
	 * kalo mau ngeliat posisi boundsnya, kalo ini true
	 * rectangle bounds-nya digambar.
	 */
	protected boolean debug = true;
	protected ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public AbstractScreen(Aplikasi app){
		this.app = app;
		
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		
		cam = new OrthographicCamera(width, height);
		cam.position.set(width / 2, height / 2, 0);
		
		batcher = new SpriteBatch();
	}
	
	public Aplikasi getAplikasi(){
		return app;
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
