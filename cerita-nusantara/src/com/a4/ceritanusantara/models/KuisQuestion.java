package com.a4.ceritanusantara.models;

public class KuisQuestion {
	private String question;
	private String[] options;
	private char answer;
	
	public KuisQuestion(String question, String optionA, 
			String optionB, String optionC, String optionD, char answer){
		
		this.question = question;
		this.answer = answer;
		
		options = new String[4];
		options[0] = optionA;
		options[1] = optionB;
		options[2] = optionC;
		options[3] = optionD;
		
	}
	
	public String getQuestions(){
		return question;
	}
	
	public char getAnswer(){
		return answer;
	}
	
	
}
