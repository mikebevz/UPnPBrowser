/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.activities;

import android.content.Context;
import com.mikebevz.upnp.ActivityState;
import com.mikebevz.upnp.R;

/**
 *
 * @author mikebevz
 */
class StateWifiDisabled implements ActivityState {
    private final Context context;
    private final MainActivity mainActivity;

    StateWifiDisabled(Context context) {
        this.context = context;
        this.mainActivity = (MainActivity)context;
    }

    public void setSettings() {
        mainActivity.devListAdapter.cancel();
        mainActivity.setContentView(R.layout.wifi_disabled);
        
    }
    
}
