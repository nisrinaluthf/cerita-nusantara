package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.SettingsController;
import com.a4.ceritanusantara.controllers.SubCeritaController;
import com.a4.ceritanusantara.models.SubCerita;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class SubCeritaScreen extends AbstractScreen {
	private SubCeritaController controller;
	private SubCerita subCerita;
	
	private Texture pauseButtonTexture;

	
	private Texture pauseButtonPressedTexture;

	private Rectangle pauseButtonBounds;

	private boolean pauseButtonPressed;

	private int tipeCerita;

	public static final int ADEGAN = 0;
	public static final int LABIRIN = 1;
	public static final int TAP_GAME = 2;
	public static final int PUZZLE = 3;
	public static final int RUNNING_GAME = 4;
	public static final int KUIS = 5;

	public static final int PAUSED = 6;
	public static final int PLAYING = 7;
	
	
	public SubCeritaScreen(Aplikasi app, SubCerita subCerita) {
		super(app);

		this.subCerita = subCerita;

		tipeCerita = subCerita.getTipe();

			
		pauseButtonTexture = new Texture(
				Gdx.files.internal("backgrounds/main_bg.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("backgrounds/main_bg.png"));

		pauseButtonBounds = new Rectangle(
				(VIRTUAL_WIDTH - pauseButtonTexture.getWidth()),
				VIRTUAL_HEIGHT, pauseButtonTexture.getWidth(),
				pauseButtonTexture.getHeight());

		pauseButtonPressed = false;

		
		controller = new SubCeritaController(this);
	}

	@Override
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


		if (pauseButtonPressed) {
			batcher.draw(pauseButtonPressedTexture,
					(VIRTUAL_WIDTH - pauseButtonPressedTexture.getWidth()),
					VIRTUAL_HEIGHT);
		} else {
			batcher.draw(pauseButtonTexture,
					(VIRTUAL_WIDTH - pauseButtonTexture.getWidth()),
					VIRTUAL_HEIGHT);
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
}
