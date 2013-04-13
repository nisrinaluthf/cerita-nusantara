package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
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
	
	private BitmapFont font;
	
	private String narration;
	
	private final int DIALOG;
	private final int NARASI;

	private Adegan adegan;
	
	
	public AdeganScreen(Aplikasi app, Adegan adegan) {
		super(app, adegan);
		
		this.adegan = adegan;
		
		// TODO Auto-generated constructor stub
		
		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		textArea = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));

		previousButtonTexture = new Texture(
				Gdx.files.internal("backgrounds/main_bg.png"));
		nextButtonTexture = new Texture(
				Gdx.files.internal("backgrounds/main_bg.png"));

		previousButtonPressedTexture = new Texture(
				Gdx.files.internal("backgrounds/main_bg.png"));
		nextButtonPressedTexture = new Texture(
				Gdx.files.internal("backgrounds/main_bg.png"));

		previousButtonBounds = new Rectangle(0, 0,
				previousButtonTexture.getWidth(),
				previousButtonTexture.getHeight());
		nextButtonBounds = new Rectangle(
				(VIRTUAL_WIDTH - nextButtonTexture.getWidth()), 0,
				nextButtonTexture.getWidth(), nextButtonTexture.getHeight());

		previousButtonPressed = false;
		nextButtonPressed = false;

		narration = "";
		
		font = new BitmapFont(
				Gdx.files
						.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files
						.internal("fonts/sf-cartoonist-hand-44-black-bold.png"),
				false);
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
				batcher.draw(previousButtonPressedTexture, 0, 0);
			} else {
				batcher.draw(previousButtonTexture, 0, 0);
			}

			if (nextButtonPressed) {
				batcher.draw(nextButtonPressedTexture,
						(VIRTUAL_WIDTH - nextButtonPressedTexture.getWidth()),
						0);
			} else {
				batcher.draw(nextButtonTexture,
						(VIRTUAL_WIDTH - nextButtonTexture.getWidth()), 0);
			}

			font.drawWrapped(batcher, narration, x, y, wrapWidth)
		


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

}
