package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.views.MainMenuScreen;
import com.badlogic.gdx.math.Vector2;

public class MainMenuController {
	
	/* 
	 * screen yang dia control apa
	 */
	MainMenuScreen screen;
	
	/* 
	 * Dia harus megang Aplikasi juga soalnya yang bisa 
	 * mindahin dari satu screen ke screen lain cuma 
	 * Aplikasi
	 */
	Aplikasi app;
	
	boolean somethingIsPressed;
	
	/*
	 * Constructor
	 */
	public MainMenuController(MainMenuScreen screen){
		this.screen = screen;
		app = screen.getAplikasi();
	}
	
	public void touch(Vector2 position){
		
	}
	
	public void release(Vector2 position){
		
	}
	
	public void processInput(){
		
	}
	
	
	
}
