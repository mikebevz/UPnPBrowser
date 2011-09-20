/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikebevz.upnp.tasks;

import org.cybergarage.upnp.Device;

/**
 *
 * @author mikebevz
 */
public interface OnDeviceDetails {

    public void OnDeviceDetailsSuccess(Device result);
    public void OnDeviceDetailsProgressUpdate(Integer integer);
    public void OnDeviceDetailsPreExecute();
    
}
