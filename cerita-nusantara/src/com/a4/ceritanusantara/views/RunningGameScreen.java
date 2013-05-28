package com.a4.ceritanusantara.views;

import java.util.Iterator;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.RunningGameController;
import com.a4.ceritanusantara.models.RunningGame;
import com.a4.ceritanusantara.models.RunningGameObstacle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class RunningGameScreen extends AbstractScreen{
	private RunningGame runningGame;
	private RunningGameController controller;
	
	private Texture background;
	private Texture panelBgTexture;
	private Texture background2;
	private Texture panelBgTexture2;
	
	private Texture playerTexture;
	private Texture progressBar;
	private Texture progressIcon;
	private Texture healthIcon;
	private Texture finishLine;
	
	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;
	
	private boolean pauseButtonPressed;
	
	private Texture helpButtonTexture;
	private Texture helpButtonPressedTexture;
	private Rectangle helpButtonBounds;
	
	private boolean helpButtonPressed;
	
	private Texture gameOverBg;
	private Texture replayTexture;
	private Texture replayPressedTexture;
	private Texture mainMenuTexture;
	private Texture mainMenuPressedTexture;
	private Texture nextTexture;
	
	private Texture leftButtonTexture;
	private Texture rightButtonTexture;
	private Texture leftButtonPressedTexture;
	private Texture rightButtonPressedTexture;
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	
	private Rectangle leftButtonBounds;
	private Rectangle rightButtonBounds;
	
	//private Rectangle playerBounds;
	
	//private runningGameButton[] buttons;
	
	private boolean debug = false;
	
	private boolean replayButtonPressed;
	private boolean mainMenuButtonPressed;
	private boolean nextButtonPressed;
	
	private boolean leftButtonPressed;
	private boolean rightButtonPressed;
	
	private BitmapFont font;
	
	private Sound hitObstacleSfx;
	
	//private Sound rightAnswerSfx;
	
	private Sound pauseClickSfx;
	
	private Music runningGameMusicBg;

	private Animation runner;
	private TextureRegion[] runFrames;
	private TextureRegion currentFrame;
	private final int FRAME_COLS = 2;
	private final int FRAME_ROWS = 1;
	private float stateTime;
	private Texture panel;
	
	public RunningGameScreen(Aplikasi app, RunningGame runningGame) {
		super(app);
		// TODO Auto-generated constructor stub
		
		this.runningGame = runningGame;
		runningGame.reinit();
		
		background = runningGame.getBackground();
		panelBgTexture = runningGame.getPanelBackground();
		background2 = runningGame.getBackground();
		panelBgTexture2 = runningGame.getPanelBackground();
		//targetsTexture = runningGame.getTargetsTexture();
		//targetsPressedTexture = runningGame.getTargetsPressedTexture();
		//indicatorsTexture = runningGame.getIndicators();
		
		gameOverBg = 
				new Texture(Gdx.files.internal("backgrounds/bg.png"));
		panel = 
				new Texture(Gdx.files.internal("backgrounds/settings_bg.png"));
		replayTexture = new
				Texture(Gdx.files.internal("buttons/restart_80.png"));
		replayPressedTexture = new
				Texture(Gdx.files.internal("buttons/restart_pressed_80.png"));
		mainMenuTexture = new
				Texture(Gdx.files.internal("buttons/mainmenu_80.png"));
		mainMenuPressedTexture = new
				Texture(Gdx.files.internal("buttons/mainmenu_pressed_80.png"));
		nextTexture = new
				Texture(Gdx.files.internal("buttons/dialog_next.png"));
		
		playerTexture = runningGame.getPlayer();
		TextureRegion[][] temp = TextureRegion.split(playerTexture, playerTexture.getWidth()/FRAME_COLS, playerTexture.getHeight()/FRAME_ROWS);
		this.runFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
		int index= 0;
		for(int ii = 0; ii < FRAME_ROWS; ii++) {
			for(int jj = 0; jj < FRAME_COLS; jj++) {
				runFrames[index++] = temp[ii][jj];
			}
		}
		runner = new Animation(0.5f, runFrames);
		stateTime = 0f;
		
		progressIcon = runningGame.getProgressIcon();
		
		progressBar = new
				Texture(Gdx.files.internal("selatbali_running/progress_bar.png"));
		healthIcon = new
				Texture(Gdx.files.internal("selatbali_running/health.png"));
		
		finishLine = new
				Texture(Gdx.files.internal("selatbali_running/finish_line.png"));
		
		leftButtonTexture = new
				Texture(Gdx.files.internal("buttons/left.png"));
		rightButtonTexture = new
				Texture(Gdx.files.internal("buttons/right.png"));
		leftButtonPressedTexture = new
				Texture(Gdx.files.internal("buttons/left_pressed.png"));
		rightButtonPressedTexture = new
				Texture(Gdx.files.internal("buttons/right_pressed.png"));
		
		
		
		//inisialisasi bounds buat game over screen here
		replayBounds = new Rectangle((VIRTUAL_WIDTH-replayTexture.getWidth())/2+50, 200,
				replayTexture.getWidth(), replayTexture.getHeight());
		mainMenuBounds = new Rectangle((VIRTUAL_WIDTH-mainMenuTexture.getWidth())/2-50, 200,
				mainMenuTexture.getWidth(), mainMenuTexture.getHeight());
		nextBounds = new Rectangle(950, 30, nextTexture.getWidth(), nextTexture.getHeight());
		
		leftButtonBounds = new Rectangle(20, (VIRTUAL_HEIGHT-leftButtonTexture.getHeight())/2,
				leftButtonTexture.getWidth(), leftButtonTexture.getHeight());
		rightButtonBounds = new Rectangle(VIRTUAL_WIDTH-20-rightButtonTexture.getWidth(), (VIRTUAL_HEIGHT-rightButtonTexture.getHeight())/2,
				rightButtonTexture.getWidth(), rightButtonTexture.getHeight());
		
		replayButtonPressed = false;
		mainMenuButtonPressed = false;
		nextButtonPressed = false;
		
		leftButtonPressed = false;
		rightButtonPressed = false;
		
		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));

		pauseButtonBounds = new Rectangle(VIRTUAL_WIDTH-pauseButtonTexture.getWidth()-7, 
				VIRTUAL_HEIGHT-pauseButtonTexture.getHeight()-7, 
				pauseButtonTexture.getWidth(), pauseButtonTexture.getHeight());
		
		pauseButtonPressed = false;
		
		helpButtonTexture = new Texture(
				Gdx.files.internal("buttons/help.png"));
		helpButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/help_pressed.png"));

		helpButtonBounds = new Rectangle(7, VIRTUAL_HEIGHT-helpButtonTexture.getHeight()-7, 
				helpButtonTexture.getWidth(), helpButtonTexture.getHeight());
		
		helpButtonPressed = false;
		
		//scoreBgTexture = new Texture(Gdx.files.internal("selatbali_running/score_bg.png"));
		//scoreFrameTexture = new Texture(Gdx.files.internal("selatbali_running/score_frame.png"));
		
		font = new BitmapFont(Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.fnt"),
				Gdx.files.internal("fonts/sf-cartoonist-hand-44-black-bold.png"), false);
		
		//buttons = runningGame.getButtons();
		
		runningGameMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/runningGame.ogg"));
		
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.runningGameMusicBg != null) {
				runningGameMusicBg.setLooping(true);
				runningGameMusicBg.setVolume(1.0f);
				runningGameMusicBg.play();
			} 
		} else if(this.runningGameMusicBg != null && this.runningGameMusicBg.isPlaying()) {
			this.stopMusic();
		}
		
		this.hitObstacleSfx = Gdx.audio.newSound(Gdx.files.internal("sound/runninggame_miss.ogg"));
		//this.rightAnswerSfx = Gdx.audio.newSound(Gdx.files.internal("sound/runninggame_hit.mp3"));
		this.pauseClickSfx = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
		
		controller = new RunningGameController(this, app, this.runningGame);
	}
	
	public void render(float delta){
		
		//runningGame.generateTargets(delta);
		//runningGame.updateTargets(delta);
		runningGame.generateObstacles(delta);
		//runningGame.updatePosition();
		cam.update();

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
        batcher.setProjectionMatrix(cam.combined);
        if(runningGame.isGameOver()){
			batcher.begin();
				batcher.draw(gameOverBg, 0, 0);
				batcher.draw(panel, (VIRTUAL_WIDTH-panel.getWidth())/2, 
						(VIRTUAL_HEIGHT-panel.getHeight())/2);
				
				if(replayButtonPressed){
					batcher.draw(replayPressedTexture, 
							replayBounds.x, replayBounds.y);
				}
				else{
					batcher.draw(replayTexture, 
							replayBounds.x, replayBounds.y);
				}
				
				if(mainMenuButtonPressed){
					batcher.draw(mainMenuPressedTexture, 
							mainMenuBounds.x, mainMenuBounds.y);
				}
				else{
					batcher.draw(mainMenuTexture, 
							mainMenuBounds.x, mainMenuBounds.y);
				}				
				
				if(runningGame.getScore()>=50){
					batcher.draw(nextTexture, 950, 30);
					font.draw(batcher, "Skor kamu: "+runningGame.getScore(), 380, 400);
				}
				else{
					font.draw(batcher, "Maaf kamu gagal,", 362, 420);
					font.draw(batcher, "silakan coba lagi :)", 358, 370);
				}
				
			batcher.end();
		}
        else{
        	playerTexture = runningGame.getProgressIcon();
			batcher.begin();
				
				batcher.draw(background, 0, runningGame.getBackgroundYPosition());
				batcher.draw(panelBgTexture, 
						(VIRTUAL_WIDTH-panelBgTexture.getWidth())/2, runningGame.getBackgroundYPosition());
				batcher.draw(background2, 0, runningGame.getBackgroundYPosition() + background2.getHeight());
				batcher.draw(panelBgTexture2, 
						(VIRTUAL_WIDTH-panelBgTexture2.getWidth())/2, runningGame.getBackgroundYPosition() + panelBgTexture2.getHeight());
				int healthPosition = 800;
				for(int ii = 0; ii < runningGame.getHealth(); ii++) {
					batcher.draw(healthIcon, healthPosition, 20);
					healthPosition += healthIcon.getWidth()+7;
				}
				
				batcher.draw(finishLine, (VIRTUAL_WIDTH-finishLine.getWidth())/2, 10+(runningGame.getFinishLine()-runningGame.getDistance()));
				
				batcher.draw(progressBar, 870, 80);
				batcher.draw(progressIcon, 870, 60 + (runningGame.getDistance()/runningGame.getFinishLine()*(this.progressBar.getHeight()-40)));
				
				
				
				//batcher.draw(scoreBgTexture, 900, 80);
				if (pauseButtonPressed) {
					batcher.draw(pauseButtonPressedTexture, pauseButtonBounds.getX(), 
							pauseButtonBounds.getY());
				} else {
					batcher.draw(pauseButtonTexture, pauseButtonBounds.getX(), 
							pauseButtonBounds.getY());
				}
				
				if (helpButtonPressed) {
					batcher.draw(helpButtonPressedTexture, helpButtonBounds.getX(), 
							helpButtonBounds.getY());
				} else {
					batcher.draw(helpButtonTexture, helpButtonBounds.getX(), 
							helpButtonBounds.getY());
				}
				
				if (leftButtonPressed) {
					batcher.draw(leftButtonPressedTexture,20, (VIRTUAL_HEIGHT-leftButtonTexture.getHeight())/2);
				} else {
					batcher.draw(leftButtonTexture, 20, (VIRTUAL_HEIGHT-leftButtonTexture.getHeight())/2);
				}
				
				if (rightButtonPressed) {
					batcher.draw(rightButtonPressedTexture,VIRTUAL_WIDTH-20-rightButtonTexture.getWidth(), (VIRTUAL_HEIGHT-rightButtonTexture.getHeight())/2);
				} else {
					batcher.draw(rightButtonTexture, VIRTUAL_WIDTH-20-rightButtonTexture.getWidth(), (VIRTUAL_HEIGHT-rightButtonTexture.getHeight())/2);
				}
				
				
			//batcher.end();
			/*
			debugRenderer.begin(ShapeType.FilledRectangle);
				int hits = runningGame.getHits();
				if(hits>10){
					debugRenderer.setColor(new Color(0, 1, 0, 1));
				}
				else if(hits>4){
					debugRenderer.setColor(new Color(1, 1, 0, 1));
				}
				else{
					debugRenderer.setColor(new Color(1, 0, 0, 1));
				}
				debugRenderer.filledRect(904, 84, 35, (hits/25.0f)*480);
			debugRenderer.end();
			*/
		//	batcher.begin();
			/*
				batcher.draw(scoreFrameTexture, 900, 80);
				if(runningGame.getHits()>10){
					batcher.draw(indicatorsTexture[0], 865, 10);
				}
				else{
					batcher.draw(indicatorsTexture[1], 865, 0);
				}
			*/
			
				Iterator<RunningGameObstacle> itr = runningGame.getObstacles().iterator();
				while(itr.hasNext()){
					RunningGameObstacle obs = itr.next();
					//if(obs.isHit()){
						//Texture obsTexture;
						//float position;
					float xPosition;
					float panelWidth = this.panelBgTexture.getWidth();
					float obstacleWidth = obs.getObstacleTexture().getWidth();
						if (obs.getIndex() == 0) {
							xPosition = ((3*VIRTUAL_WIDTH-panelWidth-(3*obstacleWidth))/6);
						} else if (obs.getIndex() == 1) {
							xPosition = ((VIRTUAL_WIDTH-obstacleWidth)/2);
						} else {
							xPosition = ((3*VIRTUAL_WIDTH+panelWidth-(3*obstacleWidth))/6);
						}
						batcher.draw(obs.getObstacleTexture(), 
								xPosition,
								obs.getPos());
						float diffX = obstacleWidth / 10;
						float diffY = obs.getObstacleTexture().getHeight()/10;
						obs.setBounds(new Rectangle(xPosition+diffX, obs.getPos()+(diffY*2), obstacleWidth-(diffX*2), obs.getObstacleTexture().getHeight()-(diffY*2)));
				}
				
				runningGame.setPlayerBounds(runningGame.getPlayerXPosition()-(this.playerTexture.getWidth()/2));
				stateTime += Gdx.graphics.getDeltaTime();
				currentFrame = runner.getKeyFrame(stateTime, true);
				batcher.draw(currentFrame, runningGame.getPlayerXPosition()-(this.playerTexture.getWidth()/2), 10);
				
					/*
					 
					 
					else{
						batcher.draw(targetsTexture[target.getType()], 
								target.getIndex() == 0 ? 274 : target.getIndex() == 1 ? 449 : 634,
								target.getPos());
					}
					
							
				}
				
				for(int i=0; i<buttons.length; i++){
					if(buttons[i].isPressed()){
						batcher.draw(buttons[i].getButtonPressedTexture(), 
								buttons[i].getX(), buttons[i].getY());
					}
					else{
						batcher.draw(buttons[i].getButtonTexture(), 
								buttons[i].getX(), buttons[i].getY());
					}
				}
				*/
			batcher.end();
			
        }	
		
		if(debug){
			drawDebug();
		}
		runningGame.updatePosition();
		controller.processInput();
	}
	
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		/*
			for(int i=0; i<buttons.length; i++){
				debugRenderer.rect(buttons[i].getBounds().x, buttons[i].getBounds().y,
						buttons[i].getBounds().getWidth(), buttons[i].getBounds().getHeight());
			}
			
*/
			Iterator<RunningGameObstacle> itr = runningGame.getObstacles().iterator();
			while(itr.hasNext()){
				Rectangle rect = itr.next().getBounds();
				debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
			}	
			Rectangle playerBound = runningGame.getPlayerBounds();
			debugRenderer.rect(playerBound.x, playerBound.y, playerBound.width, playerBound.height);
		debugRenderer.end();
	}

	public Rectangle getLeftButtonBounds(){
		return leftButtonBounds;
	}

	public void setLeftButtonPressed(boolean b) {
		leftButtonPressed = b;
		
	}
	
	public boolean leftButtonIsPressed(){
		return leftButtonPressed;
	}
	
	public Rectangle getRightButtonBounds(){
		return rightButtonBounds;
	}

	public void setRightButtonPressed(boolean b) {
		rightButtonPressed = b;
		
	}
	
	public boolean rightButtonIsPressed(){
		return rightButtonPressed;
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
	
	public Rectangle getHelpButtonBounds(){
		return helpButtonBounds;
	}

	public void setHelpButtonPressed(boolean b) {
		helpButtonPressed = b;
	}
	
	public boolean helpButtonIsPressed(){
		return helpButtonPressed;
	}
	
	public void playSoundFx(String key) {
		if(Gdx.app.getPreferences("preferences").getBoolean("soundOn"))
			if (key.equals("default"))
				this.pauseClickSfx.play();
			else if (key.equals("false"))
				this.hitObstacleSfx.play();
	}
	
	
	public void stopMusic() {
		if(this.runningGameMusicBg != null) {
			if (this.runningGameMusicBg.isPlaying()) {
				if (this.runningGameMusicBg.isLooping()) {
					this.runningGameMusicBg.setLooping(false);
				}
				this.runningGameMusicBg.stop();
				this.runningGameMusicBg.dispose();
				this.runningGameMusicBg = null;
			}
			this.runningGameMusicBg = null;
		}
	}
	public void pauseMusic() {
		if(this.runningGameMusicBg != null) {
			if (this.runningGameMusicBg.isPlaying()) {
				if (this.runningGameMusicBg.isLooping()) {
					this.runningGameMusicBg.setLooping(false);
				}
				this.runningGameMusicBg.pause();
				//this.runningGameMusicBg.dispose();
				//this.runningGameMusicBg = null;
			}
			//this.runningGameMusicBg = null;
		}
	}
	
	public void resume() {
		super.resume();
		if(Gdx.app.getPreferences("preferences").getBoolean("musicOn")) {
			if (this.runningGameMusicBg != null) {
				runningGameMusicBg.play();
			} else {
				this.runningGameMusicBg = Gdx.audio.newMusic(Gdx.files.internal("music/runningGame.ogg"));
				runningGameMusicBg.setLooping(true);
				runningGameMusicBg.setVolume(1.0f);
				runningGameMusicBg.play();
			}
		} else if(this.runningGameMusicBg != null && this.runningGameMusicBg.isPlaying()) {
			this.stopMusic();
		}
	}
}
