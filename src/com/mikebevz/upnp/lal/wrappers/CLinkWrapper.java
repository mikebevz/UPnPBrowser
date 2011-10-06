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


  device.setDescriptionFile(dev.getDescriptionFile());
  device.setDescriptionFilePath(dev.getDescriptionFilePath());
  //device.setDeviceList(dev.getDeviceList());
  //dev.getDeviceNode()
  device.setElapsedTime(dev.getElapsedTime());
  device.setHTTPBindAddress(dev.getHTTPBindAddress());
  device.setHTTPPort(dev.getHTTPPort());
  device.setLeaseTime(dev.getLeaseTime());
  //device.setIconList(dev.getIconList());

  device.setUserData(dev.getUserData());
  device.setURLBase(dev.getURLBase());
  device.setUPC(dev.getUPC());
  device.setUDN(dev.getUDN());
  device.setTimeStamp(dev.getTimeStamp());
  device.setSSDPPort(dev.getSSDPPort());
  //device.setSSDPPacket(dev.getSSDPPacket())
  device.setSSDPIPv6MulticastAddress(dev.getSSDPIPv6MulticastAddress());
  device.setSSDPIPv4MulticastAddress(dev.getSSDPIPv4MulticastAddress());
  device.setSSDPBindAddress(dev.getSSDPBindAddress());
  device.setSSDPAnnounceCount(dev.getSSDPAnnounceCount());
  //device.setSmallestIcon(dev.getSmallestIcon());
  //device.setServiceList(dev.getServiceList());
  device.setSerialNumber(dev.getSerialNumber());
  //device.setRootDevice( dev.getRootDevice());
  device.setPresentationURL(dev.getPresentationURL());
  //device.setParentDevice(dev.getParentDevice());
  device.setMulticastIPv6Address(dev.getMulticastIPv6Address());
  device.setMulticastIPv4Address(dev.getMulticastIPv4Address());
  device.setModelURL(dev.getModelURL());
  device.setModelNumber(dev.getModelNumber());
  device.setModelDescription(dev.getModelDescription());
  device.setManufactureURL(dev.getManufactureURL());
  device.setManufacture(dev.getManufacture());


  device.setDeviceType(dev.getDeviceType());
  device.setFriendlyName(dev.getFriendlyName());
  device.setLocation(dev.getLocation());
  device.setModelName(dev.getModelName());


  return device;
}


}
