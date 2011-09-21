/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.cybergarage.upnp.ActionList;
import org.cybergarage.upnp.ArgumentList;
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
    private ArgumentList argumentList;

    
    
    /**
     * 
     * @param list
     */
    public void setDeviceList(DeviceList list) {
        this.deviceList = list;
    }
    
    /**
     * 
     * @return
     */
    public DeviceList getDeviceList() {
        return deviceList;
    }

    /**
     * 
     * @param actionList
     */
    public void setActionList(ActionList actionList) {
        this.actionList = actionList;
    }
    
    /**
     * 
     * @return
     */
    public ActionList getActionList() {
        return actionList;
    }

    /**
     * 
     * @param sList
     */
    public void setServiceList(ServiceList sList) {
        this.serviceList = sList;
    }
    
    /**
     * 
     * @return
     */
    public ServiceList getServiceList() {
        return this.serviceList;
    }
    
    /**
     * 
     * @return
     */
    public Boolean IsWifiConnected() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    /**
     * 
     * @param aList
     */
    public void setArgumentList(ArgumentList aList) {
        this.argumentList = aList;
    }
    
    /**
     * 
     * @return
     */
    public ArgumentList getArgumentList() {
        return this.argumentList;
    }


    
    
}
