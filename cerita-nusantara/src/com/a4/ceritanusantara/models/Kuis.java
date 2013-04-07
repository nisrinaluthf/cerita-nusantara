package com.a4.ceritanusantara.models;

public class Kuis extends SubCerita{
	
	private int score;
	private KuisQuestion[] questions;

	public Kuis(String nama, int tipe) {
		super(nama, tipe);
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
			
		}
	}
	
	public KuisQuestion[] getKuisQuestion(){
		return questions;
	}

}
