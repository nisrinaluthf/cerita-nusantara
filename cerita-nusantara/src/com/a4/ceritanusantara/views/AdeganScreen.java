package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.AdeganController;
import com.a4.ceritanusantara.models.Adegan;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class AdeganScreen extends SubCeritaScreen {
	private Texture background;
	private Texture textArea;
	private Texture previousButtonTexture;
	private Texture nextButtonTexture;
	
	private Texture previousButtonPressedTexture;
	private Texture nextButtonPressedTexture;
	private Texture character;
	
	private Rectangle previousButtonBounds;
	private Rectangle nextButtonBounds;
	
	private boolean previousButtonPressed;
	private boolean nextButtonPressed;
	
	private Texture pauseButtonTexture;

	
	private Texture pauseButtonPressedTexture;

	private Rectangle pauseButtonBounds;

	private boolean pauseButtonPressed;
	
	private BitmapFont font;
	
	private String narration;
	
	private final int DIALOG = 0;

	private Adegan adegan;
	
	private int tipeAdegan;
	
	private AdeganController controller;
	
	
	public AdeganScreen(Aplikasi app, Adegan adegan) {
		super(app, adegan);
		
		this.adegan = adegan;
		
		// TODO Auto-generated constructor stub
		
		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		textArea = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		character = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));

		previousButtonTexture = new Texture(
				Gdx.files.internal("backgrounds/dialog_back.png"));
		nextButtonTexture = new Texture(
				Gdx.files.internal("backgrounds/dialog_next.png"));

		previousButtonPressedTexture = new Texture(
				Gdx.files.internal("backgrounds/dialog_back_pressed.png"));
		nextButtonPressedTexture = new Texture(
				Gdx.files.internal("backgrounds/dialog_next_pressed.png"));

		previousButtonBounds = new Rectangle(0, previousButtonTexture.getHeight(),
				previousButtonTexture.getWidth(),
				previousButtonTexture.getHeight());
		nextButtonBounds = new Rectangle(
				(VIRTUAL_WIDTH - nextButtonTexture.getWidth()), previousButtonTexture.getHeight(),
				nextButtonTexture.getWidth(), nextButtonTexture.getHeight());

		previousButtonPressed = false;
		nextButtonPressed = false;

		pauseButtonTexture = new Texture(
				Gdx.files.internal("backgrounds/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("backgrounds/pause_pressed.png"));

		pauseButtonBounds = new Rectangle(
				(VIRTUAL_WIDTH - pauseButtonTexture.getWidth()),
				VIRTUAL_HEIGHT, pauseButtonTexture.getWidth(),
				pauseButtonTexture.getHeight());

		pauseButtonPressed = false;
		
		tipeAdegan = -1;
		
		narration = "";
		
		
		
		font = new BitmapFont(
				Gdx.files
						.internal("fonts/sf-cartoonist-hand-30-white-bold.fnt"),
				Gdx.files
						.internal("fonts/sf-cartoonist-hand-30-white-bold.png"),
				false);
		
		controller = new AdeganController(this);
	}
	
	public void render(float delta) {
		// TODO Auto-generated method stub

		cam.update();

		// set viewport
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.setProjectionMatrix(cam.combined);
		batcher.begin();

		
			batcher.draw(background, 0, VIRTUAL_HEIGHT);

			batcher.draw(textArea, 0, (textArea.getHeight()));

			if (previousButtonPressed) {
				batcher.draw(previousButtonPressedTexture, 0, previousButtonTexture.getHeight());
			} else {
				batcher.draw(previousButtonTexture, 0, previousButtonTexture.getHeight());
			}

			if (nextButtonPressed) {
				batcher.draw(nextButtonPressedTexture,
						(VIRTUAL_WIDTH - nextButtonPressedTexture.getWidth()),
						0);
			} else {
				batcher.draw(nextButtonTexture,
						(VIRTUAL_WIDTH - nextButtonTexture.getWidth()), 0);
			}
			
			if (pauseButtonPressed) {
				batcher.draw(pauseButtonPressedTexture,
						(VIRTUAL_WIDTH - pauseButtonPressedTexture.getWidth()),
						VIRTUAL_HEIGHT);
			} else {
				batcher.draw(pauseButtonTexture,
						(VIRTUAL_WIDTH - pauseButtonTexture.getWidth()),
						VIRTUAL_HEIGHT);
			}
			
			//if(adegan.)
			
			if(this.tipeAdegan == DIALOG) {
				font = new BitmapFont(
						Gdx.files
								.internal("fonts/sf-cartoonist-hand-30-white-bold-italic.fnt"),
						Gdx.files
								.internal("fonts/sf-cartoonist-hand-30-white-bold-italic.png"),
						false);
				font.drawWrapped(batcher, narration, previousButtonTexture.getWidth()+40, textArea.getHeight() - 15, VIRTUAL_WIDTH-previousButtonTexture.getWidth()-nextButtonTexture.getWidth() - 80);
				batcher.draw(character, previousButtonTexture.getWidth()+ 10, previousButtonTexture.getHeight());
			} else{
				font.drawWrapped(batcher, narration, previousButtonTexture.getWidth()+10, textArea.getHeight() - 15, VIRTUAL_WIDTH-previousButtonTexture.getWidth()-nextButtonTexture.getWidth() - 20);
				
			}
			
			


		// batcher.draw(soundOnTexture,
		// (VIRTUAL_WIDTH-soundOnTexture.getWidth())/2, 440);

		// batcher.draw(musicOnTexture,
		// (VIRTUAL_WIDTH-musicOnTexture.getWidth())/2, 160);

		batcher.end();

		controller.processInput();

		// --kalo mau ada sfx atau musik nanti di sini aja--

		// ----------------end of sfx/musik-----------------

		// kalo udah bikin controllernya jangan lupa panggil
		// controller.processInput()

	}
	
	public Rectangle getPreviousButtonBounds(){
		return previousButtonBounds;
	}
	
	public boolean previousButtonIsPressed() {
		return previousButtonPressed;
	}
	
	public void setPreviousButtonPressed(boolean b) {
		this.previousButtonPressed = b;
	}
	
	public Rectangle getNextButtonBounds(){
		return nextButtonBounds;
	}
	
	public boolean nextButtonIsPressed() {
		return nextButtonPressed;
	}
	
	public void setNextButtonPressed(boolean b) {
		this.nextButtonPressed = b;
	}
	
	public Rectangle getPauseButtonBounds(){
		return pauseButtonBounds;
	}
	
	public boolean pauseButtonIsPressed() {
		return pauseButtonPressed;
	}
	
	public void setPauseButtonPressed(boolean b) {
		this.pauseButtonPressed = b;
	}
	
	public void setAdegan(String narration,int tipe, Texture character) {
		this.narration = narration;
		this.tipeAdegan = tipe;
		this.character = character;
	}

}
