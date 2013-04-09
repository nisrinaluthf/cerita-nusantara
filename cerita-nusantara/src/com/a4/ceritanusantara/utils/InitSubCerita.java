package com.a4.ceritanusantara.utils;

import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;

public class InitSubCerita {
	
	public static void initSumatera(Cerita cerita){
		
		((Kuis) cerita.getSubCerita(9)).setQuestions(new KuisQuestion[]{
				new KuisQuestion("Apa strategi raja parkit agar terbebas dari tangkapan si pemburu?", 
						"A. berpura-pura tidur", 
						"B. berusaha melepas perekat", 
						"C. berpura-pura mati", 
						"D. meronat meminta tolong", 
						2),
				new KuisQuestion("1+2=?", "87", "12", "3", "4" , 2),
				new KuisQuestion("11+1=?", "245", "12", "9", "76" , 1),
				new KuisQuestion("3+2=?", "4", "5", "6", "7" , 1),
				new KuisQuestion("198-54=?", "144", "148", "60", "134" , 0)
		});
	}
}
