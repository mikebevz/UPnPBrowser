/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.uicontrolls;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.mikebevz.upnp.UpnpBrowserApp;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.ServiceList;

/**
 *
 * @author mikebevz
 */
public class DeviceBrowserUIActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("device");
        Device device = (Device) ((UpnpBrowserApp)getApplication()).getDeviceList().get(position);
        
        this.setTitle(device.getFriendlyName());
        Log.d("DeviceBrowser", device.getUPC());
        
        ServiceList serviceList = device.getServiceList(); 
        
        for(int i=0; i<serviceList.size();i++) {
            Service serv = serviceList.getService(i);
            ActionList actionList = serv.getActionList();
            Log.d("Service", serv.getServiceID());
            
            for (int j=0;j<actionList.size();j++) {
                Action action = actionList.getAction(j);
                Log.d("Action", action.getName());
                ArgumentList argList = action.getArgumentList();
                
                for (int e=0;e<argList.size();e++) {
                    Argument arg = argList.getArgument(e);
                    Log.d("Argument name", arg.getName());
                    Log.d("Argument value", arg.getValue());
                }
                
            }
            
        }
    }
}
