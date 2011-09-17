/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp;

import android.app.Application;
import org.cybergarage.upnp.DeviceList;

/**
 *
 * @author mikebevz
 */
public class UpnpBrowserApp extends Application {
    
    private DeviceList deviceList;
    
    
    public void setDeviceList(DeviceList list) {
        this.deviceList = list;
    }
    
    public DeviceList getDeviceList() {
        return deviceList;
    }
}
