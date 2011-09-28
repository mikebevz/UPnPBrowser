/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.activities.states;

import android.content.Context;
import com.mikebevz.upnp.ActivityState;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.activities.MainActivity;

/**
 *
 * @author mikebevz
 */
public class StateWifiNotConnected implements ActivityState {

    private final Context context;
    private final MainActivity mainActivity;

    public StateWifiNotConnected(Context context) {
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
