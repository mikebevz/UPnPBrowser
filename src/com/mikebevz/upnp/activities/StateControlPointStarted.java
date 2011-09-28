/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.activities;

import android.content.Context;
import android.widget.ListView;
import com.mikebevz.upnp.ActivityState;
import com.mikebevz.upnp.DeviceListAdapter;
import com.mikebevz.upnp.R;
import com.mikebevz.upnp.UpnpLibraryException;
import com.mikebevz.upnp.WifiDisabledException;
import com.mikebevz.upnp.WifiNotConnectedException;

/**
 * ControlPoint is started so we can initialize list of devices and setup listeners
 * 
 * @author mikebevz
 */
class StateControlPointStarted implements ActivityState {
    //private final Context context;
    private MainActivity mainActivity;
    

    StateControlPointStarted(Context context) {
        //this.context = context;
        this.mainActivity = (MainActivity)context;
        
    }

    public void setSettings() {
        mainActivity.setTitle(R.string.available_upnp_devices);
        
        
        try {
            mainActivity.setContentView(R.layout.main);
            
            if (mainActivity.devListAdapter == null) {
                mainActivity.devListAdapter = new DeviceListAdapter(mainActivity);
            }
            
            mainActivity.devListAdapter.startControlPoint();
            
            
            mainActivity.devicesList = (ListView) mainActivity.findViewById(R.id.devices_list);
            
            mainActivity.devicesList.setAdapter(mainActivity.devListAdapter);            
            mainActivity.devicesList.setOnItemClickListener(mainActivity);
            
            
        } catch (WifiNotConnectedException e) {
            mainActivity.setState(mainActivity.STATE_WIFI_NOTCONNECTED);
        } catch (WifiDisabledException e) {
            mainActivity.setState(mainActivity.STATE_WIFI_DISABLED);
            
        } catch (UpnpLibraryException e) {
            mainActivity.setState(mainActivity.STATE_ERROR);
            mainActivity.STATE_ERROR.setMessage("Unknown error", e.getMessage());
        }
        
        
    }
    
}
