package com.mikebevz.upnp.tasks;


import com.mikebevz.upnp.lal.ServiceList;

/**
 * @author mikebevz
 */
public interface OnDeviceServiceList {
public void OnDeviceServiceListSuccess(ServiceList sList);

//public void OnDeviceServiceListProgressUpdate(Integer value);
public void OnDeviceServiceListPreExecute();
}
