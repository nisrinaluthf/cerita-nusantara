package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.KuisController;
import com.a4.ceritanusantara.controllers.MainMenuController;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class KuisScreen extends SubCeritaScreen{
	
	private KuisController controller;
	
	private Kuis kuis;
	private int currentNo;
	
	private Texture background;
	private Texture soalBackgroundTexture;
	private Texture opsiBackgroundTexture;
	private Texture opsiBackgroundPressedTexture;
	
	private BitmapFont font;
	
	private boolean[] optionPressed;
	private Rectangle[] optionBounds;
	
	private boolean debug;

	public KuisScreen(Aplikasi app, Kuis kuis) {
		super(app);
		// TODO Auto-generated constructor stub
		
		this.kuis = kuis;
		
		int currentNo = 0;
		
		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		soalBackgroundTexture = new 
				Texture(Gdx.files.internal("backgrounds/soal_kuis_bg.png"));
		opsiBackgroundTexture = new 
				Texture(Gdx.files.internal("backgrounds/opsi_kuis_bg.png"));
		opsiBackgroundPressedTexture = new 
				Texture(Gdx.files.internal("backgrounds/opsi_kuis_bg_pressed.png"));
		
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		
		
		
		debug = true;
		
		controller = new KuisController(this);
		
		
		
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        cam.update();

      
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        currentNo = kuis.getCurrentNo();
		
        optionPressed = new boolean[4];
		for(int i=0; i<optionPressed.length; i++){
			optionPressed[i] = kuis.getKuisQuestion(currentNo).getOptionPressed(i);
		}
		
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			batcher.draw(background, 0, 0);
			
			batcher.draw(soalBackgroundTexture, 
					(VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2, 340);
			
			if(kuis.getKuisQuestion(currentNo).getOptionPressed(0)){
				batcher.draw(opsiBackgroundPressedTexture, 
						(VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2, 180);
			}
			else{
				batcher.draw(opsiBackgroundTexture, 
						(VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2, 180);
			}
			
			if(kuis.getKuisQuestion(currentNo).getOptionPressed(1)){
				batcher.draw(opsiBackgroundPressedTexture, 
						((VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2)+
						opsiBackgroundTexture.getWidth()+60, 180);
				System.out.println("option 1 pressed");
			}
			else{
				batcher.draw(opsiBackgroundTexture, 
						((VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2)+
						opsiBackgroundTexture.getWidth()+60, 180);
			}
			
			if(kuis.getKuisQuestion(currentNo).getOptionPressed(2)){
				batcher.draw(opsiBackgroundPressedTexture, 
						(VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2, 20);
			}
			else{
				batcher.draw(opsiBackgroundTexture, 
						(VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2, 20);
			}
			
			if(kuis.getKuisQuestion(currentNo).getOptionPressed(3)){
				batcher.draw(opsiBackgroundPressedTexture, 
						((VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2)+
						opsiBackgroundTexture.getWidth()+60, 20);
			}
			else{
				batcher.draw(opsiBackgroundTexture, 
						((VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2)+
						opsiBackgroundTexture.getWidth()+60, 20);
			}
			
			
			KuisQuestion question = kuis.getKuisQuestion(currentNo);
			font.drawWrapped(batcher, question.getQuestion(), (VIRTUAL_WIDTH-700)/2, 475, 800);
			
			font.drawWrapped(batcher, question.getOptions(0), 100, 
					290, opsiBackgroundTexture.getWidth());
			
			font.drawWrapped(batcher, question.getOptions(1), 100, 
					130, opsiBackgroundTexture.getWidth());
			
			font.drawWrapped(batcher, question.getOptions(2), 
					100+opsiBackgroundTexture.getWidth()+60, 
					290, opsiBackgroundTexture.getWidth());
			
			font.drawWrapped(batcher, question.getOptions(3), 
					100+opsiBackgroundTexture.getWidth()+60, 
					130, opsiBackgroundTexture.getWidth());
			
		batcher.end();
		
		
		if(debug){
			drawDebug();
		}
		
		
		controller.processInput();
	}
	
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			Rectangle[] tmp = kuis.getKuisQuestion(currentNo).getBounds();
			
			for(int i=0; i<tmp.length; i++){	
				debugRenderer.rect(tmp[i].x, tmp[i].y, tmp[i].width, tmp[i].height);
			}
		debugRenderer.end();
		
	}
	
	public Kuis getKuis(){
		return kuis;
	}
	
}
