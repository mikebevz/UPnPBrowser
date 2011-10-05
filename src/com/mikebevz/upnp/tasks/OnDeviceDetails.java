package com.mikebevz.upnp.tasks;

import org.cybergarage.upnp.Device;

/**
 * @author mikebevz
 */
public interface OnDeviceDetails {

public void OnDeviceDetailsSuccess(Device result);

public void OnDeviceDetailsPreExecute();

public void OnDeviceDetailsCancelled(Exception lastException);

}
