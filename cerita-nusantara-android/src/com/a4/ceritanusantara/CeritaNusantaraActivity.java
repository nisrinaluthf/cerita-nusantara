package com.a4.ceritanusantara;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CeritaNusantaraActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cerita_nusantara);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cerita_nusantara, menu);
		return true;
	}

}
