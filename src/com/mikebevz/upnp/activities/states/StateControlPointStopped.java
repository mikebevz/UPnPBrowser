/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.activities.states;

import android.content.Context;
import com.mikebevz.upnp.ActivityState;
import com.mikebevz.upnp.activities.MainActivity;

/**
 *
 * @author mikebevz
 */
public class StateControlPointStopped implements ActivityState {

    private final Context context;
    private final MainActivity mainActivity;

    public StateControlPointStopped(Context context) {
        this.context = context;
        this.mainActivity = (MainActivity)context;
    }

    public void setSettings() {
        
        mainActivity.devListAdapter.stopControlPoint();

    }
}
