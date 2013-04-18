package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.KuisController;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class KuisScreen extends AbstractScreen{
	
	private KuisController controller;
	
	private Kuis kuis;
	private int currentNo;
	
	private Texture background;
	private Texture soalBackgroundTexture;
	private Texture opsiBackgroundTexture;
	private Texture opsiBackgroundPressedTexture;
	
	private Texture gameOverBg;
	private Texture replayTexture;
	private Texture replayPressedTexture;
	private Texture mainMenuTexture;
	private Texture mainMenuPressedTexture;
	private Texture nextTexture;
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	
	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;
	
	private BitmapFont font;
	
	private boolean[] optionPressed;
	private boolean pauseButtonPressed;
	
	private boolean debug = false;

	private boolean replayButtonPressed;
	private boolean mainMenuButtonPressed;
	private boolean nextButtonPressed;
	
	private Sound falseAnswerSfx;
	
	private Sound rightAnswerSfx;
	
	private Sound pauseClickSfx;
	
	private Music kuisMusicBg;

	private BitmapFont font36;

	public KuisScreen(Aplikasi app, Kuis kuis) {
		super(app);
		// TODO Auto-generated constructor stub
		
		this.kuis = kuis;
		
		kuis.reinit();
		
		currentNo = 0;
		
		background = new Texture(Gdx.files.internal("backgrounds/main_bg.png"));
		soalBackgroundTexture = new 
				Texture(Gdx.files.internal("backgrounds/soal_kuis_bg.png"));
		opsiBackgroundTexture = new 
				Texture(Gdx.files.internal("backgrounds/opsi_kuis_bg.png"));
		opsiBackgroundPressedTexture = new 
				Texture(Gdx.files.internal("backgrounds/opsi_kuis_bg_pressed.png"));
		
		gameOverBg = new
				Texture(Gdx.files.internal("backgrounds/gameover_bg.png"));
		replayTexture = new
				Texture(Gdx.files.internal("buttons/restart.png"));
		replayPressedTexture = new
				Texture(Gdx.files.internal("buttons/restart_pressed.png"));
		mainMenuTexture = new
				Texture(Gdx.files.internal("buttons/mainmenu.png"));
		mainMenuPressedTexture = new
				Texture(Gdx.files.internal("buttons/mainmenu_pressed.png"));
		nextTexture = new
				Texture(Gdx.files.internal("buttons/dialog_next.png"));
		
		//inisialisasi bounds buat game over screen here
		replayBounds = new Rectangle((VIRTUAL_WIDTH-replayTexture.getWidth())/2, 240,
				replayTexture.getWidth(), replayTexture.getHeight());
		mainMenuBounds = new Rectangle((VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2, 180,
				mainMenuTexture.getWidth(), mainMenuTexture.getHeight());
		nextBounds = new Rectangle(950, 30, nextTexture.getWidth(), nextTexture.getHeight());
		
		replayButtonPressed = false;
		mainMenuButtonPressed = false;
		nextButtonPressed = false;
		
		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));

		pauseButtonBounds = new Rectangle( 950, 526, 60, 60);
		
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		font36 = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-36-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-36-black-bold.png"), false);
		
		pauseButtonPressed = false;
		
		kuisMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/kuis.mp3"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.kuisMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				kuisMusicBg.setLooping(true);
				kuisMusicBg.setVolume(0.25f);
				kuisMusicBg.play();
			} 
		} else if(this.kuisMusicBg != null && this.kuisMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		this.falseAnswerSfx = Gdx.audio.newSound(Gdx.files.internal("sound/answer_false.mp3"));
		this.rightAnswerSfx = Gdx.audio.newSound(Gdx.files.internal("sound/answer_true.mp3"));
		this.pauseClickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
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
			if(kuis.isGameOver()){
				
				batcher.draw(gameOverBg, 0, 0);
				if(replayButtonPressed){
					batcher.draw(replayPressedTexture, 
							(VIRTUAL_WIDTH-replayTexture.getWidth())/2, 240);
				}
				else{
					batcher.draw(replayTexture, 
							(VIRTUAL_WIDTH-replayTexture.getWidth())/2, 240);
				}
				
				if(mainMenuButtonPressed){
					System.out.println("mainmenu pressed drawed");
					batcher.draw(mainMenuPressedTexture, 
							(VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2, 180);
				}
				else{
					batcher.draw(mainMenuTexture, 
							(VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2, 180);
				}
				
				if(kuis.getScore()>=60){
					batcher.draw(nextTexture, 950, 30);
					font.draw(batcher, "Skor kamu: "+kuis.getScore(), 380, 400);
				}
				else{
					font.draw(batcher, "Maaf kamu gagal,", 362, 420);
					font.draw(batcher, "silakan coba lagi :)", 358, 370);
				}
				
			}
			
			else{
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
							(VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2, 20);
				}
				else{
					batcher.draw(opsiBackgroundTexture, 
							(VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2, 20);
				}
				
				if(kuis.getKuisQuestion(currentNo).getOptionPressed(2)){
					batcher.draw(opsiBackgroundPressedTexture, 
							((VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2)+
							opsiBackgroundTexture.getWidth()+60, 180);
				}
				else{
					batcher.draw(opsiBackgroundTexture, 
							((VIRTUAL_WIDTH-soalBackgroundTexture.getWidth())/2)+
							opsiBackgroundTexture.getWidth()+60, 180);
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
				
				if (pauseButtonPressed) {
					batcher.draw(pauseButtonPressedTexture,950, 526);
				} else {
					batcher.draw(pauseButtonTexture, 950, 526);
				}
				
				KuisQuestion question = kuis.getKuisQuestion(currentNo);
				
				String tmp = ""+(int)kuis.timeLeft;
				if(tmp.length()==1){
					tmp = "0"+tmp;
				}
				
				font.draw(batcher, "Waktu Tersisa= 0:"+tmp, 350, 570);
				
				font.drawWrapped(batcher, question.getQuestion(), (VIRTUAL_WIDTH-700)/2, 475, 800);
				
				font36.drawWrapped(batcher, question.getOptions(0), 100, 
						290, opsiBackgroundTexture.getWidth()-15);
				
				font36.drawWrapped(batcher, question.getOptions(1), 100, 
						130, opsiBackgroundTexture.getWidth()-15);
				
				font36.drawWrapped(batcher, question.getOptions(2), 
						100+opsiBackgroundTexture.getWidth()+60, 
						290, opsiBackgroundTexture.getWidth()-15);
				
				font36.drawWrapped(batcher, question.getOptions(3), 
						100+opsiBackgroundTexture.getWidth()+60, 
						130, opsiBackgroundTexture.getWidth()-15);
				
				
			}
		batcher.end();
		
		
		if(debug){
			drawDebug();
		}
		
		
		controller.processInput(delta);
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
	
	public Rectangle getPauseButtonBounds(){
		return pauseButtonBounds;
	}

	public void setPauseButtonPressed(boolean b) {
		pauseButtonPressed = b;
		
	}
	
	public boolean pauseButtonIsPressed(){
		return pauseButtonPressed;
	}
	
	public Rectangle getReplayBounds(){
		return replayBounds;
	}
	
	public Rectangle getMainMenuBounds(){
		return mainMenuBounds;
	}
	
	public Rectangle getNextBounds(){
		return nextBounds;
	}
	
	public void setReplayButtonPressed(boolean b){
		replayButtonPressed = b;
	}
	
	public void setMainMenuButtonPressed(boolean b){
		mainMenuButtonPressed = b;
	}
	
	public void setNextButtonPressed(boolean b){
		nextButtonPressed = b;
	}
	
	public boolean replayButtonIsPressed(){
		return replayButtonPressed;
	}
	
	public boolean mainMenuButtonIsPressed(){
		return mainMenuButtonPressed;
	}
	
	public boolean nextButtonIsPressed(){
		return nextButtonPressed;
	}
	
	public void playSoundFx(String key) {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			if (key.equals("default"))
				this.pauseClickSfx.play();
			else if (key.equals("false"))
				this.falseAnswerSfx.play();
			else if (key.equals("true"))
				this.rightAnswerSfx.play();
	}
	
	
	public void stopMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.kuisMusicBg.getPosition());
		System.out.println("stop");
		if(this.kuisMusicBg != null) {
			if (this.kuisMusicBg.isPlaying()) {
				if (this.kuisMusicBg.isLooping()) {
					this.kuisMusicBg.setLooping(false);
				}
				this.kuisMusicBg.stop();
				this.kuisMusicBg.dispose();
				this.kuisMusicBg = null;
			}
			this.kuisMusicBg = null;
		}
	}
	public void pauseMusic() {
		//Gdx.app.getPreferences("preferences").putFloat("music_pos", this.kuisMusicBg.getPosition());
		System.out.println("stop");
		if(this.kuisMusicBg != null) {
			if (this.kuisMusicBg.isPlaying()) {
				if (this.kuisMusicBg.isLooping()) {
					this.kuisMusicBg.setLooping(false);
				}
				this.kuisMusicBg.pause();
				//this.kuisMusicBg.dispose();
				//this.kuisMusicBg = null;
			}
			//this.kuisMusicBg = null;
		}
	}
	
	public void resume() {
		super.resume();
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			//System.out.println("play music");
			if (this.kuisMusicBg != null) {
				System.out.println("play music");
				//Gdx.app.getPreferences("preferences").getFloat("music_pos");
				kuisMusicBg.setLooping(true);
				kuisMusicBg.setVolume(0.25f);
				kuisMusicBg.play();
			} else {
				this.kuisMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/kuis.mp3"));
				System.out.println("play music after null");
				kuisMusicBg.setLooping(true);
				kuisMusicBg.setVolume(0.25f);
				kuisMusicBg.play();
			}
		} else if(this.kuisMusicBg != null && this.kuisMusicBg.isPlaying()) {
			this.stopMusic();
		}
	}
	
}
