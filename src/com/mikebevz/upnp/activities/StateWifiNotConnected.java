/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.activities;

import android.content.Context;
import android.widget.Toast;
import com.mikebevz.upnp.ActivityState;
import com.mikebevz.upnp.R;

/**
 *
 * @author mikebevz
 */
class StateWifiNotConnected implements ActivityState {

    private final Context context;
    private final MainActivity mainActivity;

    StateWifiNotConnected(Context context) {
        this.context = context;
        this.mainActivity = (MainActivity) context;
    }

    public void setSettings() {

        //Toast toast = Toast.makeText(context.getApplicationContext(), R.string.connect_to_wifi, Toast.LENGTH_LONG);
        //toast.show();
        mainActivity.devListAdapter.cancel();
        mainActivity.setContentView(R.layout.no_wifi);
    }
}
