package com.a4.ceritanusantara.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;

/**
 * Kelas TapGame merepresentasikan permainan bertipe Tap Game.
 *
 */
public class TapGame extends SubCerita {
    /*
     * Kelas ini merupakan class model yang berisikan bagian dari permainan
     * yang bertipe tap gam, seperti tombol, life bar, dan area permainan.
     */

    private Texture background;
    private Texture PanelBgTexture;
    private TapGameButton[] buttons;
    private List<TapGameTarget> targets;
    private Texture[] targetsTexture;
    private Texture[] targetsPressedTexture;
    private Texture[] indicatorsTexture;
    private int hit;
    private int badHit;
    private Random rand;
    private float time;
    private float[] latestGeneration;
    private boolean gameOver;

    /**
     * Konstruktor dengan parameter nama dan tipe (bagian dari subcerita).
     *
     * @param nama
     * @param tipe
     */
    public TapGame(String nama, int tipe) {
        super(nama, tipe);
        rand = new Random();
        time = 0;
        latestGeneration = new float[]{-2f, -1.5f, -1f};
        targets = new ArrayList<TapGameTarget>();
        hit = 5;
        badHit = 0;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public void setPanelBackground(Texture panelBgTexture) {
        this.PanelBgTexture = panelBgTexture;
    }

    public void setButtons(TapGameButton[] buttons) {
        this.buttons = buttons;
    }

    public void setTargetsTexture(Texture[] targetsTexture) {
        this.targetsTexture = targetsTexture;
    }

    public void setTargetsPressedTexture(Texture[] targetsPressedTexture) {
        this.targetsPressedTexture = targetsPressedTexture;
    }

    public void setIndicators(Texture[] indicatorsTexture) {
        this.indicatorsTexture = indicatorsTexture;
    }

    public void setHits(int hits) {
        this.hit = hits;
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getPanelBackground() {
        return PanelBgTexture;
    }

    public TapGameButton[] getButtons() {
        return buttons;
    }

    public List<TapGameTarget> getTargets() {
        return targets;
    }

    public Texture[] getTargetsTexture() {
        return targetsTexture;
    }

    public Texture[] getTargetsPressedTexture() {
        return targetsPressedTexture;
    }

    public Texture[] getIndicators() {
        return indicatorsTexture;
    }

    public int getHits() {
        return hit;
    }

    public void addBadHit() {
        badHit++;
    }

    public void generateTargets(float delta) {
        if (gameOver) {
            return;
        }

        time += delta;
        if (rand.nextFloat() > 0.97) {
            int index = rand.nextInt(3);
            if (time > (latestGeneration[index] + 1.0)) {
                latestGeneration[index] = time;
                int type = rand.nextInt(3);
                boolean bad = false;
                if (rand.nextFloat() > 0.7) {
                    bad = true;
                    type += 3;
                }
                targets.add(new TapGameTarget(index, type, bad));
            }
        }
    }

    public void updateTargets(float delta) {
        if (gameOver) {
            return;
        }
        Iterator<TapGameTarget> itr = targets.iterator();
        while (itr.hasNext()) {
            TapGameTarget target = itr.next();
            target.update();
            if (target.getPos() < -126) {
                itr.remove();
            }
        }

    }

    public int getBadHits() {
        return badHit;
    }

    public void reinit() {
        rand = new Random();
        time = 0;
        latestGeneration = new float[]{-2f, -1.5f, -1f};
        targets = new ArrayList<TapGameTarget>();
        hit = 5;
        badHit = 0;
        gameOver = false;
    }

    public void gameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    
    public void dispose() {
		this.background.dispose();
		this.PanelBgTexture.dispose();
		for(int idx = 0; idx < this.targetsTexture.length; idx++) {
			this.targetsTexture[idx].dispose();
		}
		for(int idx = 0; idx < this.targetsPressedTexture.length; idx++) {
			this.targetsPressedTexture[idx].dispose();
		}
		for(int idx = 0; idx < this.indicatorsTexture.length; idx++) {
			this.indicatorsTexture[idx].dispose();
		}
	}
}
