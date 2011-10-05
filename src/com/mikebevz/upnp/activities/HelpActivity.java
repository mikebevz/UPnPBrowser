package com.mikebevz.upnp.activities;

import android.app.Activity;
import android.os.Bundle;
import com.mikebevz.upnp.R;

/**
 * @author mikebevz
 */
public class HelpActivity extends Activity {

/**
 * Called when the activity is first created.
 */
@Override
public void onCreate(Bundle icicle) {
  super.onCreate(icicle);
  setTitle("Help and Info");

  setContentView(R.layout.help);
}
}
