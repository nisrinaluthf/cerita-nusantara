package com.a4.ceritanusantara;

import com.a4.ceritanusantara.utils.ActionResolver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

/**
 * Kelas <code>ActionResolverAndroid</code> adalah kelas yang
 * memfasilitasi penggunaan native library milik android pada
 * project utama (cerita-nusantara).
 */
public class ActionResolverAndroid implements ActionResolver{
	
	Handler uiThread;
	Context appContext;
	
	public ActionResolverAndroid(Context appContext){
		uiThread = new Handler();
		this.appContext = appContext;
	}

	@Override
	public void shareToFacebook(String text) {
		
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT, text);
		appContext.startActivity(Intent.createChooser(shareIntent, "Bagikan skor kamu!"));
		
	}

	@Override
	public void shareToTwitter(String text) {
	}

}
