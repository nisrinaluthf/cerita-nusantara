package com.a4.ceritanusantara.models;

public class AdeganText {
	public static final int NAR = 0;
	public static final int DIA = 1;
	
	private String text;
	private int type;
	private float end;
	private int chara;
	
	public AdeganText(String text, int type, float end, int chara){
		this.text = text;
		this.type = type;
		this.end = end;
		this.chara = chara;
	}
	
	public String getText(){
		return text;
	}
	
	public int getType(){
		return type;
	}
	
	public float getEnd(){
		return end;
	}
	
	public int getChara(){
		return chara;
	}
}
