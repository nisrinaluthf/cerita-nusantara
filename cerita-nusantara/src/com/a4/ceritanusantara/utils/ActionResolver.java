package com.a4.ceritanusantara.utils;

/**
 * Kelas ActionResolver merepresentasikan kelas action untuk berbagi nilai di
 * Facebook dan Twitter.
 *
 */
public interface ActionResolver {

    public void shareToFacebook(String text);

    public void shareToTwitter(String text);
}
