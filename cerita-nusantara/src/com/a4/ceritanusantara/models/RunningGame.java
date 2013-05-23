package com.a4.ceritanusantara.models;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class RunningGame extends SubCerita {
	private Texture background;
	private Texture PanelBgTexture;
	private boolean gameOver;
	private Texture player;
	private float playerXPosition;
	private Texture progressIcon;
	private int health;
	private float distance;
	private final float finishLine = 5000.0f;
	private final float leftLimit = 250f;
	private final float rightLimit = 800f;
	private float VELOCITY;
	private float time;
	private Random rand;
	private float[] lastObstacles;
	private List<RunningGameObstacle> obstacles;
	private float backgroundYPosition;

	public RunningGame(String nama, int tipe) {
		super(nama, tipe);
		playerXPosition = 512.0f;
		VELOCITY = 3.0f;
		rand = new Random();
		backgroundYPosition = 0.0f;
		distance = 0.0f;
		// TODO Auto-generated constructor stub
	}
	
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return this.gameOver;
	}

	public void reinit() {
		// TODO Auto-generated method stub
		playerXPosition = 512.0f;
		VELOCITY = 3.0f;
		rand = new Random();
		backgroundYPosition = 0.0f;
		distance = 0.0f;
	}
	/*
	public void generateTargets(float delta){
		if(gameOver) return;
		
		time += delta;
		if(rand.nextFloat()>0.97){
			int index = rand.nextInt(3);
			if(time>(lastObstacles[index]+1.0)){
				lastObstacles[index] = time;
				obstacles.add(new RunningGameObstacle(index));
			}
		}
	}
	*/
	public void updatePosition (){
		if (gameOver) return;
		/*
		Iterator<RunningGameObstacle> itr = obstacles.iterator();
		while(itr.hasNext()){
			RunningGameObstacle obs = itr.next();
			obs.setPos(obs.getPos() + this.VELOCITY);
			
			if(obs.getPos()<-126){
				itr.remove();
			}
		}
		*/
		if(backgroundYPosition > -604 )
			this.backgroundYPosition -=VELOCITY;
		else backgroundYPosition = 0.0f;
		this.distance +=VELOCITY;
	}
	
	public void setGameOver() {
		// TODO Auto-generated method stub
		this.gameOver = true;
	}

	public Texture getBackground() {
		return background;
	}
	public void setBackground(Texture background) {
		this.background = background;
	}
	public Texture getPanelBackground() {
		return PanelBgTexture;
	}
	public void setPanelBackground(Texture panelBgTexture) {
		PanelBgTexture = panelBgTexture;
	}
	public Texture getPlayer() {
		return player;
	}
	public void setPlayer(Texture player) {
		this.player = player;
	}
	public float getPlayerXPosition() {
		return playerXPosition;
	}
	public void setPlayerXPosition(float playerXPosition) {
		this.playerXPosition = playerXPosition;
	}
	public Texture getProgressIcon() {
		return progressIcon;
	}
	public void setProgressIcon(Texture progressIcon) {
		this.progressIcon = progressIcon;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public float getFinishLine() {
		return finishLine;
	}
	public float getLeftLimit() {
		return leftLimit;
	}
	public float getRightLimit() {
		return rightLimit;
	}
	public float getVELOCITY() {
		return VELOCITY;
	}
	public void setVELOCITY(float velocity) {
		VELOCITY = velocity;
	}

	public float getBackgroundYPosition() {
		return backgroundYPosition;
	}

	public void setBackgroundYPosition(float backgroundPosition) {
		this.backgroundYPosition = backgroundPosition;
	}

}
