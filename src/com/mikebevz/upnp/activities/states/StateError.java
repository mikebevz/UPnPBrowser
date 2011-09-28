/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.activities.states;

import android.widget.TextView;
import com.mikebevz.upnp.ActivityState;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.activities.MainActivity;

/**
 *
 * @author mikebevz
 */
public class StateError implements ActivityState {
    
    private final MainActivity activity;

    public StateError(MainActivity activity) {
        this.activity = activity;
    }

    public void setSettings() {
        activity.setContentView(R.layout.error);
    }

    void setMessage(String title, String message) {
        
        TextView header = (TextView)this.activity.findViewById(R.id.error_title);
        header.setText(title);
        
        TextView msg = (TextView)this.activity.findViewById(R.id.error_message);
        msg.setText(message);
        
        
    }
    
}
