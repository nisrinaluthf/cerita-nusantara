package com.a4.ceritanusantara.utils;

import java.util.StringTokenizer;

import com.a4.ceritanusantara.models.Adegan;
import com.a4.ceritanusantara.models.AdeganText;
import com.badlogic.gdx.files.FileHandle;

/**
 * Kelas InitAdegan merepresentasikan kelas yang digunakan untuk mengambil data
 * untuk keperluan adegan dalam cerita.
 *
 */
public class InitAdegan {

    public static void initAdegan(Adegan adegan, FileHandle file) {
        String data = file.readString();
        StringTokenizer st = new StringTokenizer(data, System.getProperty("line.separator"));

        int bgAmt = Integer.parseInt(st.nextToken().trim());
        //Texture[] bgArray = new Texture[bgAmt];
        String[] bgArrayPath = new String[bgAmt];

        for (int i = 0; i < bgArrayPath.length; i++) {
            bgArrayPath[i] = st.nextToken().trim();
        }

        //Music music = Gdx.audio.newMusic(Gdx.files.internal(st.nextToken().trim()));
        String musicPath = st.nextToken().trim();
        adegan.setMusic(musicPath);

        //Texture[] chara = new Texture[Integer.parseInt(st.nextToken().trim())];
        String[] chara = new String[Integer.parseInt(st.nextToken().trim())];
        for (int i = 0; i < chara.length; i++) {
            chara[i] = st.nextToken().trim();
        }
        adegan.setCharaTexture(chara);

        AdeganText[] adeganText = new AdeganText[Integer.parseInt(st.nextToken().trim())];
        for (int i = 0; i < adeganText.length; i++) {
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), ":");
            String background = bgArrayPath[Integer.parseInt(st2.nextToken().trim())];
            int type = 0;
            if (st2.nextToken().equals("dia")) {
                type = 1;
            }

            int charaType = 0;
            if (type == 1) {
                charaType = Integer.parseInt(st2.nextToken().trim());
            }

            String text = st2.nextToken();
            adeganText[i] = new AdeganText(text, type, background);
            if (type == 1) {
                adeganText[i].setChara(charaType);
            }
        }
        adegan.setAdeganText(adeganText);
    }
}
