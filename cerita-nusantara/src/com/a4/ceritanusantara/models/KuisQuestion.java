package com.a4.ceritanusantara.models;

import com.badlogic.gdx.math.Rectangle;

public class KuisQuestion {
	private String question;
	private String[] options;
	private int answer;
	private Rectangle[] bounds;
	private boolean[] optionPressed;
	
	public KuisQuestion(String question, String optionA, 
			String optionB, String optionC, String optionD, int answer){
		
		this.question = question;
		
		options = new String[4];
		options[0] = optionA;
		options[1] = optionB;
		options[2] = optionC;
		options[3] = optionD;
		
		this.answer = answer;
		
		bounds = new Rectangle[4];
		bounds[0] = new Rectangle((1024-860)/2, 180, 400, 140);
		bounds[1] = new Rectangle(((1024-860)/2)+460, 180, 400, 140);
		bounds[2] = new Rectangle((1024-860)/2, 20, 400, 140);
		bounds[3] = new Rectangle(((1024-860)/2)+460, 20, 400, 140);
		
		optionPressed = new boolean[4];
		for(int i=0; i<optionPressed.length; i++){
			optionPressed[i] = false;
		}
		
		
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getOptions(int i){
		return options[i];
	}
	
	public int getAnswer(){
		return answer;
	}
	
	public Rectangle[] getBounds(){
		return bounds;
	}
	
	public boolean getOptionPressed(int i){
		return optionPressed[i];
	}
	
	public void setOptionPressed(int i, boolean pressed){
		optionPressed[i] = pressed;
	}
	
	
}
