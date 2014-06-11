package com.a4.ceritanusantara.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

/**
 * Kelas <code>Adegan</code> merepresentasikan konten suatu subcerita
 * berjenis adegan. Kelas ini menampung di antaranya teks, gambar,
 * dan musik yang dimainkan pada adegan tersebut. 
 * 
 */
public class Adegan extends SubCerita {
	
	/**
	 * Array yang menampung gambar dari karakter yang ditampilkan.
	 */
	private Texture[] charaTexture;
	
	/**
	 * Array yang menampung teks yang ditampilkan.
	 */
	private AdeganText[] adeganText;
	
	/**
	 * Musik yang dimainkan.
	 */
	private Music music;
	
	/**
	 * Indeks yang menyimpan teks yang sedang ditampilkan pada suatu 
	 * waktu.
	 */
	public int currentText;
	
	/**
	 * Boolean yang menyimpan apakah adegan ini sudah selesai
	 * dimainkan atau belum.
	 */
	private boolean isDone;
	
	/**
	 * Konstruktor dengan parameter nama adegan dan tipe subcerita.
	 * @param nama
	 * @param tipe
	 */
	public Adegan(String nama, int tipe) {
		super(nama, tipe);
		isDone = false;
		currentText = 0;
	}
	
	/**
	 * Set gambar karakter yang ditampilkan pada adegan ini.
	 * @param charaTexture
	 */
	public void setCharaTexture(Texture[] charaTexture){
		this.charaTexture = charaTexture;
	}
	
	/**
	 * Mengembalikan array berisi gambar karakter yang ditampilkan
	 * pada adegan ini.
	 * @return
	 */
	public Texture[] getCharaTexture(){
		return charaTexture;
	}
	
	/**
	 * Mengambil gambar karakter yang ditampilkan 
	 * pada indeks ke-<i>i</i>.
	 * @param i
	 * @return
	 */
	public Texture getCharaTexture(int i){
		return charaTexture[i];
	}
	
	/**
	 * Set teks yang ditampilkan pada adegan ini.
	 * @param adeganText
	 */
	public void setAdeganText(AdeganText[] adeganText){
		this.adeganText = adeganText;
	}
	
	/**
	 * Mengembalikan array berisi teks yang ditampilkan
	 * pada adegan ini.
	 * @return
	 */
	public AdeganText[] getAdeganText(){
		return adeganText;
	}
	
	/**
	 * Mengembalikan teks yang ditampilkan pada adegan ini
	 * pada indeks ke-<i>i</i>.
	 * @param i
	 * @return
	 */
	public AdeganText getAdeganText(int i){
		return adeganText[i];
	}
	
	/**
	 * Set musik yang dimainkan pada adegan ini.
	 * @param music
	 */
	public void setMusic(Music music){
		this.music = music;
	}
	
	/**
	 * Mengembalikan musik yang dimainkan pada adegan ini.
	 * @return
	 */
	public Music getMusic(){
		return music;
	}
	
	/**
	 * Set status adegan menjadi sudah selesai dimainkan.
	 * @return
	 */
	public boolean isDone(){
		return isDone;
	}
	
	/**
	 * Mengembalikan apakah adegan sudah selesai dimainkan atau belum.
	 */
	public void done(){
		isDone = true;
	}
	
	/**
	 * Set indeks dari teks yang sedang ditampilkan ke teks berikutnya.
	 */
	public void updateCurrentText(){
		currentText++;
	}
	
	/**
	 * Mengembalikan indeks dari teks yang sedang ditampilkan
	 * saat ini.
	 * @return
	 */
	public int getCurrentText(){
		return currentText;
	}
	
	/**
	 * Inisialisasi ulang state dari adegan.
	 */
	public void reinit(){
		isDone = false;
		currentText = 0;
	}
	
	/**
	 * Mengembalikan panjang adegan.
	 * @return
	 */
	public int getLength(){
		return adeganText.length;
	}
	
	public void dispose() {
		this.music.dispose();
		for(int idx = 0; idx < this.charaTexture.length; idx++) {
			this.charaTexture[idx].dispose();
		}
	}

	public void setMusic(String musicPath) {
		this.music = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
	}

	public void setCharaTexture(String[] chara) {
		this.charaTexture = new Texture[chara.length];
		
		for(int idx = 0; idx < this.charaTexture.length; idx++) {
			this.charaTexture[idx] = new Texture(Gdx.files.internal(chara[idx]));
		}
	}
	
}