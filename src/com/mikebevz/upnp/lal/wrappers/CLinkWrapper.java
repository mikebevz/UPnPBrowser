package com.mikebevz.upnp.lal.wrappers;

import com.mikebevz.upnp.lal.DeviceList;
import com.mikebevz.upnp.lal.Service;
import com.mikebevz.upnp.lal.wrappers.clink.ControlPointImpl;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.Device;
import org.cybergarage.upnp.ServiceList;

public class CLinkWrapper implements ILibraryWrapper<org.cybergarage.upnp.DeviceList, Device> {


private ControlPoint controlPoint;
private IControlPoint localCP;
private Wrapper wrapper;


public CLinkWrapper() {
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


  device.setWrapper(this.wrapper);

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

public void setWrapper(Wrapper wrapper) {
  this.wrapper = wrapper;
}

public Service mapService(org.cybergarage.upnp.Service ser) {

  Service service = new Service();

  //service.setActionList(ser.getActionList());
  service.setControlURL(ser.getControlURL());
  service.setDescriptionURL(ser.getDescriptionURL());
  //service.setDevice(ser.getDevice());
  //service.setRootDevice(ser.getRootDevice());
  service.setSCPDData(ser.getSCPDData());
  service.setSCPDURL(ser.getSCPDURL());
  service.setServiceID(ser.getServiceID());
  service.setServiceNode(ser.getServiceNode());
  service.setServiceStateTable(ser.getServiceStateTable());
  service.setServiceType(ser.getServiceType());
  service.setSID(ser.getSID());
  service.setSubscriberList(ser.getSubscriberList());
  service.setTimeout(ser.getTimeout());
  service.setUserData(ser.getUserData());


  return service;
}

public com.mikebevz.upnp.lal.ServiceList mapServiceList(ServiceList serviceList) {

  com.mikebevz.upnp.lal.ServiceList serList = new com.mikebevz.upnp.lal.ServiceList();

  for (int i = 0; i < serviceList.size(); i++) {
    serList.add(mapService(serviceList.getService(i)));
  }

  return serList;
}

}