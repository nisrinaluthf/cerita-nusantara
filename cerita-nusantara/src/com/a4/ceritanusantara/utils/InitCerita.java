package com.a4.ceritanusantara.utils;

import com.a4.ceritanusantara.models.Cerita;
import com.a4.ceritanusantara.models.Kuis;
import com.a4.ceritanusantara.models.KuisQuestion;

public class InitCerita {
	
	public static void initSumatera(Cerita cerita){
		
		((Kuis) cerita.getSubCerita(9)).setQuestions(new KuisQuestion[]{
				new KuisQuestion("1+1=?", "2", "12", "11", "43" , 'a'),
				new KuisQuestion("1+2=?", "87", "12", "3", "4" , 'c'),
				new KuisQuestion("11+1=?", "245", "12", "9", "76" , 'b'),
				new KuisQuestion("3+2=?", "4", "5", "6", "7" , 'b'),
				new KuisQuestion("198-54=?", "144", "148", "60", "134" , 'd')
		});
	}
}
