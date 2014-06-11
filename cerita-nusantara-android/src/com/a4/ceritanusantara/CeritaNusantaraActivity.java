package com.a4.ceritanusantara;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import android.view.Menu;

/**
 * Kelas <code>CeritaNusantaraActivity</code> adalah kelas launcher yang
 * menjalankan aplikasi CeritaNusantara.
 */
public class CeritaNusantaraActivity extends AndroidApplication {
	ActionResolverAndroid actionResolver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = true;
		actionResolver = new ActionResolverAndroid(this);
		initialize(new Aplikasi(actionResolver), config);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cerita_nusantara, menu);
		return true;
	}

}
