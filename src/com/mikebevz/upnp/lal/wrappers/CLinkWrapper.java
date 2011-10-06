package com.mikebevz.upnp.lal.wrappers;

import com.mikebevz.upnp.lal.DeviceList;
import com.mikebevz.upnp.lal.wrappers.clink.ControlPointImpl;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;

public class CLinkWrapper implements ILibraryWrapper<org.cybergarage.upnp.DeviceList, Device> {


private ControlPoint controlPoint;
//private List<OnDeviceChange> deviceChangeDelegates;
//private List<OnDeviceNotification> deviceNotificationDelegates;
private IControlPoint localCP;


public CLinkWrapper() {
    //deviceChangeDelegates = new ArrayList<OnDeviceChange>();
    //deviceNotificationDelegates = new ArrayList<OnDeviceNotification>();

  }


public IControlPoint getControlPoint() {

  return new ControlPointImpl(this);


}

public DeviceList mapDeviceList(org.cybergarage.upnp.DeviceList deviceList) {

  com.mikebevz.upnp.lal.DeviceList devList = new com.mikebevz.upnp.lal.DeviceList();

  for (int i = 0; i < deviceList.size(); i++) {
    devList.add(mapDevice(deviceList.getDevice(i)));
  }

  return devList;
}



public com.mikebevz.upnp.lal.Device mapDevice(Device dev) {
  com.mikebevz.upnp.lal.Device device = new com.mikebevz.upnp.lal.Device();

  device.setDeviceType(dev.getDeviceType());
  device.setFriendlyName(dev.getFriendlyName());
  device.setLocation(dev.getLocation());
  device.setModelName(dev.getModelName());

  return device;
}


}
