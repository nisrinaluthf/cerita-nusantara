package com.a4.ceritanusantara.models;

/**
 * Kelas SubCerita merepresentasikan bagian-bagian utama dalam CeritaNusantara
 *
 */
public class SubCerita {
    /*
     * Kelas ini merupakan class model yang berisikan tiga sub cerita pada
     * CeritaNusantara, yaitu adegan, permainan dan kuis.
     */

    public static final int ADEGAN = 0;
    public static final int LABIRIN = 1;
    public static final int TAP_GAME = 2;
    public static final int PUZZLE = 3;
    public static final int RUNNING_GAME = 4;
    public static final int KUIS = 5;
    protected SubCerita next;
    protected SubCerita prev;
    protected String nama;
    protected int tipe;
    protected boolean unlocked;
    protected int score;
    protected int asalCerita;
    protected int index;

    /**
     * Konstruktor dengan parameter nama dan tipe subcerita.
     *
     * @param nama
     * @param type
     */
    public SubCerita(String nama, int tipe) {
        this.nama = nama;
        this.tipe = tipe;

    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getNama() {
        return nama;
    }

    public int getTipe() {
        return tipe;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setNext(SubCerita next) {
        this.next = next;
    }

    public void setPrev(SubCerita prev) {
        this.prev = prev;
    }

    public SubCerita getNext() {
        return next;
    }

    public SubCerita getPrev() {
        return prev;
    }

    public void setAsalCerita(int i) {
        asalCerita = i;
    }

    public int getAsalCerita() {
        return asalCerita;
    }

    public void setIndex(int i) {
        index = i;
    }

    public int getIndex() {
        return index;
    }
}
