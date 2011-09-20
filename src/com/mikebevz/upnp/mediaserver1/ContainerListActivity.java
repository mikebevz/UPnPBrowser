/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.mediaserver1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 *
 * @author mikebevz
 */
public class ContainerListActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here   
        
        Bundle bundle = getIntent().getExtras();
        String objectId = bundle.getString("objectId");
        Log.d("ObjectID", objectId);
    }
}
