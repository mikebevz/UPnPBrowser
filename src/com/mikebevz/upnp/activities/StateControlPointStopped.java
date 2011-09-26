/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.activities;

import android.content.Context;
import android.widget.Toast;
import com.mikebevz.upnp.ActivityState;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.WifiDisabledException;
import com.mikebevz.upnp.WifiNotConnectedException;

/**
 *
 * @author mikebevz
 */
class StateControlPointStopped implements ActivityState {

    private final Context context;
    private final MainActivity mainActivity;

    StateControlPointStopped(Context context) {
        this.context = context;
        this.mainActivity = (MainActivity)context;
    }

    public void setSettings() {
        
        mainActivity.devListAdapter.stopControlPoint();

    }
}
