package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen{
	
	Aplikasi app;
	
	public AbstractScreen(Aplikasi app){
		this.app = app;
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
