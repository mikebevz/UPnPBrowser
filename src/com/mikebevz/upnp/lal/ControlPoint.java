package com.mikebevz.upnp.lal;

import com.mikebevz.upnp.lal.device.OnDeviceChange;
import com.mikebevz.upnp.lal.device.OnDeviceNotification;
import com.mikebevz.upnp.lal.wrappers.IControlPoint;
import com.mikebevz.upnp.lal.wrappers.Wrapper;

import java.util.List;

public class ControlPoint {


private List<OnDeviceChange> changeDelegates;
private List<OnDeviceNotification> notificationDelegates;
private DeviceList deviceList;
private DiscoveryManager discoveryManager;
private Wrapper wrapper;
private IControlPoint<?> controlPoint;


public ControlPoint() {
  this.wrapper = new Wrapper();
  this.controlPoint =  this.wrapper.getControlPoint();

}

public void addOnDeviceChangeDelegate(OnDeviceChange delegate) {
  //this.changeDelegates.add(delegate);
  this.controlPoint.setOnDeviceChangeDelegate(delegate);

}

public void addOnDeviceNotificationDelegate(OnDeviceNotification delegate) {
  this.controlPoint.setOnDeviceNotificationDelegate(delegate);
  //this.notificationDelegates.add(delegate);

}

public void start() {
  //TODO Start control point
  this.wrapper.getControlPoint().start();
}

public void stop() {
  this.wrapper.getControlPoint().stop();
}

public DeviceList getDeviceList() {
  return this.controlPoint.getDeviceList();
}



public void removeOnDeviceChangeDelegate(OnDeviceChange delegate) {
  //this.changeDelegates.remove(delegate);
  this.controlPoint.removeOnDeviceChangeDelegate(delegate);
}

public void removeOnDeviceNotificationDelegate(OnDeviceNotification delegate) {
  //this.notificationDelegates.remove(delegate);
  this.controlPoint.removeOnDeviceNotificationDelegate(delegate);
}

public List<OnDeviceChange> getChangeDelegates() {
	return changeDelegates;
}

public void setChangeDelegates(List<OnDeviceChange> changeDelegates) {
	this.changeDelegates = changeDelegates;
}

public List<OnDeviceNotification> getNotificationDelegates() {
	return notificationDelegates;
}

public void setNotificationDelegates(List<OnDeviceNotification> notificationDelegates) {
	this.notificationDelegates = notificationDelegates;
}

public void setDeviceList(DeviceList deviceList) {
	this.deviceList = deviceList;
}

public DiscoveryManager getDiscoveryManager() {
	return discoveryManager;
}

public void setDiscoveryManager(DiscoveryManager discoveryManager) {
	this.discoveryManager = discoveryManager;
}
}
