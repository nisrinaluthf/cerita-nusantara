package com.a4.ceritanusantara.views;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.CongratulationsController;
import com.a4.ceritanusantara.models.Cerita;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class CongratulationsScreen extends AbstractScreen {
	
	private CongratulationsController controller;
	private Cerita cerita;
	
	private Texture background;
	private Texture panel;
	
	private Texture facebookButtonTexture;
	private Texture twitterButtonTexture;
	private Texture facebookButtonPressedTexture;
	private Texture twitterButtonPressedTexture;
	
	private Rectangle facebookButtonBounds;
	private Rectangle twitterButtonBounds;
	
	private boolean facebookButtonPressed;
	private boolean twitterButtonPressed;
	
	private Rectangle nextButtonBounds;
	private Texture nextButtonTexture;
	private boolean nextButtonPressed;
	
	private BitmapFont font;
	
	String skor;
	String total;

	public CongratulationsScreen(Aplikasi app, Cerita cerita) {
		super(app);
		this.cerita = cerita;
		background = new Texture(Gdx.files.internal("backgrounds/bg.png"));
		panel = new Texture(Gdx.files.internal("backgrounds/score_bg.png"));
		
		facebookButtonTexture = new Texture(Gdx.files.internal("buttons/facebook.png"));
		twitterButtonTexture = new Texture(Gdx.files.internal("buttons/twitter.png"));
		facebookButtonPressedTexture = new Texture(Gdx.files.internal("buttons/facebook.png"));
		twitterButtonPressedTexture = new Texture(Gdx.files.internal("buttons/twitter.png"));
		nextButtonTexture = new Texture(Gdx.files.internal("buttons/dialog_next.png"));
		
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		
		facebookButtonBounds = new Rectangle(200,
				80, facebookButtonTexture.getWidth(),
				facebookButtonTexture.getHeight());
		twitterButtonBounds = new Rectangle(330,
				80, twitterButtonTexture.getWidth(),
				twitterButtonTexture.getHeight());
		
		nextButtonBounds = new Rectangle(760, 100, 
				nextButtonTexture.getWidth(), 
				nextButtonTexture.getHeight());
		
		facebookButtonPressed = false;
		twitterButtonPressed = false;
		nextButtonPressed = false;
		
		skor = "";
		total = "";
		
		readScore();
		
		controller = new CongratulationsController(this, cerita);
	}

	private void readScore() {
		FileHandle localFile = null;
		
		if(cerita.getNamaDaerah().equals("Sumatera")){
			localFile = Gdx.files.local("datasumatera");
		}
		else if(cerita.getNamaDaerah().equals("Kalimantan")){
			localFile = Gdx.files.local("datakalimantan");
		}
		else if(cerita.getNamaDaerah().equals("Jawa")){
			localFile = Gdx.files.local("datajawa");
		}
		else if(cerita.getNamaDaerah().equals("Bali")){
			localFile = Gdx.files.local("databali");
		}
		
		String data = localFile.readString();
		StringTokenizer st = new StringTokenizer(data,  ";");
		int i=0;
		int totalScore = 0;
		while(st.hasMoreTokens()){
			StringTokenizer st2 = new StringTokenizer(st.nextToken(), " ");
			st2.nextToken();	
			int currentScore = Integer.parseInt(st2.nextToken());
			if (currentScore > -1) {
				skor += cerita.getSubCerita(i).getNama()+": "
						+currentScore+" ";
				totalScore += currentScore;
			}
			i++;
		}
		
		total = "Skor total: "+totalScore;
		
	}

	@Override
	public void render(float delta) {
		cam.update();

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			batcher.draw(background, 0, 0);
			batcher.draw(panel, (VIRTUAL_WIDTH-panel.getWidth())/2, 
					(VIRTUAL_HEIGHT-panel.getHeight())/2);
			
			font.drawWrapped(batcher, skor, 380f, 440f, 300f);
			font.draw(batcher, total, 380, 240);
			if (facebookButtonPressed) {
				batcher.draw(facebookButtonPressedTexture, 
						facebookButtonBounds.x, facebookButtonBounds.y);
				
			}
			else {
				batcher.draw(facebookButtonTexture, 
						facebookButtonBounds.x, facebookButtonBounds.y);
			}
			
			if (twitterButtonPressed) {
				batcher.draw(twitterButtonPressedTexture, 
						twitterButtonBounds.x, twitterButtonBounds.y);
			}
			else{
				batcher.draw(twitterButtonTexture, 
						twitterButtonBounds.x, twitterButtonBounds.y);
			}
			batcher.draw(nextButtonTexture, 
					nextButtonBounds.x, nextButtonBounds.y);
		batcher.end();
		
		controller.processInput(delta);
		
	}
	
	public Rectangle getNextButtonBounds(){
		return nextButtonBounds;
	}



	public void setNextButtonPressed(boolean b) {
		nextButtonPressed = b;
		
	}

	public boolean nextButtonIsPressed() {
		// TODO Auto-generated method stub
		return nextButtonPressed;
	}

}
