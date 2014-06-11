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

/**
 * Kelas <code>CongratulationsScreen</code> adalah kelas untuk mengatur tampilan screen skor.
 * Kelas ini menerima input dari pengguna dan meneruskannya ke kelas CongratulationsController.
 */
public class CongratulationsScreen extends AbstractScreen {
	
	private CongratulationsController controller;
	private Cerita cerita;
	
	private Texture background;
	private Texture panel;
	
	private Texture shareButtonTexture;
	private Texture twitterButtonTexture;
	private Texture shareButtonPressedTexture;
	private Texture twitterButtonPressedTexture;
	
	private Rectangle shareButtonBounds;
	private Rectangle twitterButtonBounds;
	
	private boolean shareButtonPressed;
	private boolean twitterButtonPressed;
	
	private Rectangle nextButtonBounds;
	private Texture nextButtonTexture;
	private boolean nextButtonPressed;
	
	private BitmapFont font;
	
	String skor;
	String total;
	private BitmapFont font36;
	
	int score;

	public CongratulationsScreen(Aplikasi app, Cerita cerita) {
		super(app);
		this.cerita = cerita;
		background = new Texture(Gdx.files.internal("backgrounds/bg.png"));
		panel = new Texture(Gdx.files.internal("backgrounds/score_bg.png"));
		
		shareButtonTexture = new Texture(Gdx.files.internal("buttons/share.png"));
		twitterButtonTexture = new Texture(Gdx.files.internal("buttons/twitter.png"));
		shareButtonPressedTexture = new Texture(Gdx.files.internal("buttons/share_clicked.png"));
		twitterButtonPressedTexture = new Texture(Gdx.files.internal("buttons/twitter.png"));
		nextButtonTexture = new Texture(Gdx.files.internal("buttons/dialog_next.png"));
		
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		font36 = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-36-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-36-black-bold.png"), false);
		
		shareButtonBounds = new Rectangle(200,
				80, shareButtonTexture.getWidth(),
				shareButtonTexture.getHeight());
		twitterButtonBounds = new Rectangle(330,
				80, twitterButtonTexture.getWidth(),
				twitterButtonTexture.getHeight());
		
		nextButtonBounds = new Rectangle(760, 100, 
				nextButtonTexture.getWidth(), 
				nextButtonTexture.getHeight());
		
		shareButtonPressed = false;
		twitterButtonPressed = false;
		nextButtonPressed = false;
		
		skor = "";
		total = "";
		
		readScore();
		
		controller = new CongratulationsController(this, cerita, score);
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
		score = totalScore;
		
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
			if (shareButtonPressed) {
				batcher.draw(shareButtonPressedTexture, 
						shareButtonBounds.x, shareButtonBounds.y);
				
			}
			else {
				batcher.draw(shareButtonTexture, 
						shareButtonBounds.x, shareButtonBounds.y);
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
		return nextButtonPressed;
	}

	public Rectangle getTwitterButtonBounds() {
		return twitterButtonBounds;
	}
	
	public Rectangle getShareButtonBounds() {
		return shareButtonBounds;
	}

	public void setShareButtonPressed(boolean b) {
		shareButtonPressed = b;
	}
	
	public void setTwitterButtonPressed(boolean b) {
		twitterButtonPressed = b;
	}

	public boolean shareButtonIsPressed() {
		return shareButtonPressed;
	}
	
	public boolean twitterButtonIsPressed() {
		return twitterButtonPressed;
	}
	
	public void dispose(){
		super.dispose();
		background.dispose();
		panel.dispose();
		
		shareButtonTexture.dispose();
		twitterButtonTexture.dispose();
		shareButtonPressedTexture.dispose();
		twitterButtonPressedTexture.dispose();
		nextButtonTexture.dispose();
		font.dispose();
		font36.dispose();
		
	}
	

}
