package com.a4.ceritanusantara.utils;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;
import com.badlogic.gdx.files.FileHandle;

public class InitKuis {
	public static void initKuis(Kuis kuis, FileHandle file){
		String data = file.readString();
		StringTokenizer st = new StringTokenizer(data, System.getProperty("line.separator"));
	
		KuisQuestion[] questions = new KuisQuestion[5];
		
		for (int i=0; i<questions.length; i++){
			questions[i] = new KuisQuestion(st.nextToken(), 
					st.nextToken(), 
					st.nextToken(), 
					st.nextToken(), 
					st.nextToken(), 
					Integer.parseInt(st.nextToken()));
		}
	
		kuis.setQuestions(questions);
	}
}
