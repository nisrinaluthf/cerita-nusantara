package com.a4.ceritanusantara.models;

public class Kuis extends SubCerita{
	
	private int score;
	private KuisQuestion[] questions;
	private int currentNo;
	public int timeLeft;

	public Kuis(String nama, int tipe) {
		super(nama, tipe);
		questions = new KuisQuestion[5];
		currentNo = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setQuestions(KuisQuestion[] questions){
		for(int i=0; i<questions.length; i++){
			this.questions[i] = questions[i];
		}
	}
	
	public KuisQuestion getKuisQuestion(int i){
		return questions[i];
	}
	
	public void setCurrentNo(int i){
		currentNo = i;
	}
	
	public int getCurrentNo(){
		return currentNo;
	}
	
}
