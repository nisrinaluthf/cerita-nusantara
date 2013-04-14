package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.PauseController;
import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.SubCerita;
import com.a4.ceritanusantara.models.TapGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class PauseScreen extends AbstractScreen {
	private PauseController controller;
	private Screen originScreen;
	
	private Texture background;
	
	private int type;
	private SubCerita subcerita;
	
	private Texture resumeButtonTexture;
	private Texture restartButtonTexture;
	private Texture exitButtonTexture;
	private Texture exitButtonPressedTexture;
	private Texture resumeButtonPressedTexture;
	private Texture restartButtonPressedTexture;
	
	private Rectangle resumeButtonBounds;
	private Rectangle restartButtonBounds;
	private Rectangle exitButtonBounds;
	
	private boolean resumeButtonPressed;
	private boolean restartButtonPressed;
	private boolean exitButtonPressed;
	
	private boolean debug = false;

	public PauseScreen(Aplikasi app, Screen originScreen, Adegan adegan) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.ADEGAN;
		subcerita = adegan;
		init();
	}
	
	public PauseScreen(Aplikasi app, Screen originScreen, Labirin labirin) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.LABIRIN;
		subcerita = labirin;
		init();
	}
	
	public PauseScreen(Aplikasi app, Screen originScreen, TapGame tapGame) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.TAP_GAME;
		subcerita = tapGame;
		init();
	}
	
	public PauseScreen(Aplikasi app, Screen originScreen, Kuis kuis) {
		super(app);
		this.originScreen = originScreen;
		type = SubCerita.KUIS;
		subcerita = kuis;
		init();
	}
	
	private void init(){
		background = new Texture(Gdx.files.internal("backgrounds/pause_bg.png"));
		
		resumeButtonTexture = 
				new Texture(Gdx.files.internal("buttons/resume.png"));
		
		resumeButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/resume_pressed.png"));
		
		restartButtonTexture = 
				new Texture(Gdx.files.internal("buttons/restart.png"));
		
		restartButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/restart_pressed.png"));
		
		exitButtonTexture = 
				new Texture(Gdx.files.internal("buttons/exit.png"));
		
		exitButtonPressedTexture = 
				new Texture(Gdx.files.internal("buttons/exit_pressed.png"));
		
		resumeButtonBounds = new Rectangle((VIRTUAL_WIDTH-resumeButtonTexture.getWidth())/2,
				400, resumeButtonTexture.getWidth(),
				resumeButtonTexture.getHeight());
		
		restartButtonBounds = new Rectangle((VIRTUAL_WIDTH-restartButtonTexture.getWidth())/2,
				300, restartButtonTexture.getWidth(),
				restartButtonTexture.getHeight());
		
		exitButtonBounds = new Rectangle((VIRTUAL_WIDTH-exitButtonTexture.getWidth())/2,
				200, exitButtonTexture.getWidth(),
				exitButtonTexture.getHeight());

		
		resumeButtonPressed = false;
		restartButtonPressed = false;
		exitButtonPressed = false;
		
		controller = new PauseController(this);
	}

	/* render(float delta) dipanggil terus-terusan setiap
	 * delta detik sekali setelah screen muncul (setelah 
	 * show() dipanggil). Ini method gambar utamanya.
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        cam.update();

        // set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        /*
		 * kalo ngga salah ini supaya background VIRTUALnya
		 * warna hitam, tapi ngga tau juga sih ._.
		 */
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		/*
		 * Setiap mau gambar set dulu projection matrixnya, terus
		 * diawali batcher.begin(), diakhiri batcher.end()
		 */
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			
			/* 
			 * gambar background di sini
			 */
			batcher.draw(background, 0, 0);
			
			/*
			 * gambar button2 di sini.
			 * Kalo buttonnya lagi dipencet gambarnya beda,
			 * nge-glow atau kayak masuk gitu buttonnya
			 * atau gimana.
			 */
			
			if (resumeButtonPressed) {
				batcher.draw(resumeButtonPressedTexture, 
						(VIRTUAL_WIDTH-resumeButtonPressedTexture.getWidth())/2, 400);
				
			}
			else {
				batcher.draw(resumeButtonTexture, 
						(VIRTUAL_WIDTH-resumeButtonTexture.getWidth())/2, 400);
			}
			
			if (restartButtonPressed) {
				batcher.draw(restartButtonPressedTexture, 
						(VIRTUAL_WIDTH-restartButtonPressedTexture.getWidth())/2, 300);
			}
			else{
				batcher.draw(restartButtonTexture, 
						(VIRTUAL_WIDTH-restartButtonTexture.getWidth())/2, 300);
			}
			
			if (exitButtonPressed) {
				batcher.draw(exitButtonPressedTexture, 
						(VIRTUAL_WIDTH-exitButtonPressedTexture.getWidth())/2, 200);
			}
			else{
				batcher.draw(exitButtonTexture, 
						(VIRTUAL_WIDTH-exitButtonTexture.getWidth())/2, 200);
			}
			
		batcher.end();
		
		/*
		 * kalo mau ngeliat boundnya ada di mana, ubah debug=true 
		 * (line 35 kalo ngga salah)
		 */
		if(debug){
			drawDebug();
		}
		
		//--kalo mau ada sfx atau musik nanti di sini aja--
		
		//----------------end of sfx/musik-----------------
		
		/*
		 * Selesai gambar panggil method processInput() nya
		 * controller, supaya setiap kali selesai gambar
		 * controller ngecek ada yang terjadi apa enggak.
		 */
		controller.processInput();
		
	}
	
	//rectangle boundsnya digambar kalo mau debug==true
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
			if(resumeButtonPressed){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.rect(resumeButtonBounds.x, resumeButtonBounds.y,
					resumeButtonBounds.width, resumeButtonBounds.height);
			
			if(restartButtonPressed){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.rect(restartButtonBounds.x, restartButtonBounds.y,
					restartButtonBounds.width, restartButtonBounds.height);
			
			if(exitButtonPressed){
				debugRenderer.setColor(new Color(1, 1, 0, 1));
			}
			else{
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			}
			debugRenderer.rect(exitButtonBounds.x, exitButtonBounds.y,
					exitButtonBounds.width, exitButtonBounds.height);
		debugRenderer.end();
	}
	
	public int getType(){
		return type;
	}
	
	public SubCerita getSubCerita(){
		return subcerita;
	}
	
	public Screen getOriginScreen(){
		return originScreen;
	}
	
	
	public Rectangle getResumeButtonBounds(){
		return resumeButtonBounds;
	}
	
	public Rectangle getRestartButtonBounds(){
		return restartButtonBounds;
	}
	
	public Rectangle getExitButtonBounds(){
		return exitButtonBounds;
	}
	
	public void setResumeButtonPressed (boolean pressed){
		resumeButtonPressed = pressed;
	}
	
	public boolean resumeButtonIsPressed() {
		return resumeButtonPressed;
	}
	
	public void setRestartButtonPressed (boolean pressed){
		restartButtonPressed = pressed;
	}
	
	public boolean restartButtonIsPressed() {
		return restartButtonPressed;
	}
	
	public void setExitButtonPressed (boolean pressed){
		exitButtonPressed = pressed;
	}
	
	public boolean exitButtonIsPressed() {
		return exitButtonPressed;
	}
	
	/* 
	 * --ignore dulu deh yak, bingung sebenernya buat apa ._.--
	 * 
	 * show() dipanggil saat screennya ini pertama kali
	 * dibuat, di sini objek2 yang diperluin sama
	 * screen, kayak controller, gambar2, dll dibuat
	 * atau diinisialisasi. 
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		
	}
	
}
