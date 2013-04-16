package com.a4.ceritanusantara.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;


public class AdeganBackup extends SubCerita {
	private String [] scene2_kalimantan;
	private String [] scene3_kalimantan;
	private String [] scene4_kalimantan;
	private String [] scene5_kalimantan;
	private String [] scene6_kalimantan;
	private String [] scene7_kalimantan;
	
	private String [] scene1_sumatera;
	private String [] scene2_sumatera;
	private String [] scene3_sumatera;
	private String [] scene4_sumatera;
	private String [] scene5_sumatera;
	private String [] scene6_sumatera;
	private String [] scene7_sumatera;
	private String [] scene8_sumatera;
	
		
	public AdeganBackup(String nama, int tipe) throws IOException {
		super(nama, tipe);		
		// TODO Auto-generated constructor stub
		FileHandle sc1_k=Gdx.files.internal("dialog/dialogNarasiKalimantan/scene1_kalimantan.txt");
		FileHandle sc2_k=Gdx.files.internal("dialog/dialogNarasiKalimantan/scene2_kalimantan.txt");
		FileHandle sc3_k=Gdx.files.internal("dialog/dialogNarasiKalimantan/scene3_kalimantan.txt");
		FileHandle sc4_k=Gdx.files.internal("dialog/dialogNarasiKalimantan/scene4_kalimantan.txt");
		FileHandle sc5_k=Gdx.files.internal("dialog/dialogNarasiKalimantan/scene5_kalimantan.txt");
		FileHandle sc6_k=Gdx.files.internal("dialog/dialogNarasiKalimantan/scene6_kalimantan.txt");
		FileHandle sc7_k=Gdx.files.internal("dialog/dialogNarasiKalimantan/scene7_kalimantan.txt");
		
		FileHandle sc1_s=Gdx.files.internal("dialog/dialogNarasiSumatera/scene1_sumatera.txt");
		FileHandle sc2_s=Gdx.files.internal("dialog/dialogNarasiSumatera/scene2_sumatera.txt");
		FileHandle sc3_s=Gdx.files.internal("dialog/dialogNarasiSumatera/scene3_sumatera.txt");
		FileHandle sc4_s=Gdx.files.internal("dialog/dialogNarasiSumatera/scene4_sumatera.txt");
		FileHandle sc5_s=Gdx.files.internal("dialog/dialogNarasiSumatera/scene5_sumatera.txt");
		FileHandle sc6_s=Gdx.files.internal("dialog/dialogNarasiSumatera/scene6_sumatera.txt");
		FileHandle sc7_s=Gdx.files.internal("dialog/dialogNarasiSumatera/scene7_sumatera.txt");
		FileHandle sc8_s=Gdx.files.internal("dialog/dialogNarasiSumatera/scene8_sumatera.txt");
		
	}
	
	static void getTextKalimantan(String fileName) throws IOException {
		FileInputStream fin = null;
		int i;
		try { 
			fin = new FileInputStream(fileName); 
		} catch(FileNotFoundException e) { 
			System.out.println("File Not Found"); 
		} catch(ArrayIndexOutOfBoundsException e) { 
			System.out.println("Usage: ShowFile File"); 
		} 

		String in = "";
		do {
			i = fin.read(); 
			if(i != -1) in += (char)i;
		}
		while(i != -1); 
		
		String content[] = getContent(in);
		String textArray[];
		String identifier, dialog, dialogIdentifier;
		for (int ii = 0; ii < content.length; ii++) {
			textArray = getTextArray(content[ii]);
			identifier=content[ii].substring(0,4);
			if (identifier.equals("nar:")) {
				for (int jj = 0; jj < textArray.length; jj++) {
					System.out.println(textArray[jj].replace("nar:",""));
				}
			} if (identifier.equals("dia:")) {
				for (int jj = 1; jj < textArray.length; jj++) {
					dialog = textArray[jj];
					dialogIdentifier=dialog.substring(0,4);
					if (dialogIdentifier.equals("nus:")) {
						System.out.println(dialog.replace("nus:", ""));
					} if (dialogIdentifier.equals("ist:")) {
						System.out.println(dialog.replace("ist:", ""));
					} if (dialogIdentifier.equals("adk:")) {
						System.out.println(dialog.replace("adk:", ""));
					} if (dialogIdentifier.equals("sal:")) {
						System.out.println(dialog.replace("sal:", ""));
					} if (dialogIdentifier.equals("jel:")) {
						System.out.println(dialog.replace("jel:", ""));						
					}
				}				
			}			
		}
		fin.close();
	}
	
	static void getTextSumatera(String fileName) throws IOException {
		FileInputStream fin = null;
		int i;
		try { 
			fin = new FileInputStream(fileName); 
		} catch(FileNotFoundException e) { 
			System.out.println("File Not Found"); 
		} catch(ArrayIndexOutOfBoundsException e) { 
			System.out.println("Usage: ShowFile File"); 
		} 
		
		String in = "";
		do {
			i = fin.read(); 
			if(i != -1) in += (char)i;
		}
		while(i != -1); 
	
		String content[] = getContent(in);
		String textArray[];
		String identifier, dialog, dialogIdentifier;
		for (int ii = 0; ii < content.length; ii++) {
			textArray = getTextArray(content[ii]);
			identifier=content[ii].substring(0,4);
			if (identifier.equals("nar:")) {
				for (int jj = 0; jj < textArray.length; jj++) {
					System.out.println(textArray[jj].replace("nar:",""));
				}
			} if (identifier.equals("dia:")) {
				for (int jj = 1; jj < textArray.length; jj++) {
					dialog = textArray[jj];
					dialogIdentifier=dialog.substring(0,4);
					if (dialogIdentifier.equals("spk:")) {
						System.out.println(dialog.replace("spk:", ""));
					} if (dialogIdentifier.equals("pk1:")) {
						System.out.println(dialog.replace("pk1:", ""));
					} if (dialogIdentifier.equals("pk2:")) {
						System.out.println(dialog.replace("pk2:", ""));
					} if (dialogIdentifier.equals("pbr:")) {
						System.out.println(dialog.replace("pbr:", ""));
					} if (dialogIdentifier.equals("rja:")) {
						System.out.println(dialog.replace("rja:", ""));
					} if (dialogIdentifier.equals("pgw:")) {
						System.out.println(dialog.replace("pgw:", ""));
					}
				}				
			}			
		}
		fin.close();
	}
	
	static String [] getContent(String in) {
		StringTokenizer token = new StringTokenizer(in, "^");
		
		ArrayList <String> ret = new ArrayList<String>();
		while (token.hasMoreElements()) {
			ret.add(token.nextElement().toString());
		}
		
		return ret.toArray(new String[0]);
	}
	
	static String [] getTextArray(String in) {
		StringTokenizer token = new StringTokenizer(in, "*");
		
		ArrayList <String> ret = new ArrayList<String>();
		while (token.hasMoreElements()) {
			ret.add(token.nextElement().toString());
		}
		
		return ret.toArray(new String[0]);
	}
}
