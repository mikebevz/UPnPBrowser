/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.DeviceList;
import org.cybergarage.upnp.ServiceList;

/**
 *
 * @author mikebevz
 */
public class UpnpBrowserApp extends Application {
    
    private DeviceList deviceList;
    private ActionList actionList;
    private ServiceList serviceList;
    
    
    public void setDeviceList(DeviceList list) {
        this.deviceList = list;
    }
    
    public DeviceList getDeviceList() {
        return deviceList;
    }

    public void setActionList(ActionList actionList) {
        this.actionList = actionList;
    }
    
    public ActionList getActionList() {
        return actionList;
    }

    public void setServiceList(ServiceList sList) {
        this.serviceList = sList;
    }
    
    public ServiceList getServiceList() {
        return this.serviceList;
    }
    
    public Boolean IsWifiConnected() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

  
    
    
}
