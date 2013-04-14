package com.a4.ceritanusantara.views;

import com.a4.ceritanusantara.Aplikasi;
import com.a4.ceritanusantara.controllers.LabirinController;
import com.a4.ceritanusantara.models.Labirin;
import com.a4.ceritanusantara.models.LabirinItem;
import com.a4.ceritanusantara.models.LabirinPlayer;
import com.a4.ceritanusantara.models.LabirinWall;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class LabirinScreen extends AbstractScreen{
	
	private Labirin labirin;
	private LabirinController controller;
	
	private Texture background;
	private Texture wallTexture;
	private Texture[] playerTexture;
	
	private Texture pauseButtonTexture;
	private Texture pauseButtonPressedTexture;
	private Rectangle pauseButtonBounds;
	
	private boolean pauseButtonPressed;
	
	private LabirinWall[] walls;
	private LabirinItem[] items;
	
	private boolean debug = true;
	private Texture itemTexture;
	
	private Texture gameOverBg;
	private Texture replayTexture;
	private Texture replayPressedTexture;
	private Texture mainMenuTexture;
	private Texture mainMenuPressedTexture;
	private Texture nextTexture;
	
	private Rectangle replayBounds;
	private Rectangle mainMenuBounds;
	private Rectangle nextBounds;
	
	private boolean replayButtonPressed;
	private boolean mainMenuButtonPressed;
	private boolean nextButtonPressed;
	
	private BitmapFont font;
	
	public LabirinScreen(Aplikasi app, Labirin labirin) {
		super(app);
		// TODO Auto-generated constructor stub
		this.labirin = labirin;
		labirin.reinit();
		
		walls = labirin.getWalls();
		items = labirin.getItems();
		
		background = labirin.getBackground();
		wallTexture = labirin.getWallTexture();
		playerTexture = labirin.getPlayerTexture();
		itemTexture = labirin.getItemTexture();
		
		pauseButtonTexture = new Texture(
				Gdx.files.internal("buttons/pause.png"));
		pauseButtonPressedTexture = new Texture(
				Gdx.files.internal("buttons/pause_pressed.png"));

		pauseButtonBounds = new Rectangle(950, 526, 60, 60);
		
		pauseButtonPressed = false;
		
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
		
		controller = new LabirinController(this, app, labirin);
		
	}
	
	public void render(float delta){
		cam.update();

	      
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batcher.setProjectionMatrix(cam.combined);
		batcher.begin();
			if(labirin.isGameOver()){
			
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
				
				if(labirin.getScore()>=60){
					batcher.draw(nextTexture, 950, 30);
				}
			
			}
			else{
				batcher.draw(background, 0, 0);
				for(int i=0; i<walls.length; i++){
					batcher.draw(wallTexture, walls[i].getX(), walls[i].getY());
				}
				for(int i=0; i<items.length; i++){
					batcher.draw(itemTexture, items[i].getX(), items[i].getY());
				}
				int state = labirin.getPlayer().getState();
				batcher.draw(playerTexture[state], labirin.getPlayer().getX(), 
						labirin.getPlayer().getY());
			}
		batcher.end();
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

	public Labirin getLabirin() {
		// TODO Auto-generated method stub
		return labirin;
	}

}
