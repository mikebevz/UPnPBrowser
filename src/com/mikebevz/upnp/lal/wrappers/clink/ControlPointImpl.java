package com.mikebevz.upnp.lal.wrappers.clink;

import android.util.Log;
import com.mikebevz.upnp.lal.DeviceList;
import com.mikebevz.upnp.lal.device.OnDeviceChange;
import com.mikebevz.upnp.lal.device.OnDeviceNotification;
import com.mikebevz.upnp.lal.wrappers.IControlPoint;
import com.mikebevz.upnp.lal.wrappers.ILibraryWrapper;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.device.DeviceChangeListener;
import org.cybergarage.upnp.device.NotifyListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;

import java.util.ArrayList;
import java.util.List;


public class ControlPointImpl implements IControlPoint<Device>, DeviceChangeListener, NotifyListener {

private ControlPoint clinkCP;
private ILibraryWrapper wrapper;

//Delegates
private List<OnDeviceChange> deviceChangeDelegates;
private List<OnDeviceNotification> deviceNotificationDelegates;


public ControlPointImpl(ILibraryWrapper wrapper) {
  Log.d("Creating new ControlPoint", "NEW");

  this.wrapper = wrapper;
  deviceChangeDelegates = new ArrayList<OnDeviceChange>();
  deviceNotificationDelegates = new ArrayList<OnDeviceNotification>();

  this.clinkCP = new ControlPoint();
  this.clinkCP.addDeviceChangeListener(this);
  this.clinkCP.addNotifyListener(this);
}

public void start() {
  Log.d("Starting ControlPoint", "START");
  clinkCP.start();
}

public void stop() {
  Log.d("Stopping ControlPoint", "STOP");
  clinkCP.stop();
}


public DeviceList getDeviceList() {
  return this.wrapper.mapDeviceList(clinkCP.getDeviceList());
}

public void removeOnDeviceChangeDelegate(OnDeviceChange delegate) {
  deviceChangeDelegates.remove(delegate);
}

public void removeOnDeviceNotificationDelegate(OnDeviceNotification delegate) {
  deviceNotificationDelegates.remove(delegate);
}

public void setDeviceChangeDelegates(List<OnDeviceChange> delegates) {
  deviceChangeDelegates = delegates;
}

public void setNotificationDelegates(List<OnDeviceNotification> delegates) {
  deviceNotificationDelegates = delegates;
}

public void addDevice(Device dev) {
  Log.d("AddDevice", dev.getFriendlyName());

  com.mikebevz.upnp.lal.Device device = this.wrapper.mapDevice(dev);

  for (int i=0;i< deviceChangeDelegates.size();i++) {
    deviceChangeDelegates.get(i).deviceAdded(device);
  }
}

public void removeDevice(Device dev) {
  Log.d("RemoveDevice", dev.getFriendlyName());

  com.mikebevz.upnp.lal.Device device = this.wrapper.mapDevice(dev);

  for (int i=0; i< deviceChangeDelegates.size();i++) {
    deviceChangeDelegates.get(i).deviceDeleted(device);
  }
}

public void setOnDeviceChangeDelegate(OnDeviceChange delegate) {
  deviceChangeDelegates.add(delegate);
}

public void setOnDeviceNotificationDelegate(OnDeviceNotification delegate) {
  deviceNotificationDelegates.add(delegate);
}

//
public void deviceAdded(Device dev) {
  Log.d("Device Added", dev.getFriendlyName());
  addDevice(dev);
}

public void deviceRemoved(Device dev) {
  Log.d("Device Removed", dev.getFriendlyName());
  removeDevice(dev);
}


public void deviceNotifyReceived(SSDPPacket ssdpPacket) {
  Log.d("Device Notify", ssdpPacket.getLocation());

  //for (int i=0; i<deviceNotificationDelegates.size();i++) {
  //  deviceNotificationDelegates.get(i).deviceNotificationReceived();
  //}

}

public void setWrapper(ILibraryWrapper wrapper) {
  this.wrapper = wrapper;
}
}
