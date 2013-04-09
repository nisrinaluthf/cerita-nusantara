package com.a4.ceritanusantara.controllers;

import com.a4.ceritanusantara.utils.OverlapTester;
import com.a4.ceritanusantara.views.KuisScreen;
import com.a4.ceritanusantara.views.PilihCeritaScreen;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class KuisController {
	
	private KuisScreen screen;
	private Kuis kuis;
	
	private Rectangle[] optionsBounds;
	private KuisQuestion kuisQuestion;
	
	private OrthographicCamera cam;
	private Rectangle viewport;

	public KuisController(KuisScreen screen) {
		// TODO Auto-generated constructor stub
		this.screen = screen;
		kuis = screen.getKuis();
		kuisQuestion = kuis.getKuisQuestion(0);
		
		optionsBounds = kuisQuestion.getBounds();
		
		cam = screen.getCam();
		viewport = screen.getViewport();
		
	}
	
	public void processInput(){
		
		kuisQuestion = kuis.getKuisQuestion(kuis.getCurrentNo());
		
		optionsBounds = kuisQuestion.getBounds();
		
		if(Gdx.input.justTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			
			for(int i=0; i<optionsBounds.length; i++){
				if(OverlapTester.pointInRectangle(optionsBounds[i], pos.x, pos.y)){
					kuisQuestion.setOptionPressed(i, true);
					System.out.println(i);
				}
			}
		}
		
		if(!Gdx.input.isTouched()){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(pos, viewport.x, viewport.y, viewport.width, viewport.height);
			for(int i=0; i<4; i++){
				if(kuisQuestion.getOptionPressed(i)){
					kuisQuestion.setOptionPressed(i, false);
					if(OverlapTester.pointInRectangle(optionsBounds[i], pos.x, pos.y)){
						if(kuis.getCurrentNo()<4){
							kuis.setCurrentNo(kuis.getCurrentNo()+1);
						}
						else{
							//kuis.setScreen(new )
						}
					}
				}
			}
		}
		
	}
	
}
