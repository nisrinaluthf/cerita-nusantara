package com.a4.ceritanusantara;

import com.a4.ceritanusantara.controllers.SettingsController;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import com.a4.ceritanusantara.R;

/**
 * Kelas <code>Preferences</code> adalah kelas yang mengatur penyimpanan
 * data untuk pengaturan yaitu music dan soundFX.
 */
public class Preferences extends PreferenceActivity implements OnPreferenceChangeListener {

   @Override
   protected void onPostCreate(Bundle bundle) {
      super.onPostCreate(bundle);

      getPreferenceManager().setSharedPreferencesName("preferences"); // don't rename it, something strange here, don't solve yet
      getPreferenceManager().setSharedPreferencesMode(0);

      addPreferencesFromResource(R.xml.preferences);

   }

@Override
public boolean onPreferenceChange(Preference pref, Object newVal) {
  
	// TODO Auto-generated method stub
	return false;
}

}
